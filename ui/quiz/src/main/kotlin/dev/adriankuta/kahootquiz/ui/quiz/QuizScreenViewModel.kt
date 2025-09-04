package dev.adriankuta.kahootquiz.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adriankuta.kahootquiz.domain.models.Question
import dev.adriankuta.kahootquiz.domain.usecases.GetQuizUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class QuizScreenViewModel @Inject constructor(
    private val getQuizUseCase: GetQuizUseCase,
) : ViewModel() {
    private val _selectedChoiceIndex = MutableStateFlow<Int?>(null)
    private val _remainingTimeSeconds = MutableStateFlow<Int?>(null)
    private var timerJob: kotlinx.coroutines.Job? = null

    val uiState: StateFlow<QuizUiState> = combine(
        suspend { getQuizUseCase() }.asFlow(),
        _selectedChoiceIndex,
        _remainingTimeSeconds,
    ) { quiz, selectedChoiceIndex, remaining ->
        val currentQuestion = quiz.questions.first()
        val totalSeconds = (currentQuestion.time ?: 30.seconds).inWholeSeconds.toInt()
        QuizUiState(
            currentQuestion = currentQuestion,
            answer = selectedChoiceIndex?.let { AnswerUiState(it) },
            currentQuestionIndex = 0,
            totalQuestions = quiz.questions.size,
            totalTimeSeconds = totalSeconds,
            remainingTimeSeconds = remaining ?: totalSeconds,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = QuizUiState(),
    )

    fun onChoiceSelected(index: Int) {
        timerJob?.cancel()
        _selectedChoiceIndex.value = index
    }

    init {
        // Start countdown for the first question
        viewModelScope.launch {
            val quiz = getQuizUseCase()
            val totalSeconds = quiz.questions.first().time?.inWholeSeconds?.toInt()
            startCountdown(totalSeconds)
        }
    }

    private fun startCountdown(totalSeconds: Int?) {
        timerJob?.cancel()
        if (totalSeconds == null || totalSeconds <= 0) return
        _remainingTimeSeconds.value = totalSeconds
        timerJob = viewModelScope.launch {
            var remaining = totalSeconds
            while (remaining > 0) {
                delay(1000)
                remaining -= 1
                _remainingTimeSeconds.value = remaining
            }
            // Time out: reveal answers without a selection
            if (_selectedChoiceIndex.value == null) {
                _selectedChoiceIndex.value = -1
            }
        }
    }
}

data class QuizUiState(
    val currentQuestion: Question? = null,
    val answer: AnswerUiState? = null,
    val currentQuestionIndex: Int = 0,
    val totalQuestions: Int = 0,
    val totalTimeSeconds: Int = -1,
    val remainingTimeSeconds: Int = -1,
)

data class AnswerUiState(
    val selectedChoiceIndex: Int,
)

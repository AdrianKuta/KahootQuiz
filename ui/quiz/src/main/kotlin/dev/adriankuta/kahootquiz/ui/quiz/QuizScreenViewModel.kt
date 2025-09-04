package dev.adriankuta.kahootquiz.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adriankuta.kahootquiz.domain.models.Question
import dev.adriankuta.kahootquiz.domain.models.Quiz
import dev.adriankuta.kahootquiz.domain.usecases.GetQuizUseCase
import dev.adriankuta.kahootquiz.ui.quiz.utils.Result
import dev.adriankuta.kahootquiz.ui.quiz.utils.asResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class QuizScreenViewModel @Inject constructor(
    private val getQuizUseCase: GetQuizUseCase,
) : ViewModel() {

    private val quiz: StateFlow<QuizUiState> = flow {
        emit(QuizUiState.Success(getQuizUseCase()))
    }
        .asResult()
        .map { quizResult ->
            when (quizResult) {
                is Result.Error -> QuizUiState.Loading // Todo error handling not implemented on UI
                Result.Loading -> QuizUiState.Loading
                is Result.Success -> quizResult.data
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = QuizUiState.Loading,
        )
    private val _selectedChoiceIndex = MutableStateFlow<Int?>(null)
    private val _remainingTimeSeconds = MutableStateFlow(0)
    private val _currentQuestionIndex = MutableStateFlow(0)
    private var timerJob: Job? = null

    init {
        // Start timer when the first question is displayed (on initial quiz load)
        viewModelScope.launch {
            quiz.collect { quizState ->
                if (quizState is QuizUiState.Success) {
                    // Start only if timer hasn't been started yet and we are on the first question
                    if (timerJob == null && _currentQuestionIndex.value == 0) {
                        val firstQuestionTime =
                            quizState.quiz.questions.getOrNull(0)?.time?.inWholeSeconds?.toInt()
                        startCountdown(firstQuestionTime)
                    }
                }
            }
        }
    }

    val uiState: StateFlow<ScreenUiState> = screenUiState(
        quizFlow = quiz,
        selectedChoiceIndexFlow = _selectedChoiceIndex,
        remainingTimeSecondsFlow = _remainingTimeSeconds,
        currentQuestionIndexFlow = _currentQuestionIndex,
    )
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ScreenUiState.Loading,
        )

    fun onChoiceSelected(index: Int) {
        timerJob?.cancel()
        timerJob = null
        _selectedChoiceIndex.value = index
    }

    fun onContinue() {
        val quizState = quiz.value
        if (quizState is QuizUiState.Success) {
            val total = quizState.quiz.questions.size
            val current = _currentQuestionIndex.value
            val nextIndex = current + 1
            if (nextIndex < total) {
                _selectedChoiceIndex.value = null
                _currentQuestionIndex.value = nextIndex
                val nextQuestionTime =
                    quizState.quiz.questions[nextIndex].time?.inWholeSeconds?.toInt()
                startCountdown(nextQuestionTime)
            } else {
                // Last question reached: stop timer and keep state (could navigate to results in the future)
                timerJob?.cancel()
                timerJob = null
                _remainingTimeSeconds.value = 0
            }
        }
    }

    private fun startCountdown(totalSeconds: Int?) {
        timerJob?.cancel()
        if (totalSeconds == null || totalSeconds <= 0) {
            _remainingTimeSeconds.value = 0
            timerJob = null
            return
        }
        _remainingTimeSeconds.value = totalSeconds
        timerJob = viewModelScope.launch {
            var remaining = totalSeconds
            while (remaining > 0) {
                delay(1.seconds)
                remaining -= 1
                _remainingTimeSeconds.value = remaining
            }
            // Time out: reveal answers without a selection
            if (_selectedChoiceIndex.value == null) {
                _selectedChoiceIndex.value = -1
            }
            timerJob = null
        }
    }
}

private fun screenUiState(
    quizFlow: StateFlow<QuizUiState>,
    selectedChoiceIndexFlow: Flow<Int?>,
    remainingTimeSecondsFlow: Flow<Int>,
    currentQuestionIndexFlow: Flow<Int>,
): Flow<ScreenUiState> = combine(
    quizFlow,
    selectedChoiceIndexFlow,
    remainingTimeSecondsFlow,
    currentQuestionIndexFlow,
) { quizState, selectedChoiceIndex, remainingTimeSeconds, currentQuestionIndex ->
    when (quizState) {
        QuizUiState.Loading -> ScreenUiState.Loading
        is QuizUiState.Success -> {
            val currentQuestion = quizState.quiz.questions[currentQuestionIndex]
            val isAnswerCorrect = selectedChoiceIndex?.let { idx ->
                currentQuestion.choices?.getOrNull(idx)?.correct == true
            }

            ScreenUiState.Success(
                currentQuestion = currentQuestion,
                selectedChoiceIndex = selectedChoiceIndex,
                currentQuestionIndex = currentQuestionIndex,
                totalQuestions = quizState.quiz.questions.size,
                timerState = TimerState(
                    remainingTimeSeconds = remainingTimeSeconds,
                    totalTimeSeconds = currentQuestion.time?.inWholeSeconds?.toInt() ?: 0,
                ),
                isAnswerCorrect = isAnswerCorrect,
            )
        }
    }
}

sealed interface QuizUiState {
    data object Loading : QuizUiState
    data class Success(
        val quiz: Quiz,
    ) : QuizUiState
}

sealed interface ScreenUiState {
    data object Loading : ScreenUiState
    data class Success(
        val currentQuestion: Question,
        val selectedChoiceIndex: Int? = null,
        val currentQuestionIndex: Int = 0,
        val totalQuestions: Int = 0,
        val timerState: TimerState = TimerState(),
        val isAnswerCorrect: Boolean? = null,
    ) : ScreenUiState
}

data class TimerState(
    val remainingTimeSeconds: Int = 0,
    val totalTimeSeconds: Int = 0,
)

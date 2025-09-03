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
import javax.inject.Inject

@HiltViewModel
class QuizScreenViewModel @Inject constructor(
    private val getQuizUseCase: GetQuizUseCase,
) : ViewModel() {
    private val _selectedChoiceIndex = MutableStateFlow<Int?>(null)
    val uiState: StateFlow<QuizUiState> = combine(
        suspend { getQuizUseCase() }.asFlow(),
        _selectedChoiceIndex,
    ) { quiz, selectedChoiceIndex ->
        QuizUiState(
            currentQuestion = quiz.questions.first(),
            answer = selectedChoiceIndex?.let { AnswerUiState(it) },
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = QuizUiState(),
    )

    fun onChoiceSelected(index: Int) {
        _selectedChoiceIndex.value = index
    }
}

data class QuizUiState(
    val currentQuestion: Question? = null,
    val answer: AnswerUiState? = null,
)

data class AnswerUiState(
    val selectedChoiceIndex: Int,
)

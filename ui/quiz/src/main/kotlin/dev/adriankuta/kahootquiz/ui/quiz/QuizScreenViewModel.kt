package dev.adriankuta.kahootquiz.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adriankuta.kahootquiz.domain.models.Question
import dev.adriankuta.kahootquiz.domain.usecases.GetQuizUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizScreenViewModel @Inject constructor(
    private val getQuizUseCase: GetQuizUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = QuizUiState(getQuizUseCase().questions.first())
        }
    }

}

data class QuizUiState(
    val currentQuestion: Question? = null
)
package co.farooq.dictionary.feature_dictionary.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.farooq.dictionary.core.util.Resource
import co.farooq.dictionary.feature_dictionary.domain.use_case.GetWordInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetWordInfoViewModel @Inject constructor(
    private val getWordInfoUseCase: GetWordInfoUseCase
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    var searchQuery: State<String> = _searchQuery

    private val _eventFlow = mutableStateOf("")
    private var eventFlow: State<String> = _eventFlow

    private val _state = mutableStateOf(UIState())
    var state: State<UIState> = _state

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(1000)
            getWordInfoUseCase(query).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}
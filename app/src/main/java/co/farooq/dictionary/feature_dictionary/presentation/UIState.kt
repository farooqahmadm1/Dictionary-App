package co.farooq.dictionary.feature_dictionary.presentation

import co.farooq.dictionary.feature_dictionary.domain.model.WordInfo

data class UIState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
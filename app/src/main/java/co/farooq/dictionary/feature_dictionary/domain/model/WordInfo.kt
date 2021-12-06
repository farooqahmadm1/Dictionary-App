package co.farooq.dictionary.feature_dictionary.domain.model


data class WordInfo(
    val meaning: List<Meaning> = emptyList(),
    val phonetic: String,
    val word: String,
    val origin: String,
)

package co.farooq.dictionary.feature_dictionary.domain.model

data class Meaning(
    var definition: List<Definition>,
    var partOfSpeech: String
)

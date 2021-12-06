package co.farooq.dictionary.feature_dictionary.data.remote.dto

import co.farooq.dictionary.feature_dictionary.domain.model.Meaning


data class MeaningDto(
    val definition: List<DefinitionDto>,
    val partOfSpeech: String
) {
    fun toMeaning(): Meaning {
        return Meaning(
            definition = definition.map { it.toDefinition() },
            partOfSpeech = partOfSpeech
        )
    }
}
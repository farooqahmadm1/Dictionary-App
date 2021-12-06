package co.farooq.dictionary.feature_dictionary.data.remote.dto


import co.farooq.dictionary.feature_dictionary.domain.model.Definition
import com.google.gson.annotations.SerializedName

data class DefinitionDto(
    val definition: String,
    val example: String?,
    val synonyms: List<String>
){
    fun toDefinition() : Definition{
        return Definition(
            definition = definition,
            example = example,
            synonyms = synonyms
        )
    }
}
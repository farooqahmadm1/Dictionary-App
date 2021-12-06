package co.farooq.dictionary.feature_dictionary.data.remote.dto

import co.farooq.dictionary.feature_dictionary.data.local.entity.WordInfoEntity


data class WordInfoDto(
    val phonetics: List<PhoneticDto>,
    val meaning: List<MeaningDto>?,
    val phonetic: String?,
    val word: String?,
    val origin: String?,
) {
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            meaning = meaning?.map { it.toMeaning() } ?: emptyList(),
            phonetic = phonetic ?: "",
            word = word ?: "",
            origin = origin ?: ""
        )
    }
}
package co.farooq.dictionary.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import co.farooq.dictionary.feature_dictionary.domain.model.Meaning
import co.farooq.dictionary.feature_dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    @PrimaryKey val id: Int? = null,
    val word: String,
    val origin: String,
    val meaning: List<Meaning>,
    val phonetic: String
){
    fun toWordInfo() : WordInfo{
        return WordInfo(
            word = word,
            origin = origin,
            phonetic = phonetic,
            meaning = meaning
        )
    }
}

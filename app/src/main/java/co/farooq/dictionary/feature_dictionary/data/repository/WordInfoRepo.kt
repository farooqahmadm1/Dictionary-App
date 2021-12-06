package co.farooq.dictionary.feature_dictionary.data.repository

import co.farooq.dictionary.core.util.Resource
import co.farooq.dictionary.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow


interface WordInfoRepo {
    fun getWordInfo(word : String) : Flow<Resource<List<WordInfo>>>
}
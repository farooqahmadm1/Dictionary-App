package co.farooq.dictionary.feature_dictionary.domain.repository

import co.farooq.dictionary.core.util.Resource
import co.farooq.dictionary.feature_dictionary.data.local.WordInfoDao
import co.farooq.dictionary.feature_dictionary.data.remote.DictionaryApi
import co.farooq.dictionary.feature_dictionary.data.repository.WordInfoRepo
import co.farooq.dictionary.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class WordInfoRepoImpl @Inject constructor(val api: DictionaryApi, val dao: WordInfoDao) : WordInfoRepo {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())
        val wordInfos = dao.getWordInfos(word = word).map { it.toWordInfo() }
        emit(Resource.Loading(wordInfos))
        try {
            val remoteWordInfo = api.getWordInfo(word = word)
            dao.insert(remoteWordInfo.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            emit(Resource.Error("Oops, something got Wrong",wordInfos))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server, check your internet connection",wordInfos))
        }
        val newWordInfos = dao.getWordInfos(word = word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
    
}
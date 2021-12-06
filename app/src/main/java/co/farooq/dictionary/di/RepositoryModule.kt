package co.farooq.dictionary.di

import co.farooq.dictionary.feature_dictionary.data.local.WordInfoDao
import co.farooq.dictionary.feature_dictionary.data.remote.DictionaryApi
import co.farooq.dictionary.feature_dictionary.data.repository.WordInfoRepo
import co.farooq.dictionary.feature_dictionary.domain.repository.WordInfoRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideWordInfoRepo(api: DictionaryApi,dao: WordInfoDao) : WordInfoRepo{
        return WordInfoRepoImpl(api = api, dao = dao)
    }
}
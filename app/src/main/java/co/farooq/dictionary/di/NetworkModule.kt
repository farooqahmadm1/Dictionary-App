package co.farooq.dictionary.di

import co.farooq.dictionary.core.Constants
import co.farooq.dictionary.feature_dictionary.data.remote.DictionaryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor {
        val httpInterceptor = HttpLoggingInterceptor()
        httpInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpInterceptor
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(httpInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(httpInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .method(original.method, original.body)
                val request = requestBuilder.build()
                chain.proceed(request)
            }.build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(client: OkHttpClient) : DictionaryApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(DictionaryApi::class.java)
    }
}
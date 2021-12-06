package co.farooq.dictionary.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import co.farooq.dictionary.feature_dictionary.data.local.Converters
import co.farooq.dictionary.feature_dictionary.data.local.WordInfoDao
import co.farooq.dictionary.feature_dictionary.data.local.WordInfoDataBase
import co.farooq.dictionary.feature_dictionary.data.util.GsonParser
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(context: Context) : WordInfoDataBase{
        return Room
            .databaseBuilder(context,WordInfoDataBase::class.java,"dictionary")
            .fallbackToDestructiveMigration()
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    fun provideWordInfoDao(dataBase: WordInfoDataBase) : WordInfoDao{
        return dataBase.wordInfoDao
    }
}

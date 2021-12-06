package co.farooq.dictionary.feature_dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import co.farooq.dictionary.feature_dictionary.data.local.entity.WordInfoEntity

@Database(entities = [WordInfoEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WordInfoDataBase : RoomDatabase(){

    abstract val wordInfoDao : WordInfoDao


}
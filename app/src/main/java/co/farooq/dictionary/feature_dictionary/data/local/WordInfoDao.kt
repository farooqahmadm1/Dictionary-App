package co.farooq.dictionary.feature_dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.farooq.dictionary.feature_dictionary.data.local.entity.WordInfoEntity
import co.farooq.dictionary.feature_dictionary.domain.model.WordInfo

@Dao
interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(infos : List<WordInfoEntity>)

    @Query("SELECT * from WordInfoEntity where word LIKE '%' || :word ||'%'")
    suspend fun getWordInfos(word : String) : List<WordInfoEntity>

}
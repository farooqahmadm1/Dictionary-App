package co.farooq.dictionary.feature_dictionary.domain.use_case

import co.farooq.dictionary.core.util.Resource
import co.farooq.dictionary.feature_dictionary.data.repository.WordInfoRepo
import co.farooq.dictionary.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject


class GetWordInfoUseCase @Inject constructor(private val repo: WordInfoRepo) {

    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            emptyFlow<Resource<List<WordInfo>>>()
        }
        return repo.getWordInfo(word)
    }

}
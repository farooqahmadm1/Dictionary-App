package co.farooq.dictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.farooq.dictionary.core.components.MyApp
import co.farooq.dictionary.feature_dictionary.domain.model.WordInfo
import co.farooq.dictionary.feature_dictionary.presentation.GetWordInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                val viewModel: GetWordInfoViewModel = hiltViewModel()
                val state = viewModel.state.value
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    scaffoldState = scaffoldState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Column {
                        TextField(
                            value = viewModel.searchQuery.value,
                            onValueChange = viewModel::onSearch,
                            placeholder = { Text(text = "Search...") },
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 1
                        )
                        LazyColumn {
                            items(state.wordInfoItems.size) {
                                state.wordInfoItems.forEach {
                                    WordInfoItem(wordInfo = it)
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Divider(modifier = Modifier.height(1.dp))
                                    Spacer(modifier = Modifier.height(5.dp))
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun WordInfoItem(wordInfo: WordInfo, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = wordInfo.word,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(text = wordInfo.phonetic, fontWeight = FontWeight.Light)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = wordInfo.origin)

        wordInfo.meaning.forEach { meaning ->
            Text(text = meaning.partOfSpeech, fontWeight = FontWeight.Bold)
            meaning.definition.forEachIndexed { i, definition ->
                Text(text = "${i + 1}. ${definition.definition}")
                Spacer(modifier = Modifier.height(8.dp))
                definition.example?.let { example ->
                    Text(text = "Example: $example")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        WordInfoItem(
            wordInfo = WordInfo(
                word = "android",
                phonetic = "ˈandrɔɪd",
                origin = "early 18th century: from modern Latin androides, from andro- + -oid."
            )
        )
    }
}
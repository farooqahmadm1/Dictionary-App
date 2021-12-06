package co.farooq.dictionary.core.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import co.farooq.dictionary.ui.theme.DictionaryTheme

@Composable
fun MyApp(content : @Composable () -> Unit) {
    DictionaryTheme(){
//        Surface(color = MaterialTheme.colors.background) {
            content()
//        }
    }
}
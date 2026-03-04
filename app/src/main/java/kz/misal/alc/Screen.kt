package kz.misal.alc

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.misal.alc.ui.theme.ApplicationLayoutCource_36_3Theme

@Composable
fun Screen(modifier: Modifier = Modifier.Companion) {
//    var text by remember { mutableStateOf("") }
    val strings = remember { mutableStateListOf<String>() }

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        InputElement(modifier = Modifier.fillMaxWidth()) { text = it }
//        OutputElement(text, Modifier.fillMaxWidth())
        OutputElement(strings, Modifier.fillMaxWidth())
        InputElement(modifier = Modifier.fillMaxWidth()) { strings.add(it) }
    }
}

@Composable
fun InputElement(modifier: Modifier = Modifier, addText: (String) -> Unit) {
    var inputText by remember { mutableStateOf("") }

    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Label") }
        )

        TextButton({ addText(inputText) }) {
            Text("ADD", textDecoration = TextDecoration.Underline)
        }

    }
}

@Composable
//fun OutputElement(text: String, modifier: Modifier = Modifier) {
//    Box(modifier) {
//        Text(text = text)
//    }
//}
fun OutputElement(strings: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        items(strings) { string ->
            Text(string)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    ApplicationLayoutCource_36_3Theme {
        Screen()
    }
}
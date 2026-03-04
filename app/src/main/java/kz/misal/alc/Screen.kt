package kz.misal.alc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.misal.alc.ui.theme.ApplicationLayoutCource_36_3Theme

@Composable
fun Screen(modifier: Modifier = Modifier.Companion) {
    var text by remember { mutableStateOf("") }
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Label") }
        )
        HorizontalDivider(Modifier.padding(top = 7.dp, bottom = 9.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "Hello Android",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 10.dp),

            )

            TextButton({ text = inputText }, modifier = Modifier.padding(10.dp)) {
                Text("ADD", textDecoration = TextDecoration.Underline)
            }
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
package kz.misal.alc

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kz.misal.alc.ui.theme.ApplicationLayoutCource_36_3Theme

@Composable
fun Screen(modifier: Modifier = Modifier.Companion) {
    Text(
        text = "Hello Android",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    ApplicationLayoutCource_36_3Theme {
        Screen()
    }
}
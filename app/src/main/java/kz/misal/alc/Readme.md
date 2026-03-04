## Добавим поле ввода, и кнопку

```kotlin
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
```

## Выполним декомпозицию - разобъём экран на части

```kotlin
@Composable
fun Screen(modifier: Modifier = Modifier.Companion) {       

```
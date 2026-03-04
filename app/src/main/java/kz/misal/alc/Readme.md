# Создадим список задач

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
        // Новый элемент - горизонтальная строка, над ней и под ней - отступы padding
        HorizontalDivider(Modifier.padding(top = 7.dp, bottom = 9.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Hello Android",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 10.dp),

                )
            // Красивая текстовая кнопка, залейте её фоном, чтобы посмотреть сколько места она занимает
            TextButton({ text = inputText }, modifier = Modifier.padding(10.dp)) {
                // Текст кнопки будет подчёкрнут
                Text("ADD", textDecoration = TextDecoration.Underline)
            }
        }
    }
}
```

>Найдите новые для себя элементы, разберитесь, как они используются

## Выполним декомпозицию - разобъём экран на функциональные элементы

Сейчас функция Screen решает две задачи - ввод данных и хранение их в текстовом поле:

Разделим эти задачи, получим элемент для ввода строки и элемент для отображения строки.

Они будут иметь общую функциональную часть - состояние текста, хранимого в состоянии `text`.

А, еще, мы должны сообщить элементу ввода способ передачи значенияе в это состояние, для этого мы используем лямбду


```kotlin
@Composable
fun Screen(modifier: Modifier = Modifier.Companion) {
    var text by remember { mutableStateOf("") }
    
    Column(
        // здесь мы используем модификатор, который передан как параметр в функцию Screen!!!
        // он уже содержит отступы от элементов интерфейса Android 
        modifier = modifier.padding(horizontal = 16.dp).background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputElement(modifier = Modifier.fillMaxWidth()) { text = it }
        OutputElement(text, Modifier.fillMaxWidth())
    }
}

// Обратите внимание на лямбду, с помощью которой мы передали значение из функции InputElement в функцию Screen
// addText: (String) -> Unit)
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
fun OutputElement(text: String, modifier: Modifier = Modifier) {
    Box(modifier) {
        Text(text = text)
    }
}
```

>   Найдите новые для себя элементы, разберитесь, как они используются

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

## Добавим полноценный список!!

- Изменим элементы вывода

```kotlin
@Composable
//fun OutputElement(text: String, modifier: Modifier = Modifier) {
//    Box(modifier) {
//        Text(text = text)
//    }
//}
fun OutputElement(strings: List<String>, modifier: Modifier = Modifier) {
    Column() {
        strings.forEach { string ->
            Text(string)
        }
    }
}
```

- Изменим экран
```kotlin
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
```

> Ой, у нас нарушился порядок элементов!  
> 
> И, надо бы очищать состояние `inputText` после каждого нажатия кнопки `ADD`!  
> 
> Сделайте это!
>
> 

## Правильный список!

Проблема нашего кода в том, что если список буде длинным, а элементы списка - сложными для обрисовки, мы получим большие задержки про скроллинге списка.

Скроллинг, мы его не добавили!!! Добавим!

```kotlin
fun OutputElement(strings: List<String>, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Column(modifier.horizontalScroll(scrollState)) {
        strings.forEach { string ->
            Text(string)
        }
    }
}
```

Ну вот!

Так вот, чтобы не держать в памяти  все отображения элементов списка, используют

LazyCulumn - он создаёт столько элементов, сколько помещается на экране и когда элемент выходит за его пределы при скроллинге, этот элемент используется повторно.

А еще он умеет скроллить, анимировать, в общем хорошая штука.

```kotlin
fun OutputElement(strings: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        items(strings) { string ->
            Text(string)
        }
    }
}
```

## Добавим кнопку "Выполнено" для удаления элемента из списка

Теперь мы отображаем в списке на только строку, создадим отдельный компонент для элемента строки

Но перед этим...

```kotlin
data class TodoItem(val task:String, val id: String = UUID.randomUUID().toString())
```
Создадим специальный класс, который позволит любой нашей задаче быть уникальной!

```kotlin
@Composable
fun Item(item: TodoItem){
    Row(Modifier.fillMaxWidth()){
        Text(item.task)
    }
}
```

И компонентную функцию, которая наш элемент будет отображать!

```kotlin
@Composable
fun Item(item: TodoItem){
    Row(Modifier.fillMaxWidth()){
        Text(item.task)
    }
}
```

Отлично, теперь можно вывести наш список!

Задание: - измените код таким образом, чтобы вместо списка строк мы оперировали списком элементов типа TodoItem
и измените OutputElement, чтобы он использовал компонентную функцию Item для вывода списка TodoItem


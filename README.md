![highlights_banner_opaque](https://github.com/SnipMeDev/Highlights/assets/8405055/e123ce0f-6f58-451a-9e0a-893c0809b909)

# Highlights
Kotlin Multiplatform syntax highlighting engine

## Installation
```sh
implementation("dev.snipme:highlights:0.4.0")
```

## Usage

> 💡 As each Highlights instance caches code analysis, it is recommended to re-use the same instance for small code changes.

To start, simply put any code snippet in the default builder

```kotlin
Highlights.default().apply {
    setCode("public class ExampleClass {}")
    // Keywords = [public, class], Marks = [{, }]
    getCodeStructure()
    // BoldHighlight, ColorHighlight
    getHighlights()
}
```

You can also set language, theme and phrase emphasis. 
Language and theme has impact on the ColorHighlight and emphasis is represented by the BoldHighlight.

```kotlin
Highlights.Builder()
    .code("public class ExampleClass {}")
    .theme(SyntaxThemes.monokai())
    .language(SyntaxLanguage.JAVA)
    .emphasis(PhraseLocation(13, 25)) // ExampleClass
    .build()
    .run {
        getHighlights()
    }
```

More advance usage of this library is shown [here](/sample).

## Themes

TBD

License
=======

    Copyright 2023 Tomasz Kądziołka.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

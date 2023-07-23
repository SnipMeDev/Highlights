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

### Dark

<table>
    <th>Darcula</th>
    <th>Monokai</th>
    <th>Notepad</th>
    <th>Matrix</th>
    <th>Pastel</th>
    <tr>
        <td>

- ![#EDEDED](https://placehold.co/15x15/EDEDED/EDEDED.png) Code
- ![#CC7832](https://placehold.co/15x15/CC7832/CC7832.png) Keyword
- ![#6A8759](https://placehold.co/15x15/6A8759/6A8759.png) String
- ![#6897BB](https://placehold.co/15x15/6897BB/6897BB.png) Literal
- ![#909090](https://placehold.co/15x15/909090/909090.png) Comment
- ![#BBB529](https://placehold.co/15x15/BBB529/BBB529.png) Metadata
- ![#629755](https://placehold.co/15x15/629755/629755.png) Multiline Comment
- ![#CC7832](https://placehold.co/15x15/CC7832/CC7832.png) Punctuation
- ![#EDEDED](https://placehold.co/15x15/EDEDED/EDEDED.png) Mark
        </td>
<td>

- ![#F8F8F2](https://placehold.co/15x15/F8F8F2/F8F8F2.png) Code
- ![#F92672](https://placehold.co/15x15/F92672/F92672.png) Keyword
- ![#E6DB74](https://placehold.co/15x15/E6DB74/E6DB74.png) String
- ![#AE81FF](https://placehold.co/15x15/AE81FF/AE81FF.png) Literal
- ![#FD971F](https://placehold.co/15x15/FD971F/FD971F.png) Comment
- ![#B8F4B8](https://placehold.co/15x15/B8F4B8/B8F4B8.png) Metadata
- ![#FD971F](https://placehold.co/15x15/FD971F/FD971F.png) Multiline Comment
- ![#F8F8F2](https://placehold.co/15x15/F8F8F2/F8F8F2.png) Punctuation
- ![#F8F8F2](https://placehold.co/15x15/F8F8F2/F8F8F2.png) Mark
</td>
<td>

- ![#000080](https://placehold.co/15x15/000080/000080.png) Code
- ![#0000FF](https://placehold.co/15x15/0000FF/0000FF.png) Keyword
- ![#808080](https://placehold.co/15x15/808080/808080.png) String
- ![#FF8000](https://placehold.co/15x15/FF8000/FF8000.png) Literal
- ![#008000](https://placehold.co/15x15/008000/008000.png) Comment
- ![#000080](https://placehold.co/15x15/000080/000080.png) Metadata
- ![#008000](https://placehold.co/15x15/008000/008000.png) Multiline Comment
- ![#AA2C8C](https://placehold.co/15x15/AA2C8C/AA2C8C.png) Punctuation
- ![#AA2C8C](https://placehold.co/15x15/AA2C8C/AA2C8C.png) Mark
</td>
<td>

- ![#008500](https://placehold.co/15x15/008500/008500.png) Code
- ![#008500](https://placehold.co/15x15/008500/008500.png) Keyword
- ![#269926](https://placehold.co/15x15/269926/269926.png) String
- ![#39E639](https://placehold.co/15x15/39E639/39E639.png) Literal
- ![#67E667](https://placehold.co/15x15/67E667/67E667.png) Comment
- ![#008500](https://placehold.co/15x15/008500/008500.png) Metadata
- ![#67E667](https://placehold.co/15x15/67E667/67E667.png) Multiline Comment
- ![#008500](https://placehold.co/15x15/008500/008500.png) Punctuation
- ![#008500](https://placehold.co/15x15/008500/008500.png) Mark
</td>
<td>

- ![#DFDEE0](https://placehold.co/15x15/DFDEE0/DFDEE0.png) Code
- ![#729FCF](https://placehold.co/15x15/729FCF/729FCF.png) Keyword
- ![#93CF55](https://placehold.co/15x15/93CF55/93CF55.png) String
- ![#8AE234](https://placehold.co/15x15/8AE234/8AE234.png) Literal
- ![#888A85](https://placehold.co/15x15/888A85/888A85.png) Comment
- ![#5DB895](https://placehold.co/15x15/5DB895/5DB895.png) Metadata
- ![#888A85](https://placehold.co/15x15/888A85/888A85.png) Multiline Comment
- ![#CB956D](https://placehold.co/15x15/CB956D/CB956D.png) Punctuation
- ![#CB956D](https://placehold.co/15x15/CB956D/CB956D.png) Mark
</td>
    </tr>
</table>

### Light


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

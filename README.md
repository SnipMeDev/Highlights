![highlights_banner_opaque](https://github.com/SnipMeDev/Highlights/assets/8405055/e123ce0f-6f58-451a-9e0a-893c0809b909)

[![Maven Central](https://img.shields.io/maven-central/v/dev.snipme/highlights)](https://mvnrepository.com/artifact/dev.snipme)
[![Kotlin](https://img.shields.io/badge/kotlin-1.9.23-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

# Highlights
Kotlin Multiplatform syntax highlighting engine.

## Installation ⬇️
```shell
repositories {
    mavenCentral()
}
```

```shell
implementation("dev.snipme:highlights:0.9.0")
```

## Features ✨
- Code component analysis (Keyword, comment, etc.)
- Multiple syntax languages (Java, Swift, Kotlin, C, ...)
- Themes
- Text bolding (emphasis)
- Result caching and support for incremental changes
- Written in pure Kotlin, so available for many platforms 📱 💻 🖥️

## Support ☕
Kotlin Multiplatform is a fresh environment and developing for it is neither fast nor easy 🥲

If you feel that any of our project has saved you a time or effort, then consider supporting us via:  
[🧋 Buy Me A Coffee](https://bmc.link/SnipMeDev)

## Usage ✍️

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

## Languages 🌍

`C`,
`C++`,
`DART`,
`JAVA`,
`KOTLIN`,
`RUST`,
`C#`,
`COFFEESCRIPT`,
`JAVASCRIPT`,
`PERL`,
`PYTHON`,
`RUBY`,
`SHELL`,
`SWIFT`,
`TYPESCRIPT`,
`GO`,
`PHP`

## Themes 🖌️

The library comes with predefined syntax coloring themes available in `SyntaxThemes`:

### Dark 🌚

<table>
    <th>Darcula</th>
    <th>Monokai</th>
    <th>Notepad</th>
    <th>Matrix</th>
    <th>Pastel</th>
    <th>Atom One</th>
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
<td>

- ![#DFDEE0](https://placehold.co/15x15/BBBBBB/BBBBBB.png) Code
- ![#729FCF](https://placehold.co/15x15/D55FDE/D55FDE.png) Keyword
- ![#93CF55](https://placehold.co/15x15/89CA78/89CA78.png) String
- ![#8AE234](https://placehold.co/15x15/D19A66/D19A66.png) Literal
- ![#888A85](https://placehold.co/15x15/5C6370/5C6370.png) Comment
- ![#5DB895](https://placehold.co/15x15/E5C07B/E5C07B.png) Metadata
- ![#888A85](https://placehold.co/15x15/5C6370/5C6370.png) Multiline Comment
- ![#CB956D](https://placehold.co/15x15/EF596F/EF596F.png) Punctuation
- ![#CB956D](https://placehold.co/15x15/2BBAC5/2BBAC5.png) Mark

</td>
    </tr>
</table>

### Light 🌞

<table>
    <th>Darcula</th>
    <th>Monokai</th>
    <th>Notepad</th>
    <th>Matrix</th>
    <th>Pastel</th>
    <th>Atom One</th>
    <tr>
<td>

- ![#121212](https://placehold.co/15x15/121212/121212.png) Code
- ![#CC7832](https://placehold.co/15x15/CC7832/CC7832.png) Keyword
- ![#6A8759](https://placehold.co/15x15/6A8759/6A8759.png) String
- ![#6897BB](https://placehold.co/15x15/6897BB/6897BB.png) Literal
- ![#909090](https://placehold.co/15x15/909090/909090.png) Comment
- ![#BBB529](https://placehold.co/15x15/BBB529/BBB529.png) Metadata
- ![#629755](https://placehold.co/15x15/629755/629755.png) Multiline Comment
- ![#CC7832](https://placehold.co/15x15/CC7832/CC7832.png) Punctuation
- ![#121212](https://placehold.co/15x15/121212/121212.png) Mark
</td>
<td>

- ![#07070D](https://placehold.co/15x15/07070D/07070D.png) Code
- ![#F92672](https://placehold.co/15x15/F92672/F92672.png) Keyword
- ![#E6DB74](https://placehold.co/15x15/E6DB74/E6DB74.png) String
- ![#AE81FF](https://placehold.co/15x15/AE81FF/AE81FF.png) Literal
- ![#FD971F](https://placehold.co/15x15/FD971F/FD971F.png) Comment
- ![#B8F4B8](https://placehold.co/15x15/B8F4B8/B8F4B8.png) Metadata
- ![#FD971F](https://placehold.co/15x15/FD971F/FD971F.png) Multiline Comment
- ![#07070D](https://placehold.co/15x15/07070D/07070D.png) Punctuation
- ![#07070D](https://placehold.co/15x15/07070D/07070D.png) Mark
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

- ![#20211F](https://placehold.co/15x15/20211F/20211F.png) Code
- ![#729FCF](https://placehold.co/15x15/729FCF/729FCF.png) Keyword
- ![#93CF55](https://placehold.co/15x15/93CF55/93CF55.png) String
- ![#8AE234](https://placehold.co/15x15/8AE234/8AE234.png) Literal
- ![#888A85](https://placehold.co/15x15/888A85/888A85.png) Comment
- ![#5DB895](https://placehold.co/15x15/5DB895/5DB895.png) Metadata
- ![#888A85](https://placehold.co/15x15/888A85/888A85.png) Multiline Comment
- ![#CB956D](https://placehold.co/15x15/CB956D/CB956D.png) Punctuation
- ![#CB956D](https://placehold.co/15x15/CB956D/CB956D.png) Mark
</td>
<td>

- ![#DFDEE0](https://placehold.co/15x15/383A42/383A42.png) Code
- ![#729FCF](https://placehold.co/15x15/A626A4/A626A4.png) Keyword
- ![#93CF55](https://placehold.co/15x15/50A14F/50A14F.png) String
- ![#8AE234](https://placehold.co/15x15/986801/986801.png) Literal
- ![#888A85](https://placehold.co/15x15/A1A1A1/A1A1A1.png) Comment
- ![#5DB895](https://placehold.co/15x15/C18401/C18401.png) Metadata
- ![#888A85](https://placehold.co/15x15/A1A1A1/A1A1A1.png) Multiline Comment
- ![#CB956D](https://placehold.co/15x15/E45649/E45649.png) Punctuation
- ![#CB956D](https://placehold.co/15x15/526FFF/526FFF.png) Mark

</td>
    </tr>
</table>

You can also prepare your own themes and use them. Just create the `SyntaxTheme` class:

```kotlin
SyntaxTheme(
    key = "MY_THEME",
    code = 0xEDEDED,
    keyword = 0xCC7832,
    string = 0x6A8759,
    literal = 0x6897BB,
    comment = 0x909090,
    metadata = 0xBBB529,
    multilineComment = 0x629755,
    punctuation = 0xCC7832,
    mark = 0xEDEDED
)
```

## Popular uses 🙌

If your project uses this code, please write me or add your info

<table>
    <th>Type</th>
    <th>Name</th>
    <tr>
        <td>Library</td>
        <td>
            <a href="https://github.com/SnipMeDev/KodeView">KodeView</a>
        </td>
    </tr>
    <tr>
        <td>Application</td>
        <td> <a href="https://play.google.com/store/apps/details?id=pl.tkadziolka.snipbook">SnippLog</a> </td>
    </tr>
    <tr>
        <td>Application</td>
        <td> <a href="https://opensource.respawn.pro/FlowMVI/sample/">FlowMVI Sample</a> </td>
    </tr>
</table>

## TODO 🚧
- [ ] Migrate some lists to sets
- [ ] Optimize code analysis
- [ ] Add more themes and languages
- [ ] Support italic and underline text style

## Contribution 💻
Any form of support is very welcomed. 
Bugs, problems and new feature requests should be placed in the `Issues` tab with proper labeling.
New feature can be also submitted via `Pull Requests`. 
Then make sure:
- Current and added tests have passed
- CHANGELOG and README have been updated
- New code matches library's vision and code style

License 🖋️
=======

    Copyright 2023-2024 Tomasz Kądziołka.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

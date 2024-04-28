## [0.8.1]

### Added
- Dart keywords
- TypeScript keywords
- GO keywords
- PHP keywords
- supported languages list to README

### Fixed
- all current keywords

## [0.8.0]

### Changed
- Kotlin version to 1.9.22

### Fixed
- scientific notation numbers highlight length
- redundant keyword highlights in strings and comments
- ambiguous nested forEach returns

## [0.7.1]

### Fixed
- unclosed string notation during input

## [0.7.0]

### Added
- `key` field to `SyntaxTheme` model
- `getNames()` function to `SyntaxThemes`
- `SyntaxTheme.useDark(darkMode: Boolean)` extension to `SyntaxThemes`

### Changed
- static theme constructors names in `SyntaxTheme`

## [0.6.0]

### Added
- support for other non-mobile targets

### Changed
- project maven description
- Kotlin version to 1.9.0

## [0.5.0]

### Changed
- publication script to add pom and java doc to all targets

## [0.4.2]

### Changed
- version everywhere to 0.4.2

## [0.4.1]

### Added
- more sections to README

## [0.4.0]

### Changed
- snapshot is moved to Highlights to keep it with each instance

## [0.3.1]

### Added
- README code usage examples
- folder with sample
- README installation section

## [0.3.0]

### Added
- public static `themes` and `languages` functions for easier accessing 
  all predefined values

## [0.2.1]

### Added
- this CHANGELOG file to hopefully serve as an evolving example of a
  standardized open source project CHANGELOG

## [0.2.0]

### Added
- own native algorithm for `indicesOf` function
- KMM targets (iosArm64, iosX64, iosSimulatorArm64, jvm)

### Changed
- Java target to Kotlin
- escaped comment indicator characters to the regular ones

## [0.1.0]

### Added
- token locators and other internal core components
- unit tests
- public API interface

package dev.snipme.highlights.internal

private const val WORDS_DELIMITER = " "

internal sealed class CodeDifference {
    data class Increase(val change: String) : CodeDifference()
    data class Decrease(val change: String) : CodeDifference()
    data object Full : CodeDifference()
    data object None : CodeDifference()
}

internal object CodeComparator {
    fun difference(current: String, updated: String): CodeDifference {
        val currentWords = current.tokenize().map { it.trim() }
        val updatedWords = updated.tokenize().map { it.trim() }

        return when {
            currentWords.size == updatedWords.size ->
                if (currentWords == updatedWords) CodeDifference.None
                else CodeDifference.Full

            currentWords.size < updatedWords.size -> CodeDifference.Increase(
                findDifference(
                    currentWords,
                    updatedWords,
                    isIncrease = true
                )
            )

            else -> CodeDifference.Decrease(
                findDifference(
                    currentWords,
                    updatedWords,
                    isIncrease = false
                )
            )
        }
    }

    private fun findDifference(
        current: List<String>,
        updated: List<String>,
        isIncrease: Boolean,
    ): String {
        val differentWords = if (isIncrease) {
            updated - current
        } else {
            current - updated
        }

        return differentWords.joinToString(WORDS_DELIMITER)
    }

    private fun String.tokenize() =
        this.split("\n").map { it.split(WORDS_DELIMITER) }.flatten()
}
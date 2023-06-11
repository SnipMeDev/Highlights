package dev.snipme.highlights.internal

private const val WORDS_DELIMITER = " "

internal sealed class CodeDifference {
    data class Increase(val change: String) : CodeDifference()
    data class Decrease(val change: String) : CodeDifference()
    object None : CodeDifference()
}

internal object CodeComparator {
    fun compare(current: String, updated: String): CodeDifference {
        val currentWords = current.split(WORDS_DELIMITER)
        val updatedWords = updated.split(WORDS_DELIMITER)

        return when {
            currentWords.size == updatedWords.size -> CodeDifference.None

            currentWords.size < updatedWords.size -> CodeDifference.Increase(
                findDifference(
                    currentWords,
                    updatedWords,
                    isDecrease = false
                )
            )

            else -> CodeDifference.Decrease(
                findDifference(
                    currentWords,
                    updatedWords,
                    isDecrease = true
                )
            )
        }
    }

    private fun findDifference(
        current: List<String>,
        updated: List<String>,
        isDecrease: Boolean,
    ): String {
        // current > updated
        val differentWords = if (isDecrease) {
            current.filterIndexed { index, it -> it != updated.getOrNull(index) }
        } else {
            updated.filterIndexed { index, it -> it != current.getOrNull(index) }
        }

        return differentWords.joinToString(WORDS_DELIMITER)
    }
}
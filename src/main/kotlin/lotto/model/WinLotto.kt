package lotto.model

import lotto.Lotto
import lotto.common.ErrorMessage

class WinLotto(
    private val winningNumbers: List<Int>,
    private val bonusNumber: Int
) {
    init {
        require(winningNumbers.size == 6) { ErrorMessage.INVALID_SIZE }
        require(winningNumbers.distinct().size == winningNumbers.size) { ErrorMessage.DUPLICATE_NUMBER }
        require(bonusNumber !in winningNumbers) { ErrorMessage.DUPLICATE_NUMBER }
    }

    fun evaluate(lotto: Lotto): Rank {
        val matched = lotto.matchCount(winningNumbers)
        val hasBonus = lotto.hasBonus(bonusNumber)
        return Rank.of(matched, hasBonus)
    }
}

enum class Rank(val matchCount: Int, val prize: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0);

    companion object {
        fun of(matchCount: Int, hasBonus: Boolean): Rank = when {
            matchCount == 6 -> FIRST
            matchCount == 5 && hasBonus -> SECOND
            matchCount == 5 -> THIRD
            matchCount == 4 -> FOURTH
            matchCount == 3 -> FIFTH
            else -> NONE
        }
    }
}

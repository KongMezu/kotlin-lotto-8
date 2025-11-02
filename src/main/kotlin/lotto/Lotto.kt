package lotto

import lotto.common.ErrorMessage

class Lotto(private val numbers: List<Int>) {

    init {
        require(numbers.size == 6) { ErrorMessage.INVALID_SIZE }
        require(numbers.distinct().size == numbers.size) { ErrorMessage.DUPLICATE_NUMBER }
        require(numbers.all { it in 1..45 }) { ErrorMessage.OUT_OF_RANGE }
    }

    fun matchCount(winningNumbers: List<Int>): Int =
        numbers.count(winningNumbers::contains)

    fun hasBonus(bonus: Int): Boolean =
        bonus in numbers

    fun sortedNumbers(): List<Int> = numbers.sorted()
}

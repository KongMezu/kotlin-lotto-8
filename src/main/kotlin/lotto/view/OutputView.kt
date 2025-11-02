package lotto.view

import lotto.Lotto
import lotto.model.Rank

object OutputView {

    fun showTickets(tickets: List<Lotto>) {
        println("${tickets.size}개를 구매했습니다.")
        tickets.forEach { println(it.sortedNumbers()) }
    }

    fun showResult(results: List<Rank>, purchaseAmount: Int) {
        println("당첨 통계")
        println("---")

        Rank.values()
            .filter { it != Rank.NONE }
            .forEach { rank ->
                val count = results.count { it == rank }
                val message = when (rank) {
                    Rank.SECOND -> "5개 일치, 보너스 볼 일치 (${rank.prize.toString().formatWithComma()}원) - ${count}개"
                    else -> "${rank.matchCount}개 일치 (${rank.prize.toString().formatWithComma()}원) - ${count}개"
                }
                println(message)
            }

        val totalPrize = results.sumOf { it.prize }
        val rate = totalPrize.toDouble() / purchaseAmount * 100
        println("총 수익률은 ${"%.1f".format(rate)}%입니다.")
    }

    private fun String.formatWithComma(): String =
        this.reversed().chunked(3).joinToString(",").reversed()
}

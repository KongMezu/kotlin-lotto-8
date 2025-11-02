package lotto.view

import camp.nextstep.edu.missionutils.Console
import lotto.common.ErrorMessage

object InputView {

    fun readPurchaseAmount(): Int {
        println("구입금액을 입력해 주세요.")
        val input = Console.readLine().trim()
        val amount = input.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 숫자를 입력해 주세요.")
        require(amount % 1000 == 0) { ErrorMessage.INVALID_AMOUNT }
        return amount
    }

    fun readWinningNumbers(): List<Int> {
        println("당첨 번호를 입력해 주세요.")
        return Console.readLine()
            .split(",")
            .map { it.trim().toInt() }
    }

    fun readBonusNumber(): Int {
        println("보너스 번호를 입력해 주세요.")
        return Console.readLine().trim().toInt()
    }
}

package lotto.model

import camp.nextstep.edu.missionutils.Randoms
import lotto.Lotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoGame {

    fun start() {
        val purchaseAmount = readUntilValid {
            InputView.readPurchaseAmount()
        }

        val ticketCount = purchaseAmount / 1000
        val tickets = List(ticketCount) {
            Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6))
        }
        OutputView.showTickets(tickets)

        val winningNumbers = readUntilValid {
            InputView.readWinningNumbers()
        }

        val bonusNumber = readUntilValid {
            InputView.readBonusNumber()
        }

        val winLotto = WinLotto(winningNumbers, bonusNumber)
        val results = tickets.map { winLotto.evaluate(it) }

        OutputView.showResult(results, purchaseAmount)
    }

    private fun <T> readUntilValid(action: () -> T): T {
        while (true) {
            try {
                return action()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            } catch (e: IllegalStateException) {
                println(e.message)
            }
        }
    }
}

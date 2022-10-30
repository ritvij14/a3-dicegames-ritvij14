package androidsamples.java.dicegames

import androidx.lifecycle.ViewModel
import java.util.*

/**
 * A [ViewModel] for the gambling game that allows the user to choose a game type, set a wager, and then play.
 */
class TwoOrMoreViewModel : ViewModel() {
    private var bal = 0
    private var res: GameResult
    private var type: GameType?
    private var diecount: Int
    private var firstDie: Die? = null
    private var secondDie: Die? = null
    private var thirdDie: Die? = null
    private var fourthDie: Die? = null
    private var wagerAmt: Int
    private var check: Boolean

    /**
     * No argument constructor.
     */
    init {
        res = GameResult.UNDECIDED
        type = GameType.NONE
        wagerAmt = 0
        diecount = 0
        check = false
    }

    /**
     * Reports the current balance.
     *
     * @return the balance
     */
    fun balance(): Int {
        return bal
    }

    /**
     * Sets the balance to the given amount.
     *
     * @param balance the given amount
     */
    fun setBalance(balance: Int) {
        bal = balance
    }

    /**
     * Reports the current game type as one of the values of the `enum` [GameType].
     *
     * @return the current game type
     */
    fun gameType(): GameType? {
        return type
    }

    /**
     * Sets the current game type to the given value.
     *
     * @param gameType the game type to be set
     */
    fun setGameType(gameType: GameType?) {
        type = gameType
    }

    /**
     * Reports the wager amount.
     *
     * @return the wager amount
     */
    fun wager(): Int {
        return wagerAmt
    }

    /**
     * Sets the wager to the given amount.
     *
     * @param wager the amount to be set
     */
    fun setWager(wager: Int) {
        wagerAmt = wager
    }

    /**
     * Reports whether the wager amount is valid for the given game type and current balance.
     * For [GameType.TWO_ALIKE], the balance must be at least twice as much, for [GameType.THREE_ALIKE], at least thrice as much, and for [GameType.FOUR_ALIKE], at least four times as much.
     * The wager must also be more than 0.
     *
     * @return `true` iff the wager set is valid
     */
    val isValidWager: Boolean
        get() {
            if (wagerAmt <= 0) {
                return false
            }
            when (type) {
                GameType.TWO_ALIKE -> {
                    return bal >= wagerAmt * 2
                }
                GameType.THREE_ALIKE -> {
                    return bal >= wagerAmt * 3
                }
                GameType.FOUR_ALIKE -> {
                    return bal >= wagerAmt * 4
                }
                else -> return false
            }
        }

    /**
     * Returns the current values of all the dice.
     *
     * @return the values of dice
     */
    fun diceValues(): List<Int> {
        val ml: MutableList<Int> = Vector()
        ml.add(firstDie!!.value())
        ml.add(secondDie!!.value())
        ml.add(thirdDie!!.value())
        ml.add(fourthDie!!.value())
        return ml
    }

    /**
     * Adds the given [Die] to the game.
     *
     * @param d the Die to be added
     */
    fun addDie(d: Die?) {
        when (diecount) {
            0 -> {
                firstDie = d
                diecount++
            }
            1 -> {
                secondDie = d
                diecount++
            }
            2 -> {
                thirdDie = d
                diecount++
            }
            3 -> {
                fourthDie = d
                diecount++
            }
        }
    }

    /**
     * Simulates playing the game based on the type and the wager and reports the result as one of the values of the `enum` [GameResult].
     *
     * @return result of the current game
     * @throws IllegalStateException if the wager or the game type was not set to a proper value.
     */
    @Throws(IllegalStateException::class)
    fun play(): GameResult {
        check(!(type == null || type == GameType.NONE)) { "Game Type not set!" }
        check(!(!isValidWager && wagerAmt == 0)) { "Wager not set!" }
        check(!(!isValidWager && wagerAmt != 0)) { "Wager amount not valid!" }
        check(diecount >= 4) { "Not enough dice!" }

        firstDie!!.roll()
        secondDie!!.roll()
        thirdDie!!.roll()
        fourthDie!!.roll()

        val a1 = firstDie!!.value() == secondDie!!.value()
        val a2 = firstDie!!.value() == thirdDie!!.value()
        val a3 = firstDie!!.value() == fourthDie!!.value()
        val a4 = secondDie!!.value() == thirdDie!!.value()
        val a5 = secondDie!!.value() == fourthDie!!.value()
        val a6 = thirdDie!!.value() == fourthDie!!.value()

        when (gameType()) {

            GameType.TWO_ALIKE -> res = if (a1 && !a6 || !a1 && a6) {
                setBalance(bal + wagerAmt * 2)
                GameResult.WIN
            } else if (a2 && !a5 || !a2 && a5) {
                setBalance(bal + wagerAmt * 2)
                GameResult.WIN
            } else if (a3 && !a4 || !a3 && a4) {
                setBalance(bal + wagerAmt * 2)
                GameResult.WIN
            } else if (a1 && a6 || a2 && a5 || a3 && a4) {
                setBalance(bal + wagerAmt * 4)
                GameResult.WIN
            } else {
                setBalance(bal - wagerAmt * 2)
                GameResult.LOSS
            }

            GameType.THREE_ALIKE -> res = if (a1 && a4) {
                setBalance(bal + wagerAmt * 3)
                GameResult.WIN
            } else if (a1 && a5) {
                setBalance(bal + wagerAmt * 3)
                GameResult.WIN
            } else if (a2 && a6) {
                setBalance(bal + wagerAmt * 3)
                GameResult.WIN
            } else if (a4 && a6) {
                setBalance(bal + wagerAmt * 3)
                GameResult.WIN
            } else {
                setBalance(bal - wagerAmt * 3)
                GameResult.LOSS
            }

            GameType.FOUR_ALIKE -> res = if (a1 && a4 && a6 && a2 && a5 && a3) {
                setBalance(bal + wagerAmt * 4)
                GameResult.WIN
            } else {
                setBalance(bal - wagerAmt * 4)
                GameResult.LOSS
            }
            else -> {}
        }

        return res
    }

    fun dataSet(x: Boolean) {
        check = x
    }

    fun dataCheck(): Boolean {
        return check
    }
}
package androidsamples.java.dicegames

import androidx.lifecycle.ViewModel

class WalletViewModel
/**
 * The no argument constructor.
 */
    : ViewModel() {
    private var die: Die? = null
    private var balance = 0
    private var numberOfDie = 0
    private var increment = 0
    private var win_val = 0
    /**
     * Reports the current balance.
     *
     * @return the balance
     */
    fun balance(): Int {
        return balance
    }

    /**
     * Sets the balance to the given amount.
     *
     * @param amount the new balance
     */
    fun setBalance(amount: Int) {
        balance = amount
    }

    /**
     * Rolls the [Die] in the wallet.
     */
    fun rollDie() {
        check(numberOfDie != 0)
        die!!.roll()
        if (die!!.value() == win_val) {
            balance += increment
        }
    }

    /**
     * Reports the current value of the [Die].
     *
     * @return current value of the die
     */
    fun dieValue(): Int {
        return die!!.value()
    }

    /**
     * Sets the increment value for earning in the wallet.
     *
     * @param increment amount to add to the balance
     */
    fun setIncrement(increment: Int) {
        this.increment = increment
    }

    /**
     * Sets the value which when rolled earns the increment.
     *
     * @param winValue value to be set
     */
    fun setWinValue(winValue: Int) {
        win_val = winValue
    }

    /**
     * Sets the [Die] to be used in this wallet.
     *
     * @param d the Die to use
     */
    fun setDie(d: Die?) {
        if (numberOfDie == 0) {
            die = d
            numberOfDie++
        }
    }
}
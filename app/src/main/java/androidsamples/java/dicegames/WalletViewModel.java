package androidsamples.java.dicegames;

import androidx.lifecycle.ViewModel;

public class WalletViewModel extends ViewModel {
  /**
   * The no argument constructor.
   */
  public WalletViewModel() {
    // TODO implement method
  }

  /**
   * Reports the current balance.
   *
   * @return the balance
   */
  public int balance() {
    // TODO implement method
    return 0;
  }

  /**
   * Sets the balance to the given amount.
   *
   * @param amount the new balance
   */
  public void setBalance(int amount) {
    // TODO implement method
  }

  /**
   * Rolls the {@link Die} in the wallet.
   */
  public void rollDie() {
    // TODO implement method
  }

  /**
   * Reports the current value of the {@link Die}.
   *
   * @return current value of the die
   */
  public int dieValue() {
    // TODO implement method
    return 0;
  }

  /**
   * Sets the increment value for earning in the wallet.
   *
   * @param increment amount to add to the balance
   */
  public void setIncrement(int increment) {
    // TODO implement method
  }

  /**
   * Sets the value which when rolled earns the increment.
   *
   * @param winValue value to be set
   */
  public void setWinValue(int winValue) {
    // TODO implement method
  }

  /**
   * Sets the {@link Die} to be used in this wallet.
   *
   * @param d the Die to use
   */
  public void setDie(Die d) {
    // TODO implement method
  }
}

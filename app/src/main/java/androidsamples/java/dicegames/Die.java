package androidsamples.java.dicegames;

/**
 * Interface for a die with arbitrary number of faces.
 *
 */
public interface Die {
  /**
   * Rolls the die.
   */
  void roll();

  /**
   * Reports the value of the top face of the die.
   *
   * @return number of dots on the top face of the die
   */
  int value();
}
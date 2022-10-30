package androidsamples.java.dicegames;

import java.util.Random;

/**
 * An implementation of a six-faced {@link Die} using {@link Random}.
 */
public class Die6 implements Die {
  Random rng;
  int value;

  public Die6() {
    rng = new Random();
  }

  @Override
  public void roll() {
    value = 1 + rng.nextInt(6);
  }

  @Override
  public int value() {
    return value;
  }
}

package androidsamples.java.dicegames;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@link Die6}.
 */
public class DiceTest {
  private Die d;

  @Before
  public void initialize() {
    d = new Die6();
  }

  @Test
  public void valueIsBetween1And6() {
    d.roll();
    int value = d.value();
    assertTrue(value >= 1 && value <= 6);
  }

  @Test
  public void valueDoesNotChangeUnlessRolled() {
    d.roll();
    int valueOld = d.value();
    int valueNew = d.value();
    assertThat(valueNew, is(valueOld));
  }
}

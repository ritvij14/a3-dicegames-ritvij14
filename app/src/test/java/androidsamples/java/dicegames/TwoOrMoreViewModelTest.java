package androidsamples.java.dicegames;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class TwoOrMoreViewModelTest {
  private final TwoOrMoreViewModel m = new TwoOrMoreViewModel();

  @Test
  public void wager20Balance20GameType2Invalid() {
    m.setWager(20);
    m.setBalance(20);
    m.setGameType(GameType.TWO_ALIKE);
    assertThat(m.isValidWager(), is(false));
  }

  @Test
  public void wager20Balance40GameType2Valid() {
    m.setWager(20);
    m.setBalance(40);
    m.setGameType(GameType.TWO_ALIKE);
    assertThat(m.isValidWager(), is(true));
  }

  @Test
  public void wager20Balance40GameType3Invalid() {
    m.setWager(20);
    m.setBalance(40);
    m.setGameType(GameType.THREE_ALIKE);
    assertThat(m.isValidWager(), is(false));
  }

  @Test
  public void wager20Balance60GameType3Valid() {
    m.setWager(20);
    m.setBalance(60);
    m.setGameType(GameType.THREE_ALIKE);
    assertThat(m.isValidWager(), is(true));
  }

  @Test
  public void wager20Balance60GameType4Invalid() {
    m.setWager(20);
    m.setBalance(60);
    m.setGameType(GameType.FOUR_ALIKE);
    assertThat(m.isValidWager(), is(false));
  }

  @Test
  public void wager20Balance40GameType4Valid() {
    m.setWager(20);
    m.setBalance(80);
    m.setGameType(GameType.FOUR_ALIKE);
    assertThat(m.isValidWager(), is(true));
  }

  @Test
  public void wager0isInvalid() {
    m.setWager(0);
    assertThat(m.isValidWager(), is(false));
  }

  @Test
  public void callingPlayWithoutSettingWagerThrowsException() {
    m.setGameType(GameType.TWO_ALIKE);
    IllegalStateException thrown = assertThrows(IllegalStateException.class, m::play);
    assertThat(thrown.getMessage(), is("Wager not set, can't play!"));
  }

  @Test
  public void callingPlayWithoutSettingGameTypeThrowsException() {
    m.setWager(20);
    IllegalStateException thrown = assertThrows(IllegalStateException.class, m::play);
    assertThat(thrown.getMessage(), is("Game Type not set, can't play!"));
  }

  @Test
  public void playing4alikeWithOnlyTwoDiceThrowsExceptions() {
    m.setGameType(GameType.FOUR_ALIKE);
    m.setWager(5);
    m.setBalance(100);
    m.addDie(new Die6());
    m.addDie(new Die6());

    IllegalStateException thrown = assertThrows(IllegalStateException.class, m::play);
    assertThat(thrown.getMessage(), is("Not enough dice, can't play!"));
  }
}

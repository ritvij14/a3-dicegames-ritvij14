package androidsamples.java.dicegames;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TwoOrMoreViewModelMockitoTest {
    private final TwoOrMoreViewModel m = new TwoOrMoreViewModel();
    private final Die d1 = mock(Die6.class);
    private final Die d2 = mock(Die6.class);
    private final Die d3 = mock(Die6.class);
    private final Die d4 = mock(Die6.class);

    @Before
    public void init() {
        m.addDie(d1);
        m.addDie(d2);
        m.addDie(d3);
        m.addDie(d4);
    }

    @Test
    public void test() {
        when(d1.value()).thenReturn(1);
        assertThat(d1.value(), is(1));
        List<Integer> dice = m.diceValues();
        assertThat(dice.get(0), is(1));
    }

    @Test
    public void twoAlikeWins() {
        when(d1.value()).thenReturn(2);
        when(d2.value()).thenReturn(2);

        m.setGameType(GameType.TWO_ALIKE);
        m.setBalance(100);
        m.setWager(5);
        assertThat(m.play(), is(GameResult.WIN));
    }

    @Test
    public void twoAlikeLoss() {
        when(d1.value()).thenReturn(1);
        when(d2.value()).thenReturn(1);
        when(d1.value()).thenReturn(2);
        when(d2.value()).thenReturn(2);

        m.setGameType(GameType.TWO_ALIKE);
        m.setBalance(100);
        m.setWager(5);
        assertThat(m.play(), is(GameResult.WIN));
    }

    @Test
    public void fourAlikeWin() {
        when(d1.value()).thenReturn(4);
        when(d2.value()).thenReturn(4);
        when(d3.value()).thenReturn(4);
        when(d4.value()).thenReturn(4);

        m.setGameType(GameType.FOUR_ALIKE);
        m.setBalance(100);
        m.setWager(5);
        assertThat(m.play(), is(GameResult.WIN));
    }

    @Test
    public void fourAlikeLossoneDiff() {
        when(d1.value()).thenReturn(4);
        when(d2.value()).thenReturn(3);
        when(d3.value()).thenReturn(3);
        when(d4.value()).thenReturn(3);

        m.setGameType(GameType.FOUR_ALIKE);
        m.setBalance(100);
        m.setWager(5);
        assertThat(m.play(), is(GameResult.LOSS));
    }

    @Test
    public void fourAlikeLossWhentwoDifferent() {
        when(d1.value()).thenReturn(2);
        when(d2.value()).thenReturn(3);
        when(d3.value()).thenReturn(1);
        when(d4.value()).thenReturn(1);

        m.setGameType(GameType.FOUR_ALIKE);
        m.setBalance(100);
        m.setWager(5);
        assertThat(m.play(), is(GameResult.LOSS));
    }

    @Test
    public void fourAlikeLossWhen_2() {
        when(d1.value()).thenReturn(1);
        when(d2.value()).thenReturn(2);
        when(d3.value()).thenReturn(3);
        when(d4.value()).thenReturn(4);

        m.setGameType(GameType.FOUR_ALIKE);
        m.setBalance(100);
        m.setWager(5);
        assertThat(m.play(), is(GameResult.LOSS));
    }


    @Test
    public void threeAlikeWins() {
        when(d1.value()).thenReturn(4);
        when(d2.value()).thenReturn(4);
        when(d3.value()).thenReturn(4);

        m.setGameType(GameType.THREE_ALIKE);
        m.setBalance(100);
        m.setWager(5);
        assertThat(m.play(), is(GameResult.WIN));
    }

    @Test
    public void threeAlikeLoss() {
        when(d1.value()).thenReturn(4);
        when(d2.value()).thenReturn(4);
        when(d3.value()).thenReturn(2);

        m.setGameType(GameType.THREE_ALIKE);
        m.setBalance(100);
        m.setWager(5);
        assertThat(m.play(), is(GameResult.LOSS));
    }
}

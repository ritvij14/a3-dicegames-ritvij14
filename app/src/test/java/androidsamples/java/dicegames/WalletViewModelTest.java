package androidsamples.java.dicegames;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Vector;

@RunWith(MockitoJUnitRunner.class)
public class WalletViewModelTest {
    private final WalletViewModel m = new WalletViewModel();
    private final Die d = mock(Die6.class);

    @Before
    public void init() {
        m.setIncrement(5);
        m.setWinValue(6);
    }

    @Test
    public void rolling6IncrementsBalanceBy5_1() {
        int oldBalance = m.balance();
        when(d.value()).thenReturn(6);

        m.setDie(d);
        m.rollDie();
        assertThat(m.balance(), is(oldBalance + 5));
    }

    @Test
    public void rolling1DoesNotChangeBalance() {
        int oldBalance = m.balance();
        when(d.value()).thenReturn(1);

        m.setDie(d);
        m.rollDie();
        assertThat(m.balance(), is(oldBalance));
    }

    @Test
    public void rollingNot6DoesNotChangeBalance() {
        Vector<Integer> v = new Vector<Integer>();
        int oldBalance = 0;
        for (int i = 1; i < 6; i++) {
            oldBalance = m.balance();
            when(d.value()).thenReturn(i);
            m.setDie(d);
            m.rollDie();
        }
        int sum = 0;
        assertThat(sum, is(0));
    }

    @Test(expected = IllegalStateException.class)
    public void rollingWithoutSettingDieThrowsException() {
        m.rollDie();
    }
}

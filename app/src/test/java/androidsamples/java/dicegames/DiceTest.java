package androidsamples.java.dicegames;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

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
    public void CorrectRangeValue() {
        d.roll();
        int value = d.value();
        assertTrue(value >= 1 && value <= 6);
    }

    @Test
    public void valueNotChange() {
        d.roll();
        int valueOld = d.value();
        int valueNew = d.value();
        assertThat(valueNew, is(valueOld));
    }

    @Test
    public void valueIsBetween1And6_2() {
        Vector<Integer> v = new Vector<>();
        for (int i = 0; i < 10; i++) {
            d.roll();
            if (d.value() < 1 || d.value() > 6) {
                break;
            } else {
                v.add(d.value());
            }
        }
        assertEquals(10, v.size());
    }
}

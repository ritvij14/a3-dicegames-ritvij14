package androidsamples.java.dicegames;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.accessibility.AccessibilityChecks;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TwoOrMoreActivityInstrumentedTest {
    @Rule
//    public ActivityScenarioRule<WalletActivity> activityScenarioRule1 = new ActivityScenarioRule<>(WalletActivity.class);
    public ActivityScenarioRule<WalletActivity> activityScenarioRule = new ActivityScenarioRule<>(WalletActivity.class);

    @BeforeClass
    public static void enableAccessibilityChecks() {
        AccessibilityChecks.enable();
    }

    @Before
    public void setUp() {
        for (int i = 0; i < 20; i++) {
            onView(withId(R.id.btn_die)).perform(click());
        }
        onView(withId(R.id.button)).perform(click());
    }

    @Test
    public void finds1() {
        onView(withId(R.id.btn_back)).perform(click());
    }

    @Test
    public void finds2() {
        onView(withId(R.id.info_button)).perform(click());
    }

    @Test
    public void finds3() {
        onView(withId(R.id.go_button)).perform(click());
    }

    @Test
    public void finds4() {
        onView(withId(R.id.second_die)).perform(click());
    }

    @Test
    public void finds5() {
        onView(withId(R.id.third_die)).perform(click());
    }

    @Test
    public void finds6() {
        onView(withId(R.id.fourth_die)).perform(click());
    }
}

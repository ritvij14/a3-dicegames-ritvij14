package androidsamples.java.dicegames;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.accessibility.AccessibilityChecks;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

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
public class WalletActivityInstrumentedTest {
    @Rule
    public ActivityScenarioRule<WalletActivity> activityScenarioRule = new ActivityScenarioRule<>(WalletActivity.class);

    @BeforeClass
    public static void enableAccessibility() {
        AccessibilityChecks.enable();
    }

    @Test
    public void Error1() {
        onView(withId(R.id.button)).perform(click());
    }

    @Test
    public void Error2() {
        onView(withId(R.id.btn_die)).perform(click());
    }
}

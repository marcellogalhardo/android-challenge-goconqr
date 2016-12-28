package br.com.marcellogalhardo.goconqr.eventlist;

import android.content.Intent;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.GeneralLocation;
import android.support.test.espresso.action.GeneralSwipeAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Swipe;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import br.com.marcellogalhardo.goconqr.MockWebServerUtil;
import br.com.marcellogalhardo.goconqr.R;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class EventListActivityEspressoTest {

    private static final int MOCK_WEB_SERVER_PORT = 1234;
    private static final int MOCK_GET_ALL_EVENTS_COUNT = 21;

    @Rule
    public ActivityTestRule<EventListActivity> activityTestRule =
            new ActivityTestRule<>(EventListActivity.class, false, true);

    private MockWebServer server = new MockWebServer();

    @Before
    public void setup() throws IOException {
        server.start(MOCK_WEB_SERVER_PORT);
    }

    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }

    @Test
    public void shouldShowEmptyState() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(500));

        launchEventListActivity();
        onView(withText(R.string.error_unknown)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldAddNewEvent() {
        EventListActivity activity = activityTestRule.getActivity();
        server.enqueue(MockWebServerUtil.getEventsFakeResponse(activity));
        server.enqueue(MockWebServerUtil.postEvent(activity));

        // EventListActivity
        launchEventListActivity();
        waitLittle();

        // wait for view to become visible
        onView(withId(R.id.fab)).perform(click());

        // AddEventActivity
        waitLittle();
        onView(withId(R.id.name)).perform(typeText("Espresso Test"), closeSoftKeyboard());

        // AddEventActivity Date Start Dialog
        onView(withId(R.id.start_date)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        // AddEventActivity Date Start Dialog
        onView(withId(R.id.end_date)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        // This view is in a different Activity, no need to tell Espresso.
        onView(withId(R.id.add_event_button)).perform(click());
    }

    private void launchEventListActivity() {
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);
    }

    private void waitLittle() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static ViewAction swipeFromBottomToTop() {
        return new GeneralSwipeAction(Swipe.FAST, GeneralLocation.CENTER,
                view -> {
                    float[] coordinates = GeneralLocation.CENTER.calculateCoordinates(view);
                    coordinates[1] = 0;
                    return coordinates;
                }, Press.FINGER);
    }

}

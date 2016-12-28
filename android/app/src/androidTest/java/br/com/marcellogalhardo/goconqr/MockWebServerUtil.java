package br.com.marcellogalhardo.goconqr;

import android.app.Activity;
import android.support.annotation.NonNull;

import br.com.marcellogalhardo.goconqr.util.RawUtil;
import okhttp3.mockwebserver.MockResponse;

public class MockWebServerUtil {

    @NonNull
    public static MockResponse getEventsFakeResponse(Activity activity) {
        RawUtil rawUtil = new RawUtil();
        String body = rawUtil.readTextFile(activity.getResources(), R.raw.mock_get_events);
        return new MockResponse().setResponseCode(200)
                .addHeader("Content-Type", "application/json")
                .setBody(body);
    }

    @NonNull
    public static MockResponse postEvent(Activity activity) {
        RawUtil rawUtil = new RawUtil();
        String body = rawUtil.readTextFile(activity.getResources(), R.raw.mock_post_event);
        return new MockResponse().setResponseCode(200)
                .addHeader("Content-Type", "application/json")
                .setBody(body);
    }

}

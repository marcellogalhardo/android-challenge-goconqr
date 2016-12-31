package br.com.marcellogalhardo.goconqr.addevent;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import br.com.marcellogalhardo.goconqr.R;
import br.com.marcellogalhardo.goconqr.data.Event;

public class AddEventNavigator {

    public static final int REQUEST_CODE = 1234;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, AddEventActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_up, R.anim.stay);
    }

    public static void startForResult(Activity activity) {
        Intent intent = new Intent(activity, AddEventActivity.class);
        activity.startActivityForResult(intent, REQUEST_CODE);
        activity.overridePendingTransition(R.anim.slide_up, R.anim.stay);
    }

    public static void startForResult(Fragment fragment) {
        Activity activity = fragment.getActivity();
        Intent intent = new Intent(activity, AddEventActivity.class);
        fragment.startActivityForResult(intent, REQUEST_CODE);
        activity.overridePendingTransition(R.anim.slide_up, R.anim.stay);
    }

    public static void finishActivity(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.stay, R.anim.slide_down);
    }

    public static void finishActivityForResult(Activity activity, Event event) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(Event.TAG, event);
        activity.setResult(Activity.RESULT_OK, resultIntent);
        activity.finish();
        activity.overridePendingTransition(R.anim.stay, R.anim.slide_down);
    }

}

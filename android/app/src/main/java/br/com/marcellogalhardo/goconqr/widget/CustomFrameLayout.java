package br.com.marcellogalhardo.goconqr.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by marcello on 27/12/16.
 */

public abstract class CustomFrameLayout extends FrameLayout {

    public CustomFrameLayout(Context context) {
        super(context);
        onCreate(context, null);
    }

    public CustomFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate(context, attrs);
    }

    public CustomFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onCreate(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        onCreate(context, attrs);
    }

    protected abstract void onCreate(Context context, AttributeSet attrs);

    public View setContentView(@NonNull Context context, @LayoutRes int layout) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, null, false);
        removeAllViews();
        addView(view);
        return view;
    }

}

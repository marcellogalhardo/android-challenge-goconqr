package br.com.marcellogalhardo.goconqr;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

public class MainApplication extends Application {

    private MainComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        initDagger();
        initHawk();
    }

    private void initDagger() {
        component = DaggerMainComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);
    }

    private void initHawk() {
        Hawk.init(this).build();
    }

    public MainComponent getComponent() {
        return component;
    }

}

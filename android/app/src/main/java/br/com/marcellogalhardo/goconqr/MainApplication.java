package br.com.marcellogalhardo.goconqr;

import android.app.Application;

public class MainApplication extends Application {

    private MainComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        initDagger();
    }

    private void initDagger() {
        component = DaggerMainComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);
    }

    public MainComponent getComponent() {
        return component;
    }

}

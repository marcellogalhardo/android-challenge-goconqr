package br.com.marcellogalhardo.goconqr.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import br.com.marcellogalhardo.goconqr.MainApplication;
import br.com.marcellogalhardo.goconqr.MainComponent;

public class BaseActivity extends AppCompatActivity {
    
    protected MainApplication getMainApplication() {
        return (MainApplication) getApplication();
    }

    protected MainComponent getMainComponent() {
        return getMainApplication().getComponent();
    }
}

package br.com.marcellogalhardo.goconqr.base;

import android.support.v4.app.Fragment;

import br.com.marcellogalhardo.goconqr.MainApplication;
import br.com.marcellogalhardo.goconqr.MainComponent;

public class BaseFragment extends Fragment {

    protected MainApplication getMainApplication() {
        return (MainApplication) getActivity().getApplication();
    }

    protected MainComponent getMainComponent() {
        return getMainApplication().getComponent();
    }
}

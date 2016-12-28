package br.com.marcellogalhardo.goconqr.util;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class FragmentUtil {

    public void add(FragmentManager fragmentManager, @IdRes int containerId, Fragment fragment) {
        fragmentManager.beginTransaction()
                .add(containerId, fragment)
                .commit();
    }

    public void replace(FragmentManager fragmentManager, @IdRes int containerId, Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .commit();
    }

}

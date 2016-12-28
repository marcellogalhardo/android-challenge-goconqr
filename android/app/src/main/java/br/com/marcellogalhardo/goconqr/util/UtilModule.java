package br.com.marcellogalhardo.goconqr.util;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module
public class UtilModule {

    @Provides
    @Reusable
    FragmentUtil providesFragmentUtil() {
        return new FragmentUtil();
    }

    @Provides
    @Reusable
    ViewFlipperUtil providesViewFlipperUtil() {
        return new ViewFlipperUtil();
    }

}

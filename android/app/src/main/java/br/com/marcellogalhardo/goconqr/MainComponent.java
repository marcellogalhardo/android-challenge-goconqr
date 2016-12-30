package br.com.marcellogalhardo.goconqr;

import javax.inject.Singleton;

import br.com.marcellogalhardo.goconqr.addevent.AddEventActivity;
import br.com.marcellogalhardo.goconqr.addevent.AddEventFragment;
import br.com.marcellogalhardo.goconqr.addevent.AddEventModule;
import br.com.marcellogalhardo.goconqr.data.store.EventStoreModule;
import br.com.marcellogalhardo.goconqr.eventlist.EventListActivity;
import br.com.marcellogalhardo.goconqr.eventlist.EventListFragment;
import br.com.marcellogalhardo.goconqr.eventlist.EventListModule;
import br.com.marcellogalhardo.goconqr.util.FragmentUtil;
import br.com.marcellogalhardo.goconqr.util.RawUtil;
import br.com.marcellogalhardo.goconqr.util.ViewFlipperUtil;
import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        EventListModule.class,
        AddEventModule.class,
        EventStoreModule.class,
        NetworkModule.class})
public interface MainComponent {

    void inject(MainApplication application);

    void inject(EventListActivity activity);

    void inject(EventListFragment fragment);

    void inject(AddEventActivity activity);

    void inject(AddEventFragment fragment);

    FragmentUtil fragmentUtil();

    RawUtil rawUtil();

    ViewFlipperUtil viewFlipperUtil();

}
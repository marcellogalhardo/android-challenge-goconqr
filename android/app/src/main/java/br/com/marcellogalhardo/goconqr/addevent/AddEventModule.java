package br.com.marcellogalhardo.goconqr.addevent;

import br.com.marcellogalhardo.goconqr.data.store.EventRepository;
import dagger.Module;
import dagger.Provides;

@Module
public class AddEventModule {

    @Provides
    AddEventContract.Presenter providesAddEventPresenter(EventRepository eventRepository) {
        return new AddEventPresenter(eventRepository);
    }

}

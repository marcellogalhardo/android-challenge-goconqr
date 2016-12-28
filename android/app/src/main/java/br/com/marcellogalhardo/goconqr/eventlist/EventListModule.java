package br.com.marcellogalhardo.goconqr.eventlist;

import br.com.marcellogalhardo.goconqr.data.store.EventRepository;
import dagger.Module;
import dagger.Provides;

@Module
public class EventListModule {

    @Provides
    EventListContract.Presenter providesEventListPresente(EventRepository eventRepository) {
        return new EventListPresenter(eventRepository);
    }

}

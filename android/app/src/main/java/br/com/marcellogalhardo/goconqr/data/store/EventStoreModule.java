package br.com.marcellogalhardo.goconqr.data.store;

import javax.inject.Singleton;

import br.com.marcellogalhardo.goconqr.data.store.remote.EventService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class EventStoreModule {

    @Provides
    @Singleton
    EventRepository providesEventRepository(EventService eventService) {
        return new EventRepository(eventService);
    }

    @Provides
    @Singleton
    EventService providesEventService(Retrofit retrofit) {
        return retrofit.create(EventService.class);
    }

}

package br.com.marcellogalhardo.goconqr.data.store;

import javax.inject.Singleton;

import br.com.marcellogalhardo.goconqr.data.store.client.EventProvider;
import br.com.marcellogalhardo.goconqr.data.store.client.EventService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class EventStoreModule {

    @Provides
    @Singleton
    EventRepository providesEventRepository(EventService eventService, EventProvider eventProvider) {
        return new EventRepository(eventService, eventProvider);
    }

    @Provides
    @Singleton
    EventService providesEventService(Retrofit retrofit) {
        return retrofit.create(EventService.class);
    }

    @Provides
    @Singleton
    EventProvider providesEventCache() {
        return new EventProvider();
    }

}

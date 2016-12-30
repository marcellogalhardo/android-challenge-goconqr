package br.com.marcellogalhardo.goconqr.data.store;

import javax.inject.Singleton;

import br.com.marcellogalhardo.goconqr.data.store.client.EventCache;
import br.com.marcellogalhardo.goconqr.data.store.client.EventService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class EventStoreModule {

    @Provides
    @Singleton
    EventRepository providesEventRepository(EventService eventService, EventCache eventCache) {
        return new EventRepository(eventService, eventCache);
    }

    @Provides
    @Singleton
    EventService providesEventService(Retrofit retrofit) {
        return retrofit.create(EventService.class);
    }

    @Provides
    @Singleton
    EventCache providesEventCache() {
        return new EventCache();
    }

}

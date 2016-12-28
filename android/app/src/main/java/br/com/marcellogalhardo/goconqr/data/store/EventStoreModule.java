package br.com.marcellogalhardo.goconqr.data.store;

import javax.inject.Singleton;

import br.com.marcellogalhardo.goconqr.data.store.local.EventDao;
import br.com.marcellogalhardo.goconqr.data.store.remote.EventService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class EventStoreModule {

    @Provides
    @Singleton
    EventRepository providesEventRepository(EventService eventService, EventDao eventDao) {
        return new EventRepository(eventService, eventDao);
    }

    @Provides
    @Singleton
    EventService providesEventService(Retrofit retrofit) {
        return retrofit.create(EventService.class);
    }

    @Provides
    @Singleton
    EventDao providesEventDao() {
        return new EventDao();
    }

}

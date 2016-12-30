package br.com.marcellogalhardo.goconqr.data.store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.marcellogalhardo.goconqr.data.Event;
import br.com.marcellogalhardo.goconqr.data.store.client.EventCache;
import br.com.marcellogalhardo.goconqr.data.store.client.EventService;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EventRepository {

    private EventService eventService;
    private EventCache eventCache;

    @Inject
    EventRepository(EventService eventService, EventCache eventCache) {
        this.eventService = eventService;
        this.eventCache = eventCache;
    }

    public Observable<List<Event>> getAll() {
        return eventService.getEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .concatMap(events -> {
                    eventCache.delete();
                    return eventCache.add(events);
                })
                .onErrorResumeNext(throwable -> {
                    if (throwable instanceof IOException) {
                        return eventCache.getAll();
                    }
                    return Observable.error(throwable);
                })
                .defaultIfEmpty(new ArrayList<>());
    }

    public Observable<Event> add(Event event) {
        return eventService.addEvent(event)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Void> remove(int eventId) {
        return eventService.removeEvent(eventId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}

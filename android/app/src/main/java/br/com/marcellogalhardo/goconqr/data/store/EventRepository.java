package br.com.marcellogalhardo.goconqr.data.store;

import java.util.List;

import javax.inject.Inject;

import br.com.marcellogalhardo.goconqr.data.Event;
import br.com.marcellogalhardo.goconqr.data.store.local.EventDao;
import br.com.marcellogalhardo.goconqr.data.store.remote.EventService;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EventRepository {

    private EventService eventService;
    private EventDao eventDao;

    @Inject
    public EventRepository(EventService eventService, EventDao eventDao) {
        this.eventService = eventService;
        this.eventDao = eventDao;
    }

    public Observable<List<Event>> getEvents() {
        return eventService.getEvents().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Event> addEvent(Event event) {
        return eventService.addEvent(event).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Void> removeEvent(int eventId) {
        return eventService.removeEvent(eventId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}

package br.com.marcellogalhardo.goconqr.eventlist;

import javax.inject.Inject;

import br.com.marcellogalhardo.goconqr.data.Event;
import br.com.marcellogalhardo.goconqr.data.store.EventRepository;

public class EventListPresenter implements EventListContract.Presenter {

    private EventListContract.View view;
    private EventRepository eventRepository;

    @Inject
    EventListPresenter(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void bindView(EventListContract.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void start() {
        loadEvents();
    }

    @Override
    public void loadEvents() {
        view.showLoading();
        eventRepository.getEvents().subscribe(events -> {
            view.addAllEvents(events);
            view.showContent();
        }, throwable -> {
            view.showError();
        });
    }

    @Override
    public void removeEvent(final int position, Event event) {
        eventRepository.removeEvent(event.id).subscribe(none -> {
            view.removeEvent(position);
        }, throwable -> {
            view.showRemoveErrorMessage();
        });
    }
}

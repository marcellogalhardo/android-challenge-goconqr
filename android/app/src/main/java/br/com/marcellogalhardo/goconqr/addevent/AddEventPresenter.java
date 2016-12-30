package br.com.marcellogalhardo.goconqr.addevent;

import javax.inject.Inject;

import br.com.marcellogalhardo.goconqr.data.Event;
import br.com.marcellogalhardo.goconqr.data.store.EventRepository;

/**
 * Created by marcello on 27/12/16.
 */

public class AddEventPresenter implements AddEventContract.Presenter {

    private AddEventContract.View view;
    private EventRepository eventRepository;

    @Inject
    AddEventPresenter(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void bindView(AddEventContract.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void addEvent(Event event) {
        eventRepository.add(event).subscribe(newEvent -> {
            view.showSuccessToast();
            view.finishWithResponse(newEvent);
        }, throwable -> {
            view.showErrorToast();
        });
    }

}

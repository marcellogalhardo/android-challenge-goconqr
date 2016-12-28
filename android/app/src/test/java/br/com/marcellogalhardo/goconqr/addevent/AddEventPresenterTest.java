package br.com.marcellogalhardo.goconqr.addevent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;

import br.com.marcellogalhardo.goconqr.data.Event;
import br.com.marcellogalhardo.goconqr.data.store.EventRepository;
import rx.Observable;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddEventPresenterTest {

    @Mock
    private EventRepository repository;

    @Mock
    private AddEventContract.View view;

    private AddEventPresenter presenter;

    @Before
    public void setup() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);
        presenter = new AddEventPresenter(repository);
        presenter.bindView(view);
    }

    @Test
    public void addNewEventWithSuccess() {
        Event event = createEvent();
        when(repository.addEvent(event)).then(invocation -> Observable.just(event));
        presenter.addEvent(event);
        verify(view, times(1)).showSuccessToast();
        verify(view, times(1)).finishWithResponse(event);
    }

    @Test
    public void addNewEventWithError() {
        Event event = createEvent();
        when(repository.addEvent(event)).then(invocation -> Observable.error(new Exception()));
        presenter.addEvent(event);
        verify(view, times(1)).showErrorToast();
    }

    private Event createEvent() {
        Date date = Calendar.getInstance().getTime();
        return new Event(1, "Test", date, date);
    }

}

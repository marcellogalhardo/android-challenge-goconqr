package br.com.marcellogalhardo.goconqr.eventlist;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.marcellogalhardo.goconqr.data.Event;
import br.com.marcellogalhardo.goconqr.data.store.EventRepository;
import rx.Observable;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EventListPresenterTest {

    @Mock
    private EventRepository repository;

    @Mock
    private EventListContract.View view;

    private EventListPresenter presenter;

    @Before
    public void setup() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);
        presenter = new EventListPresenter(repository);
        presenter.bindView(view);
    }

    @Test
    public void getSuccessWhenTryToCallGetEventsFromRepositoryAndShowContentLayout() {
        when(repository.getAll()).then(invocation -> createEventList());
        presenter.loadEvents();
        verify(view, times(1)).showLoading();
        verify(view, times(1)).showContent();
    }

    @Test
    public void getErrorWhenTryToCallGetEventsFromRepositoryAndShowErrorLayout() {
        when(repository.getAll()).then(invocation -> createGenericError());
        presenter.loadEvents();
        verify(view, times(1)).showLoading();
        verify(view, times(1)).showError();
    }

    @Test
    public void getSuccessWhenTryToRemoveEventWithSuccess() {
        when(repository.remove(0)).then(invocation -> createGenericSuccess());
        presenter.removeEvent(0, new Event());
        verify(view, times(1)).removeEvent(0);
    }

    @Test
    public void getSuccessWhenTryToRemoveEventWithError() {
        when(repository.remove(0)).then(invocation -> createGenericError());
        presenter.removeEvent(0, new Event());
        verify(view, times(1)).showRemoveErrorMessage();
    }

    private Observable<List<Event>> createEventList() {
        Date date = Calendar.getInstance().getTime();
        List<Event> list = new ArrayList<>(9);
        list.add(new Event(1, "Test", date, date));
        list.add(new Event(2, "Test", date, date));
        list.add(new Event(3, "Test", date, date));
        list.add(new Event(4, "Test", date, date));
        list.add(new Event(5, "Test", date, date));
        list.add(new Event(6, "Test", date, date));
        list.add(new Event(7, "Test", date, date));
        list.add(new Event(8, "Test", date, date));
        list.add(new Event(9, "Test", date, date));
        return Observable.just(list);
    }

    private Observable createGenericSuccess() {
        return Observable.just(null);
    }

    private Observable createGenericError() {
        return Observable.error(new Exception());
    }

}

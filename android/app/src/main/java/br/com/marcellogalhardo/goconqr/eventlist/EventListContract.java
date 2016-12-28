package br.com.marcellogalhardo.goconqr.eventlist;

import java.util.List;

import br.com.marcellogalhardo.goconqr.data.Event;

public interface EventListContract {

    interface View {

        void showLoading();

        void showContent();

        void showError();

        void showRemoveErrorMessage();

        void addEvent(Event event);

        void addAllEvents(List<Event> events);

        void removeEvent(int position);

        void clearEvents();

        void navigateToAddEventActivity();

    }

    interface Presenter {

        void bindView(EventListContract.View view);

        void unbindView();

        void start();

        void loadEvents();

        void removeEvent(int position, Event event);

    }

}

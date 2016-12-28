package br.com.marcellogalhardo.goconqr.addevent;

import br.com.marcellogalhardo.goconqr.data.Event;

/**
 * Created by marcello on 27/12/16.
 */

public interface AddEventContract {

    interface View {

        void showSuccessToast();

        void showErrorToast();

        void finishWithResponse(Event event);

    }

    interface Presenter {

        void bindView(AddEventContract.View view);

        void unbindView();

        void addEvent(Event event);

    }

}

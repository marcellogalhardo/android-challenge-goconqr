package br.com.marcellogalhardo.goconqr.data.store.client;

import com.orhanobut.hawk.Hawk;

import java.util.List;

import br.com.marcellogalhardo.goconqr.data.Event;
import rx.Observable;

public class EventCache {

    private static final String TAG = EventCache.class.getSimpleName();

    public Observable<List<Event>> add(List<Event> events) {
        return Observable.create(subscriber -> {
            try {
                Hawk.put(TAG, events);
                subscriber.onNext(events);
                subscriber.onCompleted();
            } catch (Exception exception) {
                subscriber.onError(exception);
            }
        });

    }

    public Observable<List<Event>> getAll() {
        return Observable.just(Hawk.get(TAG));
    }

    public Observable<Void> delete() {
        return Observable.create(subscriber -> {
            try {
                Hawk.delete(TAG);
                subscriber.onNext(null);
                subscriber.onCompleted();
            } catch (Exception exception) {
                subscriber.onError(exception);
            }
        });
    }

}

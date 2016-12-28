package br.com.marcellogalhardo.goconqr.data.store.remote;

import java.util.List;

import br.com.marcellogalhardo.goconqr.data.Event;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface EventService {

    @GET("events")
    Observable<List<Event>> getEvents();

    @POST("events")
    Observable<Event> addEvent(@Body Event event);

    @DELETE("events/{id}")
    Observable<Void> removeEvent(@Path("id") int eventId);

}

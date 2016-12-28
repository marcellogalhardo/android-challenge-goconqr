package br.com.marcellogalhardo.goconqr.data;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {

    public static final String TAG = Event.class.getSimpleName();

    public int id;
    public String name;
    public Date start;
    public Date end;
    public Date createdAt;
    public Date updatedAt;

    public Event() {

    }

    public Event(int id, String name, Date start, Date end) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
    }

}

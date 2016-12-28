package br.com.marcellogalhardo.goconqr.eventlist;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.marcellogalhardo.goconqr.R;
import br.com.marcellogalhardo.goconqr.data.Event;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {

    public static final int SCROLL_DOWN = 0;
    public static final int SCROLL_UP = 1;

    @IntDef({SCROLL_DOWN, SCROLL_UP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ScrollPosition {
    }

    private final List<Event> events;
    private OnRemoveListener onRemoveListener;
    private int lastPosition = -1;
    private int scrollPosition = SCROLL_DOWN;

    public EventListAdapter() {
        events = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Context context = holder.container.getContext();

        Event event = events.get(position);
        holder.name.setText(event.name);
        holder.description.setText(getFormattedDate(event.start));
        if (event.id > 0) {
            Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_close);
            holder.remove.setImageDrawable(drawable);
            holder.remove.setOnClickListener(v -> {
                if (onRemoveListener != null) {
                    onRemoveListener.onRemove(position, event);
                }
            });
        } else {
            Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_sync_problem);
            holder.remove.setImageDrawable(drawable);
            holder.remove.setOnClickListener(v -> {
                Toast.makeText(context, R.string.error_synced_problem, Toast.LENGTH_SHORT).show();
            });
        }

        char firstChar = event.name.charAt(0);
        int color = ContextCompat.getColor(context, R.color.colorAccent);
        TextDrawable drawable = TextDrawable.builder()
                .buildRoundRect(Character.toString(firstChar).toUpperCase(), color, 100);
        holder.title.setImageDrawable(drawable);

        setAnimation(holder.container, position);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    void addEvent(Event event) {
        events.add(event);
        notifyItemChanged(getItemCount() - 1);
    }

    void removeEvent(Event event) {
        int index = events.indexOf(event);
        removeEvent(index);
    }

    void removeEvent(int position) {
        events.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    public void refresh(List<Event> businesses) {
        this.events.clear();
        this.events.addAll(businesses);
        notifyDataSetChanged();
    }

    public void clear() {
        int num = events.size();
        events.clear();
        notifyItemRangeRemoved(0, num);
    }

    private String getFormattedDate(Date date) {
        DateFormat dateFormat = SimpleDateFormat.getDateInstance();
        return dateFormat.format(date.getTime());
    }

    void setOnRemoveListener(OnRemoveListener onRemoveListener) {
        this.onRemoveListener = onRemoveListener;
    }

    public void setScrollPosition(@ScrollPosition int scrollPosition) {
        this.scrollPosition = scrollPosition;
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition && scrollPosition == SCROLL_DOWN) {
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(),
                    R.anim.up_from_bottom);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.container)
        View container;

        @BindView(R.id.title)
        ImageView title;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.description)
        TextView description;

        @BindView(R.id.remove)
        ImageView remove;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    interface OnRemoveListener {
        void onRemove(int position, Event event);
    }

}
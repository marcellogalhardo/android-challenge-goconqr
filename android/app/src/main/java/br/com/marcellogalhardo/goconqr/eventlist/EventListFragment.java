package br.com.marcellogalhardo.goconqr.eventlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.List;

import javax.inject.Inject;

import br.com.marcellogalhardo.goconqr.R;
import br.com.marcellogalhardo.goconqr.addevent.AddEventNavigator;
import br.com.marcellogalhardo.goconqr.base.BaseFragment;
import br.com.marcellogalhardo.goconqr.data.Event;
import br.com.marcellogalhardo.goconqr.util.ViewFlipperUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventListFragment extends BaseFragment implements EventListContract.View {

    @BindView(R.id.view_flipper)
    ViewFlipper viewFlipper;

    @BindView(R.id.error_layout)
    View errorLayout;

    @BindView(R.id.loading_layout)
    View loadingLayout;

    @BindView(R.id.event_list)
    RecyclerView eventList;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Inject
    ViewFlipperUtil viewFlipperUtil;

    @Inject
    EventListContract.Presenter presenter;

    private EventListAdapter eventListAdapter;

    public static EventListFragment newInstance() {
        return new EventListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        getMainComponent().inject(this);
        setupEventList();
        presenter.bindView(this);
        presenter.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == AddEventNavigator.REQUEST_CODE) {
            if (data.hasExtra(Event.TAG)) {
                Event event = (Event) data.getSerializableExtra(Event.TAG);
                addEvent(event);
            }
        }
    }

    @Override
    public void showLoading() {
        viewFlipperUtil.setDisplayedChild(viewFlipper, loadingLayout);
        fab.hide();
    }

    @Override
    public void showContent() {
        viewFlipperUtil.setDisplayedChild(viewFlipper, eventList);
        fab.show();
    }

    @Override
    public void showError() {
        viewFlipperUtil.setDisplayedChild(viewFlipper, errorLayout);
        fab.hide();
    }

    @Override
    public void showRemoveErrorMessage() {
        Toast.makeText(getActivity(), R.string.error_unknown, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addEvent(Event event) {
        eventListAdapter.addEvent(event);
    }

    @Override
    public void removeEvent(int position) {
        eventListAdapter.removeEvent(position);
    }

    @Override
    public void addAllEvents(List<Event> events) {
        eventListAdapter.refresh(events);
    }

    @Override
    public void clearEvents() {
        eventListAdapter.clear();
    }

    @Override
    public void navigateToAddEventActivity() {
        AddEventNavigator.startForResult(this);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.error_button)
    void onErrorButtonClicked() {
        presenter.loadEvents();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.fab)
    void onFabButtonClicked() {
        navigateToAddEventActivity();
    }

    private void setupEventList() {
        eventListAdapter = new EventListAdapter();
        eventListAdapter.setOnRemoveListener((position, event) -> {
            presenter.removeEvent(position, event);
        });
        eventList.setAdapter(eventListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        eventList.setLayoutManager(layoutManager);
        eventList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy < 0) {
                    eventListAdapter.setScrollPosition(EventListAdapter.SCROLL_UP);
                } else if (dy > 0) {
                    eventListAdapter.setScrollPosition(EventListAdapter.SCROLL_DOWN);
                }
            }
        });
    }

}

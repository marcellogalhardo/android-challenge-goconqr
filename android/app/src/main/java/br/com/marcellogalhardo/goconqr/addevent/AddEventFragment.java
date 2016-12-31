package br.com.marcellogalhardo.goconqr.addevent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import br.com.marcellogalhardo.goconqr.R;
import br.com.marcellogalhardo.goconqr.base.BaseFragment;
import br.com.marcellogalhardo.goconqr.data.Event;
import br.com.marcellogalhardo.goconqr.util.CalendarUtil;
import br.com.marcellogalhardo.goconqr.widget.DatePickerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddEventFragment extends BaseFragment implements AddEventContract.View {

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.start_date)
    DatePickerView startDate;

    @BindView(R.id.end_date)
    DatePickerView endDate;

    @Inject
    AddEventContract.Presenter presenter;

    @Inject
    CalendarUtil calendarUtil;

    public static AddEventFragment newInstance() {
        Bundle args = new Bundle();
        AddEventFragment fragment = new AddEventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_event, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        getMainComponent().inject(this);
        presenter.bindView(this);
    }

    @Override
    public void showSuccessToast() {
        Toast.makeText(getContext(), R.string.success_add_event, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorToast() {
        Toast.makeText(getContext(), R.string.error_try_again_later, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishWithResponse(Event event) {
        AddEventNavigator.finishActivityForResult(getActivity(), event);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.add_event_button)
    void onAddEventButtonClicked() {
        Event event = new Event();
        event.name = name.getText().toString();
        event.start = startDate.getDate();
        event.end = endDate.getDate();
        boolean hasErrors = validate(event);
        if (!hasErrors) {
            presenter.addEvent(event);
        }
    }

    private boolean validate(Event event) {
        boolean hasErrors = false;
        if (TextUtils.isEmpty(event.name)) {
            name.setError(getString(R.string.error_empty_name));
            hasErrors = true;
        } else {
            name.setError(null);
        }
        Date now = calendarUtil.getNow().getTime();
        if (event.start.equals(now)) {
            Toast.makeText(getActivity(), R.string.error_start_date_cannot_be_empty, Toast.LENGTH_SHORT).show();
            hasErrors = true;
        }
        if (event.start.before(now)) {
            Toast.makeText(getActivity(), R.string.error_date_smaller_than_now, Toast.LENGTH_SHORT).show();
            hasErrors = true;
        }
        if (event.end.equals(now)) {
            Toast.makeText(getActivity(), R.string.error_end_date_cannot_be_empty, Toast.LENGTH_SHORT).show();
            hasErrors = true;
        }
        if (event.start.after(event.end)) {
            Toast.makeText(getActivity(), R.string.error_end_date_smaller_than_start_date, Toast.LENGTH_SHORT).show();
            hasErrors = true;
        }
        return hasErrors;
    }

}

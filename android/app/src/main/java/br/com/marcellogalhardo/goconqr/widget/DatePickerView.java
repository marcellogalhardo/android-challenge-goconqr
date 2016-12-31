package br.com.marcellogalhardo.goconqr.widget;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.marcellogalhardo.goconqr.R;
import br.com.marcellogalhardo.goconqr.util.CalendarUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DatePickerView extends CustomFrameLayout {

    private final Calendar calendar = new CalendarUtil().getNow();
    private Context context;
    private String attrName;

    @BindView(R.id.view_date_picker_name)
    TextView name;

    @BindView(R.id.view_date_picker_value)
    TextView value;

    public DatePickerView(Context context) {
        super(context);
    }

    public DatePickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DatePickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DatePickerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onCreate(Context context, AttributeSet attrs) {
        this.context = context;
        View view = setContentView(context, R.layout.view_date_picker);
        ButterKnife.bind(this, view);
        getAttrs(attrs);
        setData();
        setListeners();
    }

    private void getAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DatePickerView);
            attrName = typedArray.getString(R.styleable.DatePickerView_name);
            typedArray.recycle();
        }
    }

    private void setData() {
        if (attrName != null) {
            name.setText(attrName);
        }
    }

    private void setListeners() {
        setOnClickListener(v -> {
            openDatePicker();
        });
    }

    private void openDatePicker() {
        DatePickerDialog.OnDateSetListener listener = (view, year, monthOfYear, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setFormattedValue(calendar);
        };

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(context, listener, year, month, dayOfMonth);
        dialog.show();
    }

    private void setFormattedValue(Calendar calendar) {
        DateFormat dateFormat = SimpleDateFormat.getDateInstance();
        String formatted = dateFormat.format(calendar.getTime());
        value.setText(formatted);
    }

    public Date getDate() {
        return calendar.getTime();
    }

}

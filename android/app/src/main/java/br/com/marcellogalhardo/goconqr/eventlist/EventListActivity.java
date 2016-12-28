package br.com.marcellogalhardo.goconqr.eventlist;

import android.os.Bundle;

import javax.inject.Inject;

import br.com.marcellogalhardo.goconqr.R;
import br.com.marcellogalhardo.goconqr.base.BaseActivity;
import br.com.marcellogalhardo.goconqr.util.FragmentUtil;
import butterknife.ButterKnife;

public class EventListActivity extends BaseActivity {

    @Inject
    FragmentUtil fragmentUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        ButterKnife.bind(this);
        getMainComponent().inject(this);
        fragmentUtil.replace(getSupportFragmentManager(), R.id.fragment_container,
                EventListFragment.newInstance());
    }
}

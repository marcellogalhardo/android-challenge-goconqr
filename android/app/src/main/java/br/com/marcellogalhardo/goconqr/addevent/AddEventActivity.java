package br.com.marcellogalhardo.goconqr.addevent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import javax.inject.Inject;

import br.com.marcellogalhardo.goconqr.R;
import br.com.marcellogalhardo.goconqr.base.BaseActivity;
import br.com.marcellogalhardo.goconqr.util.FragmentUtil;
import butterknife.ButterKnife;

public class AddEventActivity extends BaseActivity {

    @Inject
    FragmentUtil fragmentUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        ButterKnife.bind(this);
        getMainComponent().inject(this);
        setupActionBar();
        fragmentUtil.replace(getSupportFragmentManager(), R.id.fragment_container,
                AddEventFragment.newInstance());
    }

    @Override
    public void onBackPressed() {
        AddEventNavigator.finishActivity(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                AddEventNavigator.finishActivity(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupActionBar() {
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setTitle(R.string.add_event);
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setHomeAsUpIndicator(R.drawable.ic_close_white);
        }
    }

}

package vnu.uet.boatsafe.ui.main;

import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.FrameLayout;

import javax.inject.Inject;
import vnu.uet.boatsafe.R;
import vnu.uet.boatsafe.ui.base.BaseActivity;
import vnu.uet.boatsafe.ui.cate.HistoryFragment;
import vnu.uet.boatsafe.ui.home.HomeFragment;
import vnu.uet.boatsafe.ui.info.InfoFragment;
import vnu.uet.boatsafe.utils.AppUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView, BottomNavigationView.OnNavigationItemSelectedListener,  BottomNavigationView.OnNavigationItemReselectedListener {

    @Inject
    MainMvpPresenter<MainMvpView> presenter;

    @BindView(R.id.mainBottomNavView)
    BottomNavigationView mainBottomNavView;
    @BindView(R.id.vpListFragment)
    FrameLayout mainFvpListFragmentrame;

    @Override
    protected void onBeforeConfigView() {

    }

    @Override
    protected int configView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        getActivityComponent().inject(this);
        setUnbinder(ButterKnife.bind(this));


        mainBottomNavView.setOnNavigationItemSelectedListener(this);
        mainBottomNavView.setOnNavigationItemReselectedListener(this);

        //Replace HomeFragment
        AppUtils.replaceFragmentToActivity(getSupportFragmentManager(), HomeFragment.newInstance(), R.id.vpListFragment, false, HomeFragment.TAG);

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionListener:
                if (!(getSupportFragmentManager().findFragmentById(R.id.vpListFragment) instanceof HomeFragment)) {
                    AppUtils.replaceFragmentToActivity(getSupportFragmentManager(), HomeFragment.newInstance(), R.id.vpListFragment, false, HomeFragment.TAG);
                }
                return true;
            case R.id.actionCategory:
                if (!(getSupportFragmentManager().findFragmentById(R.id.vpListFragment) instanceof HistoryFragment)) {
                    AppUtils.replaceFragmentToActivity(getSupportFragmentManager(), HistoryFragment.newInstance(), R.id.vpListFragment, false, HistoryFragment.TAG);
                }
                return true;
            case R.id.actionInfo:
                if (!(getSupportFragmentManager().findFragmentById(R.id.vpListFragment) instanceof InfoFragment)) {
                    AppUtils.replaceFragmentToActivity(getSupportFragmentManager(), InfoFragment.newInstance(), R.id.vpListFragment, false, InfoFragment.TAG);
                }
                return true;
            default:
                return false;
        }
    }


    @Override
    public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.actionListener:
                if (!(getSupportFragmentManager().findFragmentById(R.id.vpListFragment) instanceof HomeFragment)) {
                    AppUtils.popToFragmentIndex(getSupportFragmentManager(), 0);
                }
                break;
            case R.id.actionCategory:
                if (!(getSupportFragmentManager().findFragmentById(R.id.vpListFragment) instanceof HistoryFragment)) {
                    AppUtils.popToFragmentIndex(getSupportFragmentManager(), 0);
                }
                break;
            case R.id.actionInfo:
                if (!(getSupportFragmentManager().findFragmentById(R.id.vpListFragment) instanceof InfoFragment)) {
                    AppUtils.popToFragmentIndex(getSupportFragmentManager(), 0);
                }
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

}

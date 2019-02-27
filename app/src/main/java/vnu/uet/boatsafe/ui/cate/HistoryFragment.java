package vnu.uet.boatsafe.ui.cate;

import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import javax.inject.Inject;
import javax.inject.Named;

import vnu.uet.boatsafe.R;
import vnu.uet.boatsafe.di.component.ActivityComponent;
import vnu.uet.boatsafe.ui.base.BaseFragment;
import vnu.uet.boatsafe.ui.base.OnItemClickListener;
import vnu.uet.boatsafe.utils.AppUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryFragment extends BaseFragment implements HistoryFrMvpView, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = HistoryFragment.class.getSimpleName();


    @Inject
    HistoryFrMvpPresent<HistoryFrMvpView> presenter;
    @Inject
    @Named("2Column")
    GridLayoutManager gridLayoutManager;

    @BindView(R.id.rcvAllCate)
    RecyclerView rcvAllCate;
    @BindView(R.id.swrfCate)
    SwipeRefreshLayout swrfCate;

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int configView() {
        return R.layout.fragment_all_cate;
    }

    @Override
    protected void init(View v,Bundle savedInstanceState) {



    }

    @Override
    protected void initCreatedView(View v) {
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnbinder(ButterKnife.bind(this, v));
            presenter.onAttach(this);
        }

    }





    @Override
    public void onRefresh() {

    }
}

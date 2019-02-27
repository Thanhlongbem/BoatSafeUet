package vnu.uet.boatsafe.ui.info;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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

public class InfoFragment extends BaseFragment implements InfoFrMvpView, OnItemClickListener {

    public static final String TAG = InfoFragment.class.getSimpleName();

    @Inject
    InfoFrMvpPresent<InfoFrMvpView> presenter;

    @Inject
    @Named("vertical")
    LinearLayoutManager linearLayoutManager;

    @BindView(R.id.rcvPost)
    RecyclerView rcvPost;

    public static InfoFragment newInstance() {
        InfoFragment fragment = new InfoFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int configView() {
        return R.layout.fragment_info;
    }

    @Override
    protected void init(View v, Bundle savedInstanceState) {



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
    public void onClick(int position) {

    }
}

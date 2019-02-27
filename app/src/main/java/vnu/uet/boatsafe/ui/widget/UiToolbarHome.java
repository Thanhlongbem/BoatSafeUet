package vnu.uet.boatsafe.ui.widget;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import vnu.uet.boatsafe.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UiToolbarHome extends AppBarLayout implements BaseWidget {

    private View v;

    public UiToolbarHome(Context context) {
        super(context);
        init(context,null,-1);
    }

    public UiToolbarHome(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,-1);
    }

    @Override
    public void init(Context context, AttributeSet attrs, int defStyleAttr) {
        v = LayoutInflater.from(context).inflate(R.layout.ui_toolbar_home, this, true);
        ButterKnife.bind(this,v);
    }

    @OnClick(R.id.imgSearch)
    public void onSearchClick(View v){

    }
}

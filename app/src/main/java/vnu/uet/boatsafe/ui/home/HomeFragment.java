package vnu.uet.boatsafe.ui.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import vnu.uet.boatsafe.R;
import vnu.uet.boatsafe.di.component.ActivityComponent;
import vnu.uet.boatsafe.models.Addr;
import vnu.uet.boatsafe.ui.base.BaseFragment;

import butterknife.ButterKnife;
import vnu.uet.boatsafe.ui.location.GPSTracker;
import vnu.uet.boatsafe.utils.AppConstants;
import vnu.uet.boatsafe.utils.AppUtils;

public class HomeFragment extends BaseFragment implements HomeFrMvpView, OnMapReadyCallback , GoogleMap.OnCameraIdleListener, GoogleMap.OnCameraMoveStartedListener,  AppUtils.PermissionCallBack{

    public static final String TAG = HomeFragment.class.getSimpleName();

    @Inject
    HomeFrMvpPresent<HomeFrMvpView> presenter;
    @Inject
    @Named("vertical")
    LinearLayoutManager linearLayoutManager;

    @BindView(R.id.txtAddr)
    EditText txtAddr;

    private GoogleMap ggMap;
    private SupportMapFragment ggMapFr;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private vnu.uet.boatsafe.models.Location currentLocation;
    private Addr currentAddr;


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int configView() {
        return R.layout.fragment_home;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void init(View v, Bundle savedInstanceState) {
        GPSTracker gps = new GPSTracker(getContext());
        if(gps.canGetLocation()){
            currentLocation = new vnu.uet.boatsafe.models.Location();
            currentLocation.setLat(gps.getLatitude());
            currentLocation.setLng(gps.getLongitude());
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        ggMapFr = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        ggMapFr.getMapAsync(this);





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





    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.ggMap = googleMap;

        if(currentLocation != null){
            ggMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLocation.getLat(), currentLocation.getLng()), 20f));
            txtAddr.setText(String.valueOf(currentLocation.getLat()) + " : " + String.valueOf(currentLocation.getLng()));
        }

        ggMap.setOnCameraIdleListener(this);
        ggMap.setOnCameraMoveStartedListener(this);

        ggMap.getUiSettings().setMyLocationButtonEnabled(false);
        ggMap.getUiSettings().setMapToolbarEnabled(false);

        if(currentLocation == null){
            if (AppUtils.isPermisstionLocationGrant(getContext())) {
                ggMap.setMyLocationEnabled(true);
                presenter.getCurrentLocation(fusedLocationProviderClient);
            } else {
                AppUtils.requestPermission(getActivity(), AppConstants.LOCATION_PERMISSION, this);
            }
        }
    }



    @Override
    public void onGotCurrentLocation(Location location) {
        if (location != null) {
            presenter.onCameraMapIdle(getContext(), new LatLng(location.getLatitude(), location.getLongitude()));
            ggMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 16f));
        }
    }

    @Override
    public void onFailGotAddr() {

    }

    @Override
    public void onGotAddr(Addr addr) {
        this.currentAddr = addr;
        if (txtAddr != null) {
            txtAddr.setText(addr.getAddr());
        }
    }

    @Override
    public void onCameraIdle() {
        presenter.onCameraMapIdle(getContext(), ggMap.getCameraPosition().target);
    }

    @Override
    public void onCameraMoveStarted(int i) {
        txtAddr.setText("");
        presenter.onStartMoveMapCamera();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onPermissionsChecked(MultiplePermissionsReport report) {
        if (report.areAllPermissionsGranted()) {
            ggMap.setMyLocationEnabled(true);
            presenter.getCurrentLocation(fusedLocationProviderClient);
        }
    }

    @Override
    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
        token.continuePermissionRequest();
    }
}

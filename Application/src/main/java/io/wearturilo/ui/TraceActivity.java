package io.wearturilo.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
import android.view.MenuItem;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.wearturilo.R;
import io.wearturilo.WearturiloApp;
import io.wearturilo.common.model.Station;
import io.wearturilo.common.model.directions.Directions;
import io.wearturilo.network.DirectionApiClient;
import io.wearturilo.notification.DirectionNotification;
import io.wearturilo.provider.UserDataProvider;
import io.wearturilo.ui.adapter.TraceListAdapter;
import javax.inject.Inject;
import javax.inject.Named;
import retrofit.RestAdapter;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

public class TraceActivity extends Activity implements LocationListener {
    private static final String VETURILO_PACKAGE_NAME = "de.nextbike";

    @InjectView(R.id.direction_list)
    RecyclerView stationListRecyclerView;

    @Inject
    UserDataProvider userDataProvider;

    @Inject
    LocationManager locationManager;

    @Inject
    @Named("MAPS_REST")
    RestAdapter restAdapter;

    private TraceListAdapter traceListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trace_list_activity);
        WearturiloApp.component(this).inject(this);
        ButterKnife.inject(this);
        prepareList();

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        prepareLocalizationData(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, Time.SECOND * 20, 0, this);
        callForDirection();
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onMenuItemSelected(featureId, item);
    }

    private void prepareLocalizationData(Location location) {
        if (location != null) {
            userDataProvider.setLat(location.getLatitude());
            userDataProvider.setLng(location.getLongitude());
            callForDirection();
        }
    }

    private void prepareList() {
        final LinearLayoutManager lm = new LinearLayoutManager(this);
        stationListRecyclerView.setLayoutManager(lm);
        traceListAdapter = new TraceListAdapter();
        stationListRecyclerView.setAdapter(traceListAdapter);
    }

    void callForDirection(){
        if (userDataProvider.isStation()) {
            double lat = userDataProvider.getLat();
            double lng = userDataProvider.getLng();
            final Station station = userDataProvider.getSelectedStation();

            restAdapter.create(DirectionApiClient.class)
                    .directions("" + lat + "," + lng, "" + station.getLatPos() + "," + station.getLngPos(), "walking")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Directions>() {
                                   @Override
                                   public void call(Directions directions) {
                                        traceListAdapter.fillListByNewItem(directions);
                                       DirectionNotification.showDirectionNotification(TraceActivity.this, station, directions.getSteps().get(1));

                                   }
                               },
                            new Action1<Throwable>() {
                                @Override
                                public void call(Throwable throwable) {
                                    Log.e("Error", throwable.getMessage());
                                }
                            },
                            new Action0() {
                                @Override
                                public void call() {

                                }

                            }
                    );
        }
    }
    @OnClick(R.id.bike_button)
    protected void openVeturilo() {
        PackageManager pm = getPackageManager();
        if (isVeturiloInstalled()) {
            startActivity(new Intent(pm.getLaunchIntentForPackage(VETURILO_PACKAGE_NAME)));
        }
        else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + VETURILO_PACKAGE_NAME)));
        }

    }

    private boolean isVeturiloInstalled() {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(VETURILO_PACKAGE_NAME, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        prepareLocalizationData(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, Time.SECOND * 20, 0, this);
        if (location != null) {
            Log.e("test", "Location GET " + location.getLatitude() + "/" + location.getLongitude());
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public static class IntentFactory {
        public  static Intent forStart(Context context){
            return new Intent(context,TraceActivity.class);
        }
    }
}

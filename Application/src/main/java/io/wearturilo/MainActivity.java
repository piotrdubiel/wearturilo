package io.wearturilo;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.wearturilo.common.model.Station;
import io.wearturilo.common.model.StationList;
import io.wearturilo.common.model.directions.Directions;
import io.wearturilo.common.utils.DistanceUtils;
import io.wearturilo.network.DirectionApiClient;
import io.wearturilo.network.ListStationRequest;
import io.wearturilo.notification.DirectionNotification;
import io.wearturilo.provider.UserDataProvider;
import io.wearturilo.ui.BaseRetrofitActivity;
import io.wearturilo.ui.TraceActivity;
import io.wearturilo.ui.adapter.StationListAdapter;
import retrofit.RestAdapter;
import rx.functions.Action0;
import rx.functions.Action1;

public class MainActivity extends BaseRetrofitActivity<StationList> implements LocationListener {


    @InjectView(R.id.station_list)
    RecyclerView stationListRecyclerView;

    @Inject
    DistanceUtils distanceUtils;

    @Inject
    UserDataProvider userDataProvider;

    @Inject
    LocationManager locationManager;


    private StationListAdapter stationListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list_activity);
        WearturiloApp.component(this).inject(this);
        ButterKnife.inject(this);

        prepareList();
        spiceManager.execute(new ListStationRequest(), this);

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        prepareLocalizationData(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, Time.SECOND * 20, 0, this);
    }

    private void prepareLocalizationData(Location location) {
        if (location != null) {
            userDataProvider.setLat(location.getLatitude());
            userDataProvider.setLng(location.getLongitude());
        }
    }

    private void prepareList() {
        final LinearLayoutManager lm = new LinearLayoutManager(this);
        stationListRecyclerView.setLayoutManager(lm);
        stationListAdapter = new StationListAdapter();
        stationListRecyclerView.setAdapter(stationListAdapter);
        stationListAdapter.setOnStationItemClickListener(new StationListAdapter.OnStationItemClickListener() {
            @Override
            public void onStationItemClick(Station station) {
                Log.d("MAIN", station.getStationName());
                userDataProvider.setSelectedStation(station);
                startActivity(TraceActivity.IntentFactory.forStart(MainActivity.this));

            }
        });
    }


    @OnClick(R.id.refresh_btn)
    protected void requestForData() {
        spiceManager.execute(new ListStationRequest(), this);
    }

    @Override
    public void onRequestSuccess(StationList stationList) {
        stationListAdapter.fillListByNewItem(stationList, distanceUtils, userDataProvider);
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        Toast.makeText(this, "Couldn't download list of station", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        prepareLocalizationData(location);
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
}

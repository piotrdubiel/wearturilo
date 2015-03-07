package io.wearturilo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.octo.android.robospice.persistence.exception.SpiceException;
import io.wearturilo.common.model.StationList;
import io.wearturilo.network.ListStationRequest;
import io.wearturilo.ui.BaseRetrofitActivity;
import io.wearturilo.ui.adapter.StationListAdapter;

public class MainActivity extends BaseRetrofitActivity<StationList> {


    @InjectView(R.id.station_list)
    RecyclerView stationListRecyclerView;

    private  StationListAdapter stationListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list_activity);
        WearturiloApp.component(this).inject(this);
        ButterKnife.inject(this);
        prepareList();
        spiceManager.execute(new ListStationRequest(), this);
    }

    private void prepareList(){
        final LinearLayoutManager lm = new LinearLayoutManager(this);
        stationListRecyclerView.setLayoutManager(lm);
        stationListAdapter = new StationListAdapter();
        stationListRecyclerView.setAdapter(stationListAdapter);
    }


    @Override
    public void onRequestSuccess(StationList stationList) {
        stationListAdapter.fillListByNewItem(stationList);
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        Toast.makeText(this, "Couldn't download list of station", Toast.LENGTH_LONG).show();
    }
}

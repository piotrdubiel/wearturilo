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

public class MainActivity extends BaseRetrofitActivity<StationList> {


    @InjectView(R.id.station_list)
    RecyclerView stationListRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list_activity);
        WearturiloApp.component(this).inject(this);
        ButterKnife.inject(this);
        spiceManager.execute(new ListStationRequest(), this);
    }

    private void preapreList(){
        final LinearLayoutManager lm = new LinearLayoutManager(this);
        stationListRecyclerView.setLayoutManager(lm);
    }


    @Override
    public void onRequestSuccess(StationList stationList) {
        Toast.makeText(this, "Dziala", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {

    }
}

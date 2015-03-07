package io.wearturilo;

import android.os.Bundle;
import android.widget.Toast;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import io.wearturilo.common.model.StationList;
import io.wearturilo.network.ListStationRequest;
import io.wearturilo.ui.BaseRetrofitActivity;
import javax.inject.Inject;

public class MainActivity extends BaseRetrofitActivity<StationList>{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WearturiloApp.component(this).inject(this);
        spiceManager.execute(new ListStationRequest(),this);
    }

    @Inject
    protected SpiceManager spiceManager;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        WearturiloApp.component(this).inject(this);
//    }

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }


    @Override
    public void onRequestSuccess(StationList stationList) {
        Toast.makeText(this,"Dziala", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {

    }
}

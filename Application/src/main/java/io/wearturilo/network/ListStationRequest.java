package io.wearturilo.network;


import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import io.wearturilo.common.model.StationList;

public class ListStationRequest extends RetrofitSpiceRequest<StationList, StationListClient> {

    public ListStationRequest() {
        super(StationList.class, StationListClient.class);
    }

    @Override
    public StationList loadDataFromNetwork() throws Exception {
        return getService().getStationList();
    }
}

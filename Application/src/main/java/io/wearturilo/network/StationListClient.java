package io.wearturilo.network;

import io.wearturilo.common.model.StationList;
import retrofit.http.GET;

public interface StationListClient {

    @GET("/stationList")
    StationList getStationList();

}

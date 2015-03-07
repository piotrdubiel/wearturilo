package io.wearturilo.common.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class StationList {

    interface  Metadata {
        String STATION_LIST = "stationList";
    }

    @SerializedName(Metadata.STATION_LIST)
    List<Station> stations;
}

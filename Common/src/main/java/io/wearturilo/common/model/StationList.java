package io.wearturilo.common.model;

import com.google.gson.annotations.SerializedName;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class StationList implements Iterable<Station>{

    interface  Metadata {
        String STATION_LIST = "stationList";
    }

    @SerializedName(Metadata.STATION_LIST)
    List<Station> stations;

    @Override
    public Iterator<Station> iterator() {
        if(stations != null){
            return stations.iterator();
        }
        return Collections.emptyIterator();
    }
}

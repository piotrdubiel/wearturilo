package io.wearturilo.common.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Station {

    interface Metadata {
        String STATION_NAME = "stationName";
        String STATION_NUMBER = "stationNumber";
        String LAT = "lat";
        String LNG = "lng";
        String BIKE_NUMBER = "bikeNumber";
        String RACK_NUMBER = "rackNumber";
        String BIKE_IDS = "bikeIds";

    }

    @SerializedName(Metadata.STATION_NAME)
    String stationName;

    @SerializedName(Metadata.STATION_NUMBER)
    String stationNumber;

    @SerializedName(Metadata.LAT)
    double latPos;

    @SerializedName(Metadata.LNG)
    double lngPos;

    @SerializedName(Metadata.BIKE_NUMBER)
    BikeNumber bikeNumber;

    @SerializedName(Metadata.RACK_NUMBER)
    int rackNumber;

    @SerializedName(Metadata.BIKE_IDS)
    List<String> bikeIds;

    public String getStationName() {
        return stationName;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public double getLatPos() {
        return latPos;
    }

    public double getLngPos() {
        return lngPos;
    }

    public BikeNumber getBikeNumber() {
        return bikeNumber != null ? bikeNumber : BikeNumber.NONE;
    }

    public int getRackNumber() {
        return rackNumber;
    }
}

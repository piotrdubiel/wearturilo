package io.wearturilo.provider;

import io.wearturilo.common.model.Station;

public class UserDataProvider {

    double lat = 52.2206505;
    double lng = 21.00842;

    Station selectedStation;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public boolean isStation(){
        return selectedStation != null;
    }

    public Station getSelectedStation() {
        return selectedStation;
    }

    public void setSelectedStation(Station selectedStation) {
        this.selectedStation = selectedStation;
    }
}

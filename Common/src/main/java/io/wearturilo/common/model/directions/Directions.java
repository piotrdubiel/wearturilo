package io.wearturilo.common.model.directions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Directions {
    @SerializedName("routes")
    public List<Route> routes;

    public List<Step> getSteps() {
        try {
            return routes.get(0).legs.get(0).steps;
        }
        catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }
}

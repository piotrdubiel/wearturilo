package io.wearturilo.common.model.directions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Route {
    @SerializedName("legs")
    public List<Leg> legs;
}

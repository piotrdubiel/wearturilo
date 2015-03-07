package io.wearturilo.common.model.directions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Leg {
    @SerializedName("steps")
    List<Step> steps;
}

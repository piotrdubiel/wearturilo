package io.wearturilo.common.model.directions;

import com.google.gson.annotations.SerializedName;

public class Step {
    @SerializedName("maneuver")
    public Direction maneuver;

    @SerializedName("html_instructions")
    public String instructions;
}

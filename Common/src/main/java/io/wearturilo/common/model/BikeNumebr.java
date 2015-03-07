package io.wearturilo.common.model;

import com.google.gson.annotations.SerializedName;

public enum BikeNumebr {

    @SerializedName(Metadata.NONE)
    NONE,
    @SerializedName(Metadata.ONE)
    ONE,
    @SerializedName(Metadata.TWO)
    TWO,
    @SerializedName(Metadata.THREE)
    THREE,
    @SerializedName(Metadata.FOUR)
    FOUR,
    @SerializedName(Metadata.MORE)
    MORE;

    interface Metadata {
        String NONE = "NONE";
        String ONE = "ONE";
        String TWO = "TWO";
        String THREE = "THREE";
        String FOUR = "FOUR";
        String MORE = "MORE";

    }
}

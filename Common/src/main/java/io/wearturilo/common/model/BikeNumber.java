package io.wearturilo.common.model;

import com.google.gson.annotations.SerializedName;

public enum BikeNumber {

    @SerializedName(Metadata.NONE)
    NONE("0"),
    @SerializedName(Metadata.ONE)
    ONE("1"),
    @SerializedName(Metadata.TWO)
    TWO("2"),
    @SerializedName(Metadata.THREE)
    THREE("3"),
    @SerializedName(Metadata.FOUR)
    FOUR("4"),
    @SerializedName(Metadata.MORE)
    MORE("5+");

    private final String number;

    private BikeNumber(String number){
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    interface Metadata {
        String NONE = "NONE";
        String ONE = "ONE";
        String TWO = "TWO";
        String THREE = "THREE";
        String FOUR = "FOUR";
        String MORE = "MORE";

    }
}

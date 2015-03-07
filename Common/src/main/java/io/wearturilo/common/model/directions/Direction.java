package io.wearturilo.common.model.directions;

import com.google.gson.annotations.SerializedName;

import io.wearturilo.common.R;

public enum Direction {
    @SerializedName("turn-left")
    LEFT(R.drawable.ic_left_36dp),
    @SerializedName("turn-right")
    RIGHT(R.drawable.ic_right_36dp),
    @SerializedName("straight")
    STRAIGHT(R.drawable.ic_straight_36dp);
    private final int direction;

    private Direction(int direction) {

        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}

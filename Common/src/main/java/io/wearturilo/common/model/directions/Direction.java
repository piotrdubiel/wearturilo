package io.wearturilo.common.model.directions;

import io.wearturilo.common.R;

public enum Direction {
    LEFT(R.drawable.ic_left_36dp),
    RIGHT(R.drawable.ic_right_36dp),
    STRAIGHT(R.drawable.ic_straight_36dp),
    TURN_BACK(R.drawable.ic_turn_back_36dp);

    private final int direction;

    private Direction(int direction) {

        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}

package io.wearturilo.notification;

import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.text.Html;

import io.wearturilo.R;
import io.wearturilo.common.model.Station;
import io.wearturilo.common.model.directions.Step;

public class DirectionNotification {
    public static void showDirectionNotification(Context context, Station station, Direction direction) {
        int directionIcon;
        if (direction == Direction.LEFT) {
            directionIcon = R.drawable.ic_left_36dp;
        }
        else if (direction == Direction.RIGHT) {
            directionIcon = R.drawable.ic_right_36dp;
        }
        else if (direction == Direction.STRAIGHT) {
            directionIcon = R.drawable.ic_straight_36dp;
        }
        else if (direction == Direction.TURN_BACK) {
            directionIcon = R.drawable.ic_turn_back_36dp;
        }
        else {
            directionIcon = R.drawable.ic_directions_bike_black_36dp;
        }


        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_directions_bike_black_48dp)
                .setContentTitle(station.getStationName())
                .setContentText(String.format("%.1f km", station.getDistanceFromUser()))
                .extend(new NotificationCompat.WearableExtender()
                        .setHintHideIcon(true)
                        .setCustomSizePreset(Notification.WearableExtender.SIZE_LARGE)
                        .setContentIcon(directionIcon))
                .build();

        NotificationManagerCompat.from(context).notify(1, notification);
    }

    public static void showDirectionNotification(Context context, Station station, Step step) {
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_directions_bike_black_48dp)
                .setContentTitle(station.getStationName())
                .setContentText(Html.fromHtml(step.instructions))
                .extend(new NotificationCompat.WearableExtender()
                        .setHintHideIcon(true)
                        .setCustomSizePreset(Notification.WearableExtender.SIZE_FULL_SCREEN)
                        .setContentIcon(R.drawable.ic_straight_36dp))
                .build();

        NotificationManagerCompat.from(context).notify(1, notification);
    }
}

package io.wearturilo.notification;

import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.Gravity;

import io.wearturilo.R;
import io.wearturilo.common.model.Station;

public class DirectionNotification {
    public static void showDirectionNotification(Context context, Station station) {
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_directions_bike_black_48dp)
                .setContentTitle(station.getStationName())
                .setContentText(String.format("%.1f km", station.getDistanceFromUser()))
                .extend(new NotificationCompat.WearableExtender()
                        .setHintHideIcon(true)
                        .setContentIcon(R.drawable.ic_directions_bike_black_48dp)
                        .setContentIconGravity(Gravity.START))
                .build();

        NotificationManagerCompat.from(context).notify(1, notification);
    }
}

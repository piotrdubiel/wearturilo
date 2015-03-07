package io.wearturilo;


import android.app.Application;
import android.content.Context;
import io.wearturilo.dependencyinjection.WearturiloComponent;

public class WearturiloApp extends Application {


    WearturiloComponent component;


    @Override
    public void onCreate() {
        super.onCreate();
        component = WearturiloComponent.Initializer.init(this);
        component.inject(this);
    }

    public static WearturiloComponent component(Context context) {
        return ((WearturiloApp) context.getApplicationContext()).component;
    }
}

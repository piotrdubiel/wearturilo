package io.wearturilo.dependencyinjection;

import android.content.Context;
import android.location.LocationManager;
import dagger.Module;
import dagger.Provides;

@Module
public class SystemModule {

    private final  Context context;

    public SystemModule(Context context){
        this.context = context;
    }

    @Provides
    LocationManager provideLocationManager(){
           return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

}

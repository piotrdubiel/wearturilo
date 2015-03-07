package io.wearturilo.dependencyinjection;

import dagger.Module;
import dagger.Provides;
import io.wearturilo.common.utils.DistanceUtils;

@Module
public class UtilsModule {

    @Provides
    DistanceUtils provideDistanceUtils(){
        return new DistanceUtils();
    }
}

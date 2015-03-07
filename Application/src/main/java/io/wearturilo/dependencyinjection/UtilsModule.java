package io.wearturilo.dependencyinjection;

import dagger.Module;
import dagger.Provides;
import io.wearturilo.common.utils.DistanceUtils;
import io.wearturilo.provider.UserDataProvider;
import javax.inject.Singleton;

@Module
public class UtilsModule {

    @Singleton
    @Provides
    DistanceUtils provideDistanceUtils(){
        return new DistanceUtils();
    }

    @Singleton
    @Provides
    UserDataProvider provideUserDataProvider(){
        return new UserDataProvider();
    }
}

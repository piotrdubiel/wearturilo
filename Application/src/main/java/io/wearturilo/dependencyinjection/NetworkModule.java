package io.wearturilo.dependencyinjection;

import com.google.gson.Gson;
import com.octo.android.robospice.SpiceManager;
import dagger.Module;
import dagger.Provides;
import io.wearturilo.BuildConfig;
import io.wearturilo.network.WearturiloRetrofitSpiceService;
import javax.inject.Named;
import javax.inject.Singleton;
import retrofit.RestAdapter;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

@Module
public class NetworkModule {

    @Provides
    SpiceManager provideSpiceManager() {
        return new SpiceManager(WearturiloRetrofitSpiceService.class);
    }


    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    GsonConverter provideGsonConverter(Gson gson) {
        return new GsonConverter(gson);
    }

    private RestAdapter.Builder createRestAdapterBuilder(Converter gsonConverter) {
        return new RestAdapter.Builder()
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .setConverter(gsonConverter);
    }

    @Provides
    @Singleton
    @Named("MAIN")
    RestAdapter.Builder provideMainRestAdapterBuilder(GsonConverter gsonConverter) {
        return createRestAdapterBuilder(gsonConverter)
                .setEndpoint(io.wearturilo.common.BuildConfig.URL);
    }

    @Provides
    @Singleton
    @Named("MAPS")
    RestAdapter.Builder provideMapsRestAdapterBuilder(GsonConverter gsonConverter) {
        return createRestAdapterBuilder(gsonConverter)
                .setEndpoint(io.wearturilo.common.BuildConfig.URL_MAPS);
    }


    @Provides
    @Singleton
    @Named("MAIN_REST")
    RestAdapter provideRestAdapter(@Named("MAIN") RestAdapter.Builder builder) {
        return builder.build();
    }

    @Provides
    @Named("MAPS_REST")
    RestAdapter provideMapsRestAdapter(@Named("MAPS") RestAdapter.Builder builder) {
        return builder.build();
    }
}

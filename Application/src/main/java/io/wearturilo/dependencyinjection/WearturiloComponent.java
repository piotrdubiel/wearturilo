package io.wearturilo.dependencyinjection;


import dagger.Component;
import io.wearturilo.MainActivity;
import io.wearturilo.WearturiloApp;
import io.wearturilo.network.WearturiloRetrofitSpiceService;
import io.wearturilo.ui.BaseRetrofitActivity;
import javax.inject.Singleton;

@Singleton
@Component(modules = {NetworkModule.class})
public interface WearturiloComponent {

    public final static class Initializer {

        public static WearturiloComponent init(WearturiloApp app) {
            return Dagger_WearturiloComponent.builder()
                    .networkModule(new NetworkModule())
                    .build();
        }

    }

    void inject(WearturiloApp wearturiloApp);

    void inject(WearturiloRetrofitSpiceService wearturiloRetrofitSpiceService);

    void inject(MainActivity mainActivity);

}

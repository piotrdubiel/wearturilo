package io.wearturilo.dependencyinjection;


import dagger.Component;
import io.wearturilo.MainActivity;
import io.wearturilo.WearturiloApp;
import io.wearturilo.network.WearturiloRetrofitSpiceService;
import io.wearturilo.ui.TraceActivity;
import javax.inject.Singleton;

@Singleton
@Component(modules = {NetworkModule.class, UtilsModule.class, SystemModule.class})
public interface WearturiloComponent {

    public final static class Initializer {

        public static WearturiloComponent init(WearturiloApp app) {
            return Dagger_WearturiloComponent.builder()
                    .systemModule(new SystemModule(app))
                    .build();
        }

    }

    void inject(WearturiloApp wearturiloApp);

    void inject(WearturiloRetrofitSpiceService wearturiloRetrofitSpiceService);

    void inject(MainActivity mainActivity);

    void inject(TraceActivity traceActivity);

}

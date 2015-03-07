package io.wearturilo.network;

import android.app.Application;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;
import io.wearturilo.WearturiloApp;
import javax.inject.Inject;
import retrofit.RestAdapter;

public class WearturiloRetrofitSpiceService extends RetrofitGsonSpiceService {


    @Inject
    RestAdapter.Builder restAdapterBuilder;

    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(StationListClient.class);
    }

    @Override
    protected String getServerUrl() {
        throw new UnsupportedOperationException("Shouldn't call");
    }


    @Override
    protected RestAdapter.Builder createRestAdapterBuilder() {
        return restAdapterBuilder;
    }

    @Override
    public int getThreadCount() {
        return 3;
    }

    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        WearturiloApp.component(this).inject(this);
        CacheManager cacheManager = new CacheManager();
        return cacheManager;
    }
}

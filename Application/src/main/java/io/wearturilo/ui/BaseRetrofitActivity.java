package io.wearturilo.ui;

import android.app.Activity;
import android.os.Bundle;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.request.listener.RequestListener;
import io.wearturilo.WearturiloApp;
import javax.inject.Inject;

public abstract class BaseRetrofitActivity<RESULT> extends Activity implements RequestListener<RESULT> {

    @Inject
    protected SpiceManager spiceManager;

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }
}

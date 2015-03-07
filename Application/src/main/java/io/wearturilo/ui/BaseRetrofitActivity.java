package io.wearturilo.ui;

import android.app.Activity;
import android.os.Bundle;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.request.listener.RequestListener;
import io.wearturilo.WearturiloApp;
import javax.inject.Inject;

public abstract class BaseRetrofitActivity<RESULT> extends Activity implements RequestListener<RESULT> {


}

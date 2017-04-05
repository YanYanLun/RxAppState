package com.jenzz.appstate.sample;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.jenzz.appstate.AppState;
import com.jenzz.appstate.AppStateListener;
import com.jenzz.appstate.AppStateMonitor;
import com.jenzz.appstate.adapter.rxjava.RxAppStateMonitor;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import rx.functions.Action1;

import static android.widget.Toast.LENGTH_LONG;
import static com.jenzz.appstate.AppState.BACKGROUND;
import static com.jenzz.appstate.AppState.FOREGROUND;

public class SampleApplication extends Application {

  private static final String TAG = "AppStateMonitor";

  private AppStateMonitor appStateMonitor;

  @Override
  public void onCreate() {
    super.onCreate();

    // RxJava sample
    RxAppStateMonitor.monitor(this).subscribe(new Action1<AppState>() {
      @Override
      public void call(AppState appState) {
        // Hocus, Pocus, Abracadabra!
      }
    });

    // RxJava2 sample
    com.jenzz.appstate.adapter.rxjava2.RxAppStateMonitor.monitor(this).subscribe(new Consumer<AppState>() {
      @Override
      public void accept(@NonNull AppState appState) throws Exception {
        // Hocus, Pocus, Abracadabra!
      }
    });

    // Callback sample
    appStateMonitor = AppStateMonitor.create(this);
    appStateMonitor.addListener(new SampleAppStateListener());
    appStateMonitor.start();
  }

  public AppStateMonitor getAppStateMonitor() {
    return appStateMonitor;
  }

  private class SampleAppStateListener implements AppStateListener {

    @Override
    public void onAppDidEnterForeground() {
      logAndToast(FOREGROUND);
    }

    @Override
    public void onAppDidEnterBackground() {
      logAndToast(BACKGROUND);
    }
  }

  private void logAndToast(AppState appState) {
    Log.d(TAG, appState.toString());
    Toast.makeText(SampleApplication.this, TAG + ": " + appState, LENGTH_LONG).show();
  }
}
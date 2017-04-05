package com.jenzz.appstate.adapter.rxjava2;

import android.app.Application;
import android.support.annotation.NonNull;

import com.jenzz.appstate.AppState;
import com.jenzz.appstate.adapter.rxjava2.internal.AppStateObservableOnSubscribe;
import com.jenzz.appstate.internal.DefaultAppStateRecognizer;

import io.reactivex.Observable;

public final class RxAppStateMonitor {

  /**
   * Creates a new {@link Observable} that emits {@link AppState} items
   * whenever the app goes into background and comes back into foreground.
   *
   * @param app the application to monitor for app state changes
   * @return a new {@link Observable} that emits app state changes
   */
  @NonNull
  public static Observable<AppState> monitor(@NonNull Application app) {
    return Observable.create(new AppStateObservableOnSubscribe(new DefaultAppStateRecognizer(app)));
  }
}

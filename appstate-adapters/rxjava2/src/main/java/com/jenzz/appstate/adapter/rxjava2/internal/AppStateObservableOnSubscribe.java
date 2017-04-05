package com.jenzz.appstate.adapter.rxjava2.internal;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.jenzz.appstate.AppState;
import com.jenzz.appstate.AppStateListener;
import com.jenzz.appstate.internal.AppStateRecognizer;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;

import static android.support.annotation.RestrictTo.Scope.LIBRARY;
import static com.jenzz.appstate.AppState.BACKGROUND;
import static com.jenzz.appstate.AppState.FOREGROUND;

@RestrictTo(LIBRARY)
public final class AppStateObservableOnSubscribe implements ObservableOnSubscribe<AppState> {

  @NonNull private final AppStateRecognizer recognizer;

  public AppStateObservableOnSubscribe(@NonNull AppStateRecognizer recognizer) {
    this.recognizer = recognizer;
  }

  @Override
  public void subscribe(@NonNull final ObservableEmitter<AppState> appStateEmitter) throws Exception {
    final AppStateListener appStateListener = new AppStateListener() {
      @Override
      public void onAppDidEnterForeground() {
        appStateEmitter.onNext(FOREGROUND);
      }

      @Override
      public void onAppDidEnterBackground() {
        appStateEmitter.onNext(BACKGROUND);
      }
    };

    appStateEmitter.setCancellable(new Cancellable() {
      @Override public void cancel() throws Exception {
        recognizer.removeListener(appStateListener);
        recognizer.stop();
      }
    });

    recognizer.addListener(appStateListener);
    recognizer.start();
  }
}

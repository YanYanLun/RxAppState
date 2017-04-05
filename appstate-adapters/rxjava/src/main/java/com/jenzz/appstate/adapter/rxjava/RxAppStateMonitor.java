package com.jenzz.appstate.adapter.rxjava;

import android.app.Application;
import android.support.annotation.NonNull;

import com.jenzz.appstate.AppState;
import com.jenzz.appstate.adapter.rxjava.internal.AppStateEmitter;
import com.jenzz.appstate.internal.DefaultAppStateRecognizer;

import rx.Observable;

import static rx.Emitter.BackpressureMode.LATEST;

public final class RxAppStateMonitor {

    /**
     * Creates a new {@link Observable} that emits {@link AppState} items
     * whenever the app goes into background and comes back into foreground.
     *
     * @return a new {@link Observable}
     */
    @NonNull
    public static Observable<AppState> monitor(@NonNull Application app) {
        return Observable.fromEmitter(new AppStateEmitter(new DefaultAppStateRecognizer(app)), LATEST);
    }
}

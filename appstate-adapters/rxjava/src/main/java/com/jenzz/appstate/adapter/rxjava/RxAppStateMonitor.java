package com.jenzz.appstate.adapter.rxjava;

import android.app.Application;
import android.support.annotation.NonNull;

import com.jenzz.appstate.AppState;
import com.jenzz.appstate.adapter.rxjava.internal.AppStateEmitter;
import com.jenzz.appstate.internal.DefaultAppStateRecognizer;

import rx.Emitter;
import rx.Observable;

import static rx.Emitter.BackpressureMode.NONE;

public final class RxAppStateMonitor {

    /**
     * Calls {@link #monitor(Application, rx.Emitter.BackpressureMode)}
     * with {@link rx.Emitter.BackpressureMode#NONE Emitter.BackpressureMode.NONE}.
     *
     * @param app the application to monitor for app state changes
     * @return a new {@link Observable} that emits app state changes
     */
    @NonNull
    public static Observable<AppState> monitor(@NonNull Application app) {
        return monitor(app, NONE);
    }

    /**
     * Creates a new {@link Observable} that emits {@link AppState} items
     * whenever the app goes into background and comes back into foreground.
     *
     * @param app the application to monitor for app state changes
     * @param backpressureMode the backpressure mode to apply if the downstream Subscriber doesn't request (fast) enough
     * @return a new {@link Observable} that emits app state changes
     */
    @NonNull
    public static Observable<AppState> monitor(@NonNull Application app, @NonNull
        Emitter.BackpressureMode backpressureMode) {
        return Observable.create(new AppStateEmitter(new DefaultAppStateRecognizer(app)), backpressureMode);
    }
}

package com.jenzz.appstate.adapter.rxjava;

import com.jenzz.appstate.AppState;
import com.jenzz.appstate.FakeApplication;

import org.junit.Test;

import rx.observers.TestSubscriber;

import static com.jenzz.appstate.AppState.BACKGROUND;
import static com.jenzz.appstate.AppState.FOREGROUND;

public class RxAppStateMonitorTest {

    @Test
    public void emitsAppStates() {
        FakeApplication fakeApplication = new FakeApplication();
        TestSubscriber<AppState> subscriber = TestSubscriber.create();
        RxAppStateMonitor.monitor(fakeApplication).subscribe(subscriber);

        fakeApplication.goForeground();
        fakeApplication.goBackground();

        subscriber.assertValues(FOREGROUND, BACKGROUND);
        subscriber.assertNoTerminalEvent();
    }
}
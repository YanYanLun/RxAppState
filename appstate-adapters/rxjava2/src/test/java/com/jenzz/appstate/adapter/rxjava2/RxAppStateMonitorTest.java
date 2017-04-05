package com.jenzz.appstate.adapter.rxjava2;

import com.jenzz.appstate.AppState;
import com.jenzz.appstate.FakeApplication;

import io.reactivex.observers.TestObserver;
import org.junit.Test;

import static com.jenzz.appstate.AppState.BACKGROUND;
import static com.jenzz.appstate.AppState.FOREGROUND;

public class RxAppStateMonitorTest {

  @Test
  public void emitsAppStates() {
    FakeApplication fakeApplication = new FakeApplication();
    TestObserver<AppState> subscriber = TestObserver.create();
    RxAppStateMonitor.monitor(fakeApplication).subscribe(subscriber);

    fakeApplication.goForeground();
    fakeApplication.goBackground();

    subscriber.assertValues(FOREGROUND, BACKGROUND);
    subscriber.assertNotTerminated();
  }
}
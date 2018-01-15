package com.jenzz.appstate.internal;

import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks2;
import android.content.IntentFilter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultAppStateRecognizerSetupTest {

  @Mock private Application mockApplication;

  private DefaultAppStateRecognizer recognizer;

  @Before
  public void setUp() {
    initMocks(this);

    recognizer = new DefaultAppStateRecognizer(mockApplication);
  }

  @Test
  public void registersCallbacks() {
    recognizer.start();

    verifyStarted();
  }

  @Test
  public void unregistersCallbacks() {
    recognizer.start();
    recognizer.stop();

    verifyStopped();
  }

  @Test
  public void ignoresMultipleCallsToStart() {
    recognizer.start();
    recognizer.start(); // should be ignored as it's already started

    verifyStarted();
  }

  @Test
  public void ignoresMultipleCallsToStop() {
    recognizer.stop(); // should be ignored as it hasn't been started yet

    verifyZeroInteractions(mockApplication);
  }

  private void verifyStarted() {
    verify(mockApplication).registerActivityLifecycleCallbacks(any(ActivityLifecycleCallbacks.class));
    verify(mockApplication).registerComponentCallbacks(any(ComponentCallbacks2.class));
    verify(mockApplication).registerReceiver(any(BroadcastReceiver.class), any(IntentFilter.class));
  }

  private void verifyStopped() {
    verify(mockApplication).unregisterActivityLifecycleCallbacks(any(ActivityLifecycleCallbacks.class));
    verify(mockApplication).unregisterComponentCallbacks(any(ComponentCallbacks2.class));
    verify(mockApplication).unregisterReceiver(any(BroadcastReceiver.class));
  }
}

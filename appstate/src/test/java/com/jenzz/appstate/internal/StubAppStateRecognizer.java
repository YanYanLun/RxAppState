package com.jenzz.appstate.internal;

import android.support.annotation.NonNull;

import com.jenzz.appstate.AppState;
import com.jenzz.appstate.AppStateListener;

import java.util.ArrayList;
import java.util.List;

import static com.jenzz.appstate.AppState.BACKGROUND;

public class StubAppStateRecognizer implements AppStateRecognizer {

  @NonNull private final List<AppStateListener> listeners = new ArrayList<>();

  @NonNull private AppState appState = BACKGROUND;
  private boolean isStarted;

  @Override
  public void addListener(@NonNull AppStateListener listener) {
    listeners.add(listener);
  }

  @Override
  public void removeListener(@NonNull AppStateListener listener) {
    listeners.remove(listener);
  }

  @Override
  public void start() {
    isStarted = true;
  }

  @Override
  public void stop() {
    isStarted = false;
  }

  @NonNull
  @Override
  public AppState getAppState() {
    return appState;
  }

  public void setAppState(@NonNull AppState appState) {
    this.appState = appState;
  }

  @NonNull
  public List<AppStateListener> getListeners() {
    return listeners;
  }

  public boolean isStarted() {
    return isStarted;
  }

  public void notifyAppDidEnterForeground() {
    for (AppStateListener listener : listeners) {
      listener.onAppDidEnterForeground();
    }
  }

  public void notifyAppDidEnterBackground() {
    for (AppStateListener listener : listeners) {
      listener.onAppDidEnterBackground();
    }
  }
}

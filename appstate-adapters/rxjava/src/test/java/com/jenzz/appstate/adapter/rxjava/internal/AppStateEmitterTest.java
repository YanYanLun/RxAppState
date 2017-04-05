package com.jenzz.appstate.adapter.rxjava.internal;

import android.support.annotation.NonNull;

import com.jenzz.appstate.AppState;
import com.jenzz.appstate.AppStateListener;
import com.jenzz.appstate.internal.AppStateRecognizer;
import com.jenzz.appstate.internal.StubAppStateRecognizer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import rx.Emitter;
import rx.Observable;
import rx.functions.Cancellable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static rx.Emitter.BackpressureMode.NONE;

public class AppStateEmitterTest {

  @NonNull private final StubAppStateRecognizer stubRecognizer = new StubAppStateRecognizer();
  @NonNull private final AppStateEmitter emitter = new AppStateEmitter(stubRecognizer);

  @Mock private Emitter<AppState> mockEmitter;
  @Mock private AppStateRecognizer mockAppStateRecognizer;

  private Observable<AppState> emitterObservable;

  @Before
  public void setUp() {
    initMocks(this);

    emitter.call(mockEmitter);
    emitterObservable = Observable.fromEmitter(new AppStateEmitter(mockAppStateRecognizer), NONE);
  }

  @Test
  public void emitsForeground() {
    stubRecognizer.notifyAppDidEnterForeground();

    verify(mockEmitter).onNext(AppState.FOREGROUND);
  }

  @Test
  public void emitsBackground() {
    stubRecognizer.notifyAppDidEnterBackground();

    verify(mockEmitter).onNext(AppState.BACKGROUND);
  }

  @Test
  public void setsCancellation() {
    verify(mockEmitter).setCancellation(any(Cancellable.class));
  }

  @Test
  public void addsListenerAndStartsRecognitionOnSubscribe() {
    emitterObservable.subscribe();

    verify(mockAppStateRecognizer).addListener(any(AppStateListener.class));
    verify(mockAppStateRecognizer).start();
  }

  @Test
  public void removesListenerAndStopsRecognitionOnUnsubscribe() {
    emitterObservable.subscribe().unsubscribe();

    verify(mockAppStateRecognizer).removeListener(any(AppStateListener.class));
    verify(mockAppStateRecognizer).stop();
  }
}
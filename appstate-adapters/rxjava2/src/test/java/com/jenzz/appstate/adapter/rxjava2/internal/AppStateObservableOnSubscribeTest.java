package com.jenzz.appstate.adapter.rxjava2.internal;

import android.support.annotation.NonNull;

import com.jenzz.appstate.AppState;
import com.jenzz.appstate.AppStateListener;
import com.jenzz.appstate.internal.AppStateRecognizer;
import com.jenzz.appstate.internal.StubAppStateRecognizer;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.functions.Cancellable;

import static com.jenzz.appstate.AppState.BACKGROUND;
import static com.jenzz.appstate.AppState.FOREGROUND;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class AppStateObservableOnSubscribeTest {

  @NonNull private final StubAppStateRecognizer stubRecognizer = new StubAppStateRecognizer();
  @NonNull private final AppStateObservableOnSubscribe observable = new AppStateObservableOnSubscribe(stubRecognizer);

  @Mock private ObservableEmitter<AppState> mockEmitter;
  @Mock private AppStateRecognizer mockAppStateRecognizer;

  private Observable<AppState> emitterObservable;

  @Before
  public void setUp() throws Exception {
    initMocks(this);

    observable.subscribe(mockEmitter);
    emitterObservable = Observable.create(new AppStateObservableOnSubscribe(mockAppStateRecognizer));
  }

  @Test
  public void emitsForeground() {
    stubRecognizer.notifyAppDidEnterForeground();

    verify(mockEmitter).onNext(FOREGROUND);
  }

  @Test
  public void emitsBackground() {
    stubRecognizer.notifyAppDidEnterBackground();

    verify(mockEmitter).onNext(BACKGROUND);
  }

  @Test
  public void setsCancellable() {
    verify(mockEmitter).setCancellable(any(Cancellable.class));
  }

  @Test
  public void addsListenerAndStartsRecognitionOnSubscribe() {
    emitterObservable.subscribe();

    verify(mockAppStateRecognizer).addListener(any(AppStateListener.class));
    verify(mockAppStateRecognizer).start();
  }

  @Test
  public void removesListenerAndStopsRecognitionOnDispose() {
    emitterObservable.subscribe().dispose();

    verify(mockAppStateRecognizer).removeListener(any(AppStateListener.class));
    verify(mockAppStateRecognizer).stop();
  }
}
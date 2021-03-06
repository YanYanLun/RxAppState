RxJava Adapter
==============
An `Adapter` for adapting [RxJava 1.x](https://github.com/ReactiveX/RxJava/tree/1.x) types.

Usage
-----
You most probably want to monitor for app state changes in your application's `onCreate()` method
in which case you also don't need to worry about unsubscribing from the `Observable`.
Remember that if you subscribe in an `Activity` or a `Fragment`, don't forget to unsubscribe to avoid memory leaks.

```java
RxAppStateMonitor.monitor(this).subscribe(new Action1<AppState>() {
    @Override
    public void call(AppState appState) {
        switch (appState) {
            case FOREGROUND:
                // Hocus Pocus...
                break;
            case BACKGROUND:
                // Abracadabra!
                break;
        }
    }
});
```

Download
--------
Grab it via Gradle:

```groovy
dependencies {
  compile 'com.jenzz.appstate:adapter-rxjava:3.0.1'
}
```
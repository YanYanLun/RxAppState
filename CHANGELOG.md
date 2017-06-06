Change Log
==========

Version 3.0.0 *(2017/04/11)*
----------------------------
* Remove hard dependency on RxJava by abstracting out RxJava adapter ([#4](https://github.com/jenzz/RxAppState/issues/4))
* Add support for RxJava2 ([#13](https://github.com/jenzz/RxAppState/issues/13))

Version 2.0.1 *(2017/01/31)*
----------------------------
* Fix missing call to `AppStateRecognizer.start()` when using RX monitor ([#12](https://github.com/jenzz/RxAppState/issues/12))

Version 2.0.0 *(2016/12/01)*
----------------------------
* Internal refactor/rewrite to create a more stable, testable architecture
* API changes:
    * `RxAppState` is now called `RxAppStateMonitor`
    * `AppStateMonitor` is the new interface returned by factory methods
    * Minor renamings (e.g. `startMonitoring()` / `stopMonitoring()` to `start()` / `stop()`)
* **Behaviour change:** A `FOREGROUND` event is now fired on initialisation ([#1](https://github.com/jenzz/RxAppState/issues/1))

Version 1.1.0 *(2016/06/23)*
----------------------------
* Handle BACKGROUND / FOREGROUND events when toggling screen on/off ([#3](https://github.com/jenzz/RxAppState/issues/3))

Version 1.0.1 *(2016/05/02)*
----------------------------
* Bugfix: Missing default app state ([#2](https://github.com/jenzz/RxAppState/issues/2))

Version 1.0.0 *(2016/03/06)*
----------------------------
* Initial release
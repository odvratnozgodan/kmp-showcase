# :composeApp

This module is the main application module for each platform (Android, iOS, Web). It is responsible for:

* Initializing the application.
* Setting up the main application screen and navigation.
* Providing platform-specific implementations and integrations.

## Dependencies

This module uses the following dependencies:

* **[Compose Multiplatform](https://developer.android.com/jetpack/compose):** For building the user interface.
* **[Kotlinx Coroutines](https://kotlinlang.org/docs/coroutines-overview.html):** For managing asynchronous operations.
* **[Koin](https://insert-koin.io/):** For dependency injection.
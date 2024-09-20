# :core:datastore

This module provides an interface for storing and retrieving data locally on each platform. It abstracts the underlying storage mechanisms (e.g., SharedPreferences on Android, UserDefaults on iOS) and provides a consistent API for data persistence.

This module might include:

* A common interface for data storage and retrieval.
* Platform-specific implementations of the data storage interface.
* Serialization and deserialization of data.

## Dependencies

This module uses the following dependencies:

* **[Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization):** For serializing and deserializing data to be stored.
* **[Kotlinx Coroutines](https://kotlinlang.org/docs/coroutines-overview.html):** For managing asynchronous operations related to data storage.
* **[Okio](https://square.github.io/okio/):** A multiplatform library for efficient I/O operations.
* **(Platform-specific dependencies):** List any platform-specific dependencies used for data storage (e.g., AndroidX DataStore on Android).

This module uses the `com.kmpshowcase.buildlogic.multiplatform` plugin for setup.
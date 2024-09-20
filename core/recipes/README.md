# :core:recipes

This module manages data related to recipes within the application. It might include:

* Data models for representing recipes and related information (e.g., ingredients, instructions).
* Repositories for accessing and managing recipe data (e.g., fetching from a network source).
* Use cases or interactors for performing operations on recipe data (e.g., getting a recipe).

## Dependencies

This module uses the following dependencies:

* **[Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization):** For serializing and deserializing recipe data.
* **[Kotlinx Coroutines](https://kotlinlang.org/docs/coroutines-overview.html):** For managing asynchronous operations related to recipe data.
* **[Paging](https://developer.android.com/topic/libraries/architecture/paging):** For loading and displaying large sets of recipe data efficiently (passed from the `com.kmpshowcase.buildlogic.multiplatform` plugin).
* **`:core:network`:** For making network requests to fetch recipe data.

This module uses the `com.kmpshowcase.buildlogic.multiplatform` plugin for setup.
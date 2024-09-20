# :feature:home

This module provides the home screen of the application, which displays a list of recipes. It includes:

* A screen or view for displaying the list of recipes.
* Use of Paging for efficient loading of recipe data.
* Navigation to the recipe details screen when a recipe is clicked.

## Dependencies

This module uses the following dependencies:

* **[Compose Multiplatform](https://developer.android.com/jetpack/compose):** For building user interface components.
* **[Compose Material](https://developer.android.com/jetpack/compose/material):** For using Material Design components in Compose.
* **[Paging](https://developer.android.com/topic/libraries/architecture/paging):** For loading and displaying large sets of recipe data efficiently.
* **(Other potential dependencies):** List any other relevant dependencies here (e.g., an image loading library for Compose).
* **`:core:ui`:** For shared UI components and styling.
* **`:core:recipes`:** For accessing and managing recipe data.
* **`:core:common`:** For common utilities and helper functions.
* **`:core:datastore`:** For storing and retrieving user data locally.
* **`:core:user`:** For managing user-related data and operations.

This module uses the `com.kmpshowcase.buildlogic.multiplatform.compose` plugin for setup.
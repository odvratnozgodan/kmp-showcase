# :feature:authentication

This module provides the user interface for authentication within the application. It includes:

* Screens or views for user login and registration.
* Handling of user input.
* Interaction with the `:core:authentication` module for authentication logic.

## Dependencies

This module uses the following dependencies:

* **[Compose Multiplatform](https://developer.android.com/jetpack/compose):** For building user interface components.
* **[Compose Material](https://developer.android.com/jetpack/compose/material):** For using Material Design components in Compose.
* **`:core:ui`:** For shared UI components and styling.
* **`:core:authentication`:** For authentication logic and data.
* **`:core:common`:** For common utilities and helper functions.
* **`:core:datastore`:** For storing and retrieving user data locally.
* **`:core:user`:** For managing user-related data and operations.

This module uses the `com.kmpshowcase.buildlogic.multiplatform.compose` plugin for setup.
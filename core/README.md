# :core

This module contains the core logic of the application, shared across all platforms. It includes the following submodules:

* **:core:authentication:** Handles user authentication and authorization.
* **:core:common:** Contains common utilities, extensions, and helper functions used throughout the application.
* **:core:datastore:** Provides an interface for storing and retrieving data locally.
* **:core:network:** Handles network communication and API interactions.
* **:core:recipes:** Manages data related to recipes.
* **:core:ui:** Contains shared UI components and styling that can be used across different features.
* **:core:user:** Manages user-related data and operations.

Each submodule has its own README.md file with a detailed explanation and a list of dependencies.

**Note:** All submodules, except `:core:ui`, utilize the `com.kmpshowcase.buildlogic.multiplatform` plugin for setup. The `:core:ui` submodule, which contains UI components, uses the `com.kmpshowcase.buildlogic.multiplatform.compose` plugin.
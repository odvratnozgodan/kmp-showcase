# Kotlin Multiplatform Project Showcase

This project showcases a Kotlin Multiplatform mobile application with Compose Multiplatform for UI. The application targets Android, iOS, and Web (wasmJs, experimental). It demonstrates how to structure a modularized Kotlin Multiplatform project and leverage modern tools and libraries.

### WasmJs
I have disabled it in this project because some dependencies don't yet support it or are still buggy(mostly because they are in alpha stage).
You can enable it in the composite build plugin. Search for `configureWebApplication()` and uncomment it.  

## Key Features

* **Modular Structure:** The project is divided into distinct modules for better organization, code reuse, and independent development.
* **Compose Multiplatform:** [Compose](https://developer.android.com/jetpack/compose) A declarative UI framework for building consistent user interfaces across all platforms.
* **Kotlin Multiplatform:** [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) Write shared business logic and platform-specific code where necessary.
* **Ktor Client:** [Ktor](https://ktor.io/) A multiplatform networking library for making HTTP requests.
* **Koin:** [Koin](https://insert-koin.io/) A lightweight dependency injection framework for Kotlin.
* **Coil:** [Coil](https://coil-kt.github.io/coil/) An image loading library for Compose.
* **Paging:** [Paging](https://developer.android.com/topic/libraries/architecture/paging) A library for loading and displaying large datasets efficiently.
* **Compose Navigation** [Compose Multiplatform Navigation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html) A library used for navigation between the screens. **Doesn't support WasmJs**
* **Okio** [Okio](https://square.github.io/okio/) A library used for file handling and data storage.
* **Inspektify** [Inspektify](https://github.com/BVantur/inspektify?tab=readme-ov-file#features) Library used for inspecting network traffic of the app
* **Custom Composite Build Plugins:** Reduce boilerplate and enforce consistency with custom Gradle plugins.

## Modules

This project is organized into the following modules:

* **:build-logic:** Contains the composite build plugins used to configure all other modules.
* **:composeApp:** The main application module for each platform (Android, iOS, Web).
* **:core:** Contains shared business logic, data models, and common utilities with the following submodules:
    *  `:core:authentication`
    *  `:core:common`
    *  `:core:datastore`
    *  `:core:network`
    *  `:core:recipes`
    *  `:core:ui`
    *  `:core:user`
* **:feature:** Feature modules encapsulate specific functionalities or screens of the application with the following submodules:
    * `:feature:authentication`
    * `:feature:home`

Each module has its own README.md file with a detailed explanation and a list of dependencies.

## Data used

Used [https://dummyjson.com/](https://dummyjson.com/) for mocking the API endpoints.

## Getting Started

1. Clone the repository: `git clone https://github.com/odvratnozgodan/kmp-showcase`
2. Open the project in Android Studio.
3. Build and run the app on your desired platform.

## TODO
- [ ] Implement iOS theme
- [ ] Upgrade Koin to 4.0
- [x] Better handling of data reloading and application background state


## Contributing

Contributions are welcome! Feel free to open issues and pull requests.

## License

This project is licensed under the [MIT License](https://opensource.org/license/mit).
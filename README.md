# Kotlin Multiplatform Project Showcase

This project showcases a Kotlin Multiplatform mobile application with Compose Multiplatform for UI. The application targets Android, iOS, and Web (wasmJs, experimental). It demonstrates how to structure a modularized Kotlin Multiplatform project and leverage modern tools and libraries.

## Key Features

* **Modular Structure:** The project is divided into distinct modules for better organization, code reuse, and independent development.
* **Compose Multiplatform:** [Compose](https://developer.android.com/jetpack/compose) A declarative UI framework for building consistent user interfaces across all platforms.
* **Kotlin Multiplatform:** [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) Write shared business logic and platform-specific code where necessary.
* **Ktor Client:** [Ktor](https://ktor.io/) A multiplatform networking library for making HTTP requests.
* **Koin:** [Koin](https://insert-koin.io/) A lightweight dependency injection framework for Kotlin.
* **Coil:** [Coil](https://coil-kt.github.io/coil/) An image loading library for Compose.
* **Paging:** [Paging](https://developer.android.com/topic/libraries/architecture/paging) A library for loading and displaying large datasets efficiently.
* **Custom Composite Build Plugins:** Reduce boilerplate and enforce consistency with custom Gradle plugins.

## Modules

This project is organized into the following modules:

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

## Getting Started

1. Clone the repository: `git clone https://github.com/<your-username>/Kotlin-Multiplatform-Project-Showcase.git`
2. Open the project in Android Studio.
3. Build and run the app on your desired platform.

## Contributing

Contributions are welcome! Feel free to open issues and pull requests.

## License

This project is licensed under the [MIT License](LICENSE).
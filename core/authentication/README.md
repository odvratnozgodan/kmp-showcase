# :core:authentication

This module is responsible for handling user authentication and authorization within the application. It uses JWT (JSON Web Tokens) for authentication and provides functionalities for:

* User login
* User registration
* Session management

This module is also responsible for creating and providing an authenticated Ktor HTTP client.

## Dependencies

This module uses the following dependencies:

* **[Ktor Client](https://ktor.io/):** For making network requests to the authentication server.
* **[Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization):** For serializing and deserializing data related to authentication (e.g., user credentials, tokens).
* **[Kotlinx Coroutines](https://kotlinlang.org/docs/coroutines-overview.html):** For managing asynchronous operations related to authentication.
* **`:core:network`:** Provides basic network setup and functionality.

This module uses the `com.kmpshowcase.buildlogic.multiplatform` plugin for setup.

**Note:** If any other module needs to make authenticated network requests, it should include `:core:authentication` as a dependency to reuse the authenticated Ktor HTTP client.
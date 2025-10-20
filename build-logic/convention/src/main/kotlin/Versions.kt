import org.gradle.api.JavaVersion

object AppVersions {
    const val APP_ID = "com.kmp.showcase"

    const val APP_VERSION_NAME = "1.0.5"
    const val APP_VERSION_CODE = 105
}

object Configs {
    const val COMPILE_SDK_VERSION = 36
    const val MIN_SDK_VERSION = 26
    const val TARGET_SDK_VERSION = 36
    val JAVA_COMPATIBILITY_VERSION = JavaVersion.VERSION_11
}

object DeepLinkConfigs {
    const val DEEP_LINK_HOST = "dummyjson.com"
}

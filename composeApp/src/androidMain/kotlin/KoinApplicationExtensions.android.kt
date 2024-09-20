import com.kmpshowcase.client.KMPShowcaseApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication

actual fun KoinApplication.configurePlatform() {
    androidContext(KMPShowcaseApplication.instance)
}

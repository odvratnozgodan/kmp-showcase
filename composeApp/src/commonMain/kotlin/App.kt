import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import core.authentication.di.coreModuleAuthentication
import core.common.di.coreModuleCommon
import core.datastore.di.coreModuleDatastore
import core.network.di.coreModuleNetwork
import core.ui.di.coreModuleUi
import core.user.di.coreModuleUser
import feature.authentication.di.featureModuleAuthentication
import feature.authentication.navigation.addAuthenticationGraph
import feature.home.di.featureModuleHome
import feature.home.navigation.addHomeGraph
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.module
import recipes.di.coreModuleRecipes

@Composable
@Preview
fun App() {
    initKoin()
    AppNavigation()
}

@Composable
fun AppNavigation(viewModel: AppViewModel = koinInject<AppViewModel>()) {
    val navController = rememberNavController()
    val isReady by viewModel.isInitialDataReady.collectAsState()
    if (isReady) {
        val initialDestination by viewModel.initialNavigationDestination.collectAsState()
        val lifecycleOwner = LocalLifecycleOwner.current
        LaunchedEffect(Unit) {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.refreshData()
            }
        }
        LaunchedEffect(Unit) {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.navigation.collect {
                    it.navigate(navController)
                }
            }
        }

        NavHost(
            navController = navController,
            startDestination = initialDestination,
            modifier =
            Modifier
                .fillMaxSize()
        ) {
            addAuthenticationGraph()
            addHomeGraph()
        }
    }
}

fun initKoin() {
    fun appModule() = listOf(
        coreModuleCommon,
        coreModuleUi,
        coreModuleDatastore,
        coreModuleNetwork,
        coreModuleAuthentication,
        coreModuleUser,
        coreModuleRecipes,
        featureModuleAuthentication,
        featureModuleHome,
        module {
            viewModelOf(::AppViewModel)
        }
    )

    startKoin {
        configurePlatform()
        modules(appModule())
    }
}

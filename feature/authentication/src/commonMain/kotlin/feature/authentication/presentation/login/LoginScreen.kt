package feature.authentication.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.common.base.navigation.ScreenNavigationRoute
import core.ui.components.CoreButton
import core.ui.components.CoreTextField
import core.ui.theme.TemplateAppTheme
import core.ui.theme.ThemePreviews
import kmp_showcase.core.common.generated.resources.session_expired
import kmp_showcase.feature.authentication.generated.resources.Res
import kmp_showcase.feature.authentication.generated.resources.email_field_label
import kmp_showcase.feature.authentication.generated.resources.login_button_label
import kmp_showcase.feature.authentication.generated.resources.login_description
import kmp_showcase.feature.authentication.generated.resources.login_title
import kmp_showcase.feature.authentication.generated.resources.password_field_label
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun LoginScreen(login: ScreenNavigationRoute.Login, viewModel: LoginViewModel = koinInject<LoginViewModel>()) {
    val state by viewModel.viewState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.handleEvent(LoginEvent.OnReceivedArguments(login.sessionExpired))
    }
    LoginScreenContent(
        state,
        handleEvent = { viewModel.handleEvent(it) }
    )
}

@Composable
fun LoginScreenContent(viewState: LoginViewState, handleEvent: (event: LoginEvent) -> Unit) {
    TemplateAppTheme { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(top = 16.dp)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = stringResource(Res.string.login_title),
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                text = stringResource(Res.string.login_description),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )

            Inputs(viewState = viewState, handleEvent = handleEvent)
        }
    }
}

@Composable
private fun Inputs(viewState: LoginViewState, handleEvent: (event: LoginEvent) -> Unit) {
    val focusManager = LocalFocusManager.current

    var passwordVisible by remember { mutableStateOf(false) }
    var buttonEnabled by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = viewState.username, key2 = viewState.password) {
        buttonEnabled = viewState.username.isNotBlank() && viewState.password.isNotBlank()
    }

    CoreTextField(
        modifier = Modifier.padding(top = 40.dp),
        value = viewState.username,
        label = stringResource(Res.string.email_field_label),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.None
        ),
        readOnly = viewState.loading,
        onValueChange = {
            handleEvent(LoginEvent.EmailChanged(it))
        }
    )
    CoreTextField(
        modifier = Modifier.padding(top = 24.dp),
        value = viewState.password,
        label = stringResource(Res.string.password_field_label),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Go,
            capitalization = KeyboardCapitalization.None
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                if (buttonEnabled) {
                    handleEvent(
                        LoginEvent.SignIn(
                            email = viewState.username,
                            password = viewState.password
                        )
                    )
                }
            }
        ),
        readOnly = viewState.loading,
        onValueChange = {
            handleEvent(LoginEvent.PasswordChanged(it))
        },
        visualTransformation =
        if (passwordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            val image = if (passwordVisible) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, "")
            }
        },
        showClearIcon = false
    )

    if (viewState.emailError) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(top = 40.dp),
            text = viewState.emailMessage,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )
    }

    if (viewState.sessionExpiredError) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(top = 40.dp),
            text = stringResource(kmp_showcase.core.common.generated.resources.Res.string.session_expired),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )
    }

    CoreButton(
        text = stringResource(Res.string.login_button_label),
        enabled = buttonEnabled,
        loading = viewState.loading,
        modifier = Modifier.fillMaxWidth().padding(top = 40.dp),
        onClick = {
            focusManager.clearFocus()
            handleEvent(
                LoginEvent.SignIn(
                    email = viewState.username,
                    password = viewState.password
                )
            )
        }
    )
}

@ThemePreviews
@Composable
fun ScreenPreview() {
    TemplateAppTheme {
        LoginScreenContent(
            viewState = LoginViewState(),
            handleEvent = {}
        )
    }
}

@ThemePreviews
@Composable
fun ScreenPreviewError() {
    TemplateAppTheme {
        LoginScreenContent(
            viewState = LoginViewState(emailError = true, emailMessage = "Some error message"),
            handleEvent = {}
        )
    }
}

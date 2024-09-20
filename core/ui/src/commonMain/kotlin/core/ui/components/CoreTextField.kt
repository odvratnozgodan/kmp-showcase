package core.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import core.ui.theme.TemplateAppTheme
import core.ui.theme.ThemePreviews

@Composable
fun CoreTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    label: String = "",
    placeholder: String = "",
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    showClearIcon: Boolean = true,
    errorMessage: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    noOutlineColor: Boolean = false,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    val fullWidth = Modifier.fillMaxWidth()
    val merged = modifier.then(fullWidth)
    var hasFocus by remember { mutableStateOf(false) }

    val defaultColors = if (noOutlineColor) {
        OutlinedTextFieldDefaults.colors(
            disabledBorderColor = Color.Unspecified,
            errorBorderColor = Color.Unspecified,
            focusedBorderColor = Color.Unspecified,
            unfocusedBorderColor = Color.Unspecified
        )
    } else {
        colors
    }

    val clearIcon = @Composable {
        IconButton(onClick = {
            onValueChange.invoke("")
        }) {
            Icon(imageVector = Icons.Filled.Clear, "")
        }
    }

    val setTrailingIcon = {
        when {
            showClearIcon && value.isNotEmpty() && hasFocus -> clearIcon
            trailingIcon != null -> trailingIcon
            else -> null
        }
    }

    var trailingIconOrClearIcon by remember {
        mutableStateOf(
            setTrailingIcon()
        )
    }
    LaunchedEffect(key1 = hasFocus) {
        trailingIconOrClearIcon = setTrailingIcon()
    }
    Column(
        modifier = merged,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        OutlinedTextField(
            value = value,
            modifier = fullWidth.onFocusChanged {
                hasFocus = it.hasFocus
            },
            enabled = enabled,
            readOnly = readOnly,
            isError = isError,
            onValueChange = onValueChange,
            label = if (label.isNotBlank()) {
                { Text(text = label) }
            } else {
                null
            },
            placeholder = {
                Text(
                    text = placeholder,
                    style = textStyle
                )
            },
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIconOrClearIcon,
            singleLine = singleLine,
            maxLines = if (singleLine) 1 else maxLines,
            minLines = minLines,
            prefix = prefix,
            suffix = suffix,
            shape = OutlinedTextFieldDefaults.shape,
            colors = defaultColors
        )
        AnimatedVisibility(
            (isError && errorMessage.isNotBlank()),
            enter = slideInVertically(),
            exit = slideOutVertically()
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun ClearIcon(clearClicked: () -> Unit) = IconButton(onClick = clearClicked) {
    Icon(imageVector = Icons.Filled.Clear, "")
}

@Composable
@ThemePreviews
fun CoreTextFieldPreview() {
    TemplateAppTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CoreTextField(
                value = "",
                label = "Label",
                placeholder = "Placeholder"
            )
            CoreTextField(
                value = "Text preview",
                label = "Label",
                placeholder = "Placeholder"
            )
            CoreTextField(
                value = "Text preview",
                label = "Label",
                placeholder = "Placeholder",
                enabled = false
            )
            CoreTextField(
                value = "Text preview",
                label = "Label",
                placeholder = "Placeholder",
                readOnly = true
            )
            CoreTextField(
                value = "Text preview",
                label = "Label",
                placeholder = "Placeholder",
                isError = true,
                errorMessage = "Error message"
            )
        }
    }
}

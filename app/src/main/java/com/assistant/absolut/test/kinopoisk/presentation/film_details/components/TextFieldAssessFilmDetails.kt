package com.assistant.absolut.test.kinopoisk.presentation.film_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.CustomTextSelectionColors
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.TextFieldBackground
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.TextFieldBorder
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.TextFieldCursorColor
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.TextFieldFocusedBorder
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.TextFieldHintColor
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.TextFieldTextColor

@Preview
@Composable
fun TextFieldAssessFilmDetails(
    text: String = "",
    onTextChange: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    hint: String = ""
) {

    var isFocused by remember { mutableStateOf(false) }

    CompositionLocalProvider(LocalTextSelectionColors provides CustomTextSelectionColors) {
        BasicTextField(
            value = text,
            onValueChange = { onTextChange(it) },
            textStyle = TextStyle(
                color = TextFieldTextColor,
                fontSize = 18.sp
            ),
            cursorBrush = SolidColor(TextFieldCursorColor),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = modifier
                .onFocusChanged {
                    isFocused = it.isFocused
                },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(TextFieldBackground)
                        .border(
                            width = 2.dp,
                            color = if (isFocused) TextFieldFocusedBorder else TextFieldBorder,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    contentAlignment = Alignment.CenterStart
                ) {

                    if (text.isEmpty()) {
                        Text(hint, color = TextFieldHintColor, fontSize = 16.sp)
                    }
                    innerTextField()
                }
            }
        )
    }
}
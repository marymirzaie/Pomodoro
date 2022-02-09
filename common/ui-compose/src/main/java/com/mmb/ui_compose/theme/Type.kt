package com.mmb.ui_compose.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.mmb.ui_compose.R

val Nunito = FontFamily(
    Font(R.font.nunito_regular),
    Font(R.font.nunito_medium, FontWeight.W500),
    Font(R.font.nunito_bold, FontWeight.Bold)
)
val Typography = Typography(defaultFontFamily = Nunito)
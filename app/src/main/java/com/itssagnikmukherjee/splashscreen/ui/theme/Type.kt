package com.itssagnikmukherjee.splashscreen.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.itssagnikmukherjee.pragati.R

// Set of Material typography styles to start with

//Custom font MUKTA

val mukta = FontFamily(
    Font(R.font.muktaregular, FontWeight.Normal),
    Font(R.font.muktamedium, FontWeight.Medium),
    Font(R.font.muktasemibold, FontWeight.SemiBold),
    Font(R.font.muktabold, FontWeight.Bold)
)

val outfit = FontFamily(
    Font(R.font.outfitregular, FontWeight.Normal),
    Font(R.font.outfitmedium, FontWeight.Medium),
    Font(R.font.outfitlight, FontWeight.Light),
    Font(R.font.outfitbold, FontWeight.Bold),
    Font(R.font.outfitsemibold, FontWeight.SemiBold)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
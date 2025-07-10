package com.ansssiaz.component.ui_components.ratingBar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ansssiaz.component.ui_components.R

@Composable
fun RatingBar(rating: Float, maxRating: Int = 5) {
    val ratingOn5 = rating / 2f
    val fullStars = ratingOn5.toInt()
    val fraction = ratingOn5 - fullStars
    Row {
        repeat(fullStars) {
            Icon(
                painter = painterResource(id = R.drawable.rating_full_star),
                tint = Color.Unspecified,
                contentDescription = null,
            )
        }

        if (fraction > 0f) {
            PartialStar(fraction)
        }

        repeat(maxRating - fullStars - if (fraction > 0f) 1 else 0) {
            Icon(
                painter = painterResource(id = R.drawable.rating_empty_star),
                tint = Color.Unspecified,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun PartialStar(fraction: Float, modifier: Modifier = Modifier) {
    val size = 28.dp
    val fractionClamped = fraction.coerceIn(0f, 1f)
    Box(modifier = modifier.size(28.dp)) {
        Icon(
            painter = painterResource(id = R.drawable.rating_empty_star),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.matchParentSize()
        )
        Icon(
            painter = painterResource(id = R.drawable.rating_full_star),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .matchParentSize()
                .drawWithContent {
                    val clipWidth = size.toPx() * fractionClamped
                    clipRect(left = 0f, top = 0f, right = clipWidth, bottom = size.toPx()) {
                        this@drawWithContent.drawContent()
                    }
                }
        )
    }
}
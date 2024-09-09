package com.timedsilence.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun shapeManager(isBoth: Boolean = false,isLast: Boolean = false,isFirst: Boolean = false,radius: Int): RoundedCornerShape {
    val smallerRadius: Dp = (radius/5).dp
    val defaultRadius: Dp = radius.dp

    return when {
        isBoth -> RoundedCornerShape(defaultRadius)
        isLast -> RoundedCornerShape(smallerRadius,smallerRadius,defaultRadius,defaultRadius)
        isFirst -> RoundedCornerShape(defaultRadius,defaultRadius,smallerRadius,smallerRadius)
        else -> RoundedCornerShape(smallerRadius)
    }
}

@Composable
fun CardBox(shape: RoundedCornerShape = RoundedCornerShape(16.dp), content: @Composable () -> Unit ) {
    ElevatedCard(
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .padding(bottom = 3.dp)
            .clip(shape)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    horizontal = 20.dp,
                    vertical = 10.dp
                )
                .fillMaxWidth()
        ) {
            content()
        }
    }
}

@Composable
fun SheetContent(
    content: (() -> Unit?)? = null
) {
    val radius = RoundedCornerShape(10.dp)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 10.dp
            )
    ) {
        if (content != null) {
            content()
        }

        CardBox(
            shape = shapeManager(radius = 16, isFirst = true)
        ) {
            Text("Hello")
        }

        CardBox(
            shape = shapeManager(radius = 16)
        ) {
            Text("Hello again")
        }

        CardBox(
            shape = shapeManager(radius = 16, isLast = true)
        ) {
            Text("Bye now")
        }
    }
}
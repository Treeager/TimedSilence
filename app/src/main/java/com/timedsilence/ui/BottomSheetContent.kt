package com.timedsilence.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun shapeManager(isBoth: Boolean = false,isLast: Boolean = false,isFirst: Boolean = false,radius: Int = 16): RoundedCornerShape {
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
fun CardBoxTitle(title: String, small: Boolean = false) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = if (small) colorScheme.onPrimary else Color.Unspecified
    )
}

@Composable
fun CardBoxIcon(icon: ImageVector, small: Boolean = false) {
    Box(
        modifier = Modifier
            .background(
                color = if (small) colorScheme.surfaceContainerHighest else colorScheme.primary,
                shape = RoundedCornerShape(50)
            )

    ) {
        Icon(
            imageVector = icon,
            tint = if (small) colorScheme.onSurface else colorScheme.surfaceContainerHighest,
            contentDescription = null,
            modifier = Modifier
                .scale(if (small) 0.8f else 1f)
                .padding( 9.dp)
        )
    }
}

@Composable
fun CardBox(
    shape: RoundedCornerShape = shapeManager(radius = 16),
    title: String = "",
    icon: ImageVector? = null,
    isLast: Boolean = false,
    small: Boolean = false,
    onClick: (() -> Unit)? = null,
    content: @Composable() (() -> Unit)? = null
) {
    ElevatedCard(
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = if (small) CardDefaults.elevatedCardColors(containerColor = colorScheme.primary) else CardDefaults.elevatedCardColors(),
        modifier = Modifier
            .padding(bottom = 3.dp)
            .clip(shape)
            .clickable {
                if (onClick != null)
                    onClick()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    24.dp,
                    16.dp,
                    14.dp,
                    16.dp
                )
                .fillMaxWidth()
        ) {
            CardBoxTitle(
                title = title,
                small = small
            )

            if (icon != null) {
                Spacer(modifier = Modifier.weight(1f))
                CardBoxIcon(
                    icon = icon,
                    small = small
                )
            }

            if (content != null) {
                content()
            }
        }
    }
    Spacer(modifier = Modifier.height(if (isLast) 26.dp else 2.dp))
}

@Composable
fun SheetContent(
    content: (() -> Unit?)? = null
) {
    val context = LocalContext.current

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

        // Say thank you
        CardBox(
            title = "Say \"Thank you!\"",
            icon = Icons.Filled.ThumbUp,
            shape = CircleShape,
            small = true,
            isLast = true,
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.me/yavka"))
                context.startActivity(intent)
            }
        )

        CardBox(
            title = "Hello",
            icon = Icons.Filled.Add,
            shape = shapeManager(isFirst = true)
        ) {
        }

        CardBox(
            title = "Hello again"
        )

        CardBox(
            title = "Bye now",
            shape = shapeManager(isLast = true),
            isLast = true
        )
    }
}
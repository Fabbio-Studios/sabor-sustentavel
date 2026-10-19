package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.EcoBorder
import com.example.ui.theme.EcoDeepGreen
import com.example.ui.theme.EcoInk
import com.example.ui.theme.EcoLeafGreen
import com.example.ui.theme.EcoLime
import com.example.ui.theme.EcoParchment
import com.example.ui.theme.EcoRustRed
import com.example.ui.theme.EcoSurface
import com.example.ui.theme.SpaceGrotesk

/**
 * Clean Neobrutalist Card utilizing the sustainable palette:
 * ["#386641","#6a994e","#a7c957","#f2e8cf","#bc4749"]
 */
@Composable
fun NeobrutalistCard(
  modifier: Modifier = Modifier,
  backgroundColor: Color = EcoSurface,
  borderColor: Color = EcoDeepGreen,
  shadowColor: Color = EcoDeepGreen,
  borderWidth: Dp = 2.dp,
  shadowOffset: Dp = 3.dp,
  shape: Shape = RoundedCornerShape(2.dp),
  onClick: (() -> Unit)? = null,
  testTag: String? = null,
  content: @Composable BoxScope.() -> Unit
) {
  val interactionSource = remember { MutableInteractionSource() }
  val isPressed by interactionSource.collectIsPressedAsState()

  val currentShadowOffset by animateDpAsState(
    targetValue = if (onClick != null && isPressed) 1.dp else shadowOffset,
    animationSpec = spring(dampingRatio = 0.65f, stiffness = 1400f),
    label = "shadow_offset"
  )
  val currentCardOffset by animateDpAsState(
    targetValue = if (onClick != null && isPressed) (shadowOffset - 1.dp) else 0.dp,
    animationSpec = spring(dampingRatio = 0.65f, stiffness = 1400f),
    label = "card_offset"
  )

  var baseModifier = modifier.padding(end = shadowOffset, bottom = shadowOffset)
  if (testTag != null) {
    baseModifier = baseModifier.testTag(testTag)
  }

  Box(modifier = baseModifier) {
    // 1. Hard solid shadow
    if (shadowOffset > 0.dp) {
      Box(
        modifier = Modifier
          .matchParentSize()
          .offset(x = currentShadowOffset, y = currentShadowOffset)
          .background(shadowColor, shape)
      )
    }

    // 2. Foreground surface
    val surfaceModifier = Modifier
      .offset(x = currentCardOffset, y = currentCardOffset)
      .background(backgroundColor, shape)
      .border(borderWidth, borderColor, shape)
      .then(
        if (onClick != null) {
          Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick
          )
        } else Modifier
      )

    Box(
      modifier = surfaceModifier,
      content = content
    )
  }
}

/**
 * Neobrutalist Button with icon and tactile push-down physics.
 */
@Composable
fun NeobrutalistButton(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  backgroundColor: Color = EcoDeepGreen,
  textColor: Color = EcoSurface,
  icon: ImageVector? = null,
  shadowOffset: Dp = 3.dp,
  borderWidth: Dp = 2.dp,
  testTag: String? = null
) {
  NeobrutalistCard(
    modifier = modifier,
    backgroundColor = backgroundColor,
    borderColor = EcoDeepGreen,
    shadowColor = EcoDeepGreen,
    borderWidth = borderWidth,
    shadowOffset = shadowOffset,
    onClick = onClick,
    testTag = testTag
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 14.dp, vertical = 9.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      if (icon != null) {
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = textColor,
          modifier = Modifier.size(17.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
      }
      Text(
        text = text,
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        color = textColor
      )
    }
  }
}

/**
 * Clean Badge using the palette tones.
 */
@Composable
fun NeobrutalistBadge(
  text: String,
  modifier: Modifier = Modifier,
  backgroundColor: Color = EcoLime,
  textColor: Color = EcoDeepGreen,
  borderColor: Color = EcoDeepGreen,
  icon: ImageVector? = null,
  shadowOffset: Dp = 2.dp,
  borderWidth: Dp = 1.5.dp
) {
  NeobrutalistCard(
    modifier = modifier,
    backgroundColor = backgroundColor,
    borderColor = borderColor,
    shadowColor = borderColor,
    borderWidth = borderWidth,
    shadowOffset = shadowOffset,
    shape = RoundedCornerShape(2.dp)
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      if (icon != null) {
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = textColor,
          modifier = Modifier.size(13.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
      }
      Text(
        text = text,
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        color = textColor
      )
    }
  }
}

/**
 * Clean Icon Button.
 */
@Composable
fun NeobrutalistIconButton(
  icon: ImageVector,
  contentDescription: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  backgroundColor: Color = EcoSurface,
  iconTint: Color = EcoDeepGreen,
  shadowOffset: Dp = 2.5.dp,
  borderWidth: Dp = 2.dp,
  testTag: String? = null
) {
  NeobrutalistCard(
    modifier = modifier,
    backgroundColor = backgroundColor,
    borderColor = EcoDeepGreen,
    shadowColor = EcoDeepGreen,
    borderWidth = borderWidth,
    shadowOffset = shadowOffset,
    onClick = onClick,
    testTag = testTag
  ) {
    Box(
      modifier = Modifier.padding(8.dp),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        tint = iconTint,
        modifier = Modifier.size(20.dp)
      )
    }
  }
}

/**
 * Animated Favorite Toggle with Rust Red pop and scale bounce.
 */
@Composable
fun NeobrutalistFavoriteButton(
  isFavorite: Boolean,
  onToggle: () -> Unit,
  modifier: Modifier = Modifier,
  testTag: String? = null
) {
  val scale by animateFloatAsState(
    targetValue = if (isFavorite) 1.15f else 1f,
    animationSpec = spring(dampingRatio = 0.5f, stiffness = 1200f),
    label = "fav_scale"
  )

  val bg by animateColorAsState(
    targetValue = if (isFavorite) EcoRustRed else EcoSurface,
    label = "fav_bg"
  )

  val tint by animateColorAsState(
    targetValue = if (isFavorite) EcoSurface else EcoDeepGreen,
    label = "fav_tint"
  )

  NeobrutalistCard(
    modifier = modifier,
    backgroundColor = bg,
    borderColor = EcoDeepGreen,
    shadowColor = EcoDeepGreen,
    borderWidth = 1.5.dp,
    shadowOffset = 2.dp,
    onClick = onToggle,
    testTag = testTag
  ) {
    Box(
      modifier = Modifier
        .padding(7.dp)
        .scale(scale),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        imageVector = if (isFavorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
        contentDescription = if (isFavorite) "Remover dos favoritos" else "Salvar nos favoritos",
        tint = tint,
        modifier = Modifier.size(18.dp)
      )
    }
  }
}

/**
 * Animated Checkbox with Deep Green checkmark fill and bounce.
 */
@Composable
fun NeobrutalistCheckbox(
  checked: Boolean,
  onCheckedChange: (Boolean) -> Unit,
  modifier: Modifier = Modifier
) {
  val scale by animateFloatAsState(
    targetValue = if (checked) 1.05f else 1f,
    animationSpec = spring(dampingRatio = 0.6f, stiffness = 1200f),
    label = "check_scale"
  )

  val bgColor by animateColorAsState(
    targetValue = if (checked) EcoDeepGreen else EcoSurface,
    label = "check_bg"
  )

  Box(
    modifier = modifier
      .size(22.dp)
      .scale(scale)
      .background(bgColor, RoundedCornerShape(2.dp))
      .border(2.dp, EcoDeepGreen, RoundedCornerShape(2.dp))
      .clickable { onCheckedChange(!checked) },
    contentAlignment = Alignment.Center
  ) {
    if (checked) {
      Icon(
        imageVector = Icons.Default.Check,
        contentDescription = "Selecionado",
        tint = EcoSurface,
        modifier = Modifier.size(16.dp)
      )
    }
  }
}

/**
 * Filter Tab with custom palette and micro-interactions.
 */
@Composable
fun NeobrutalistFilterTab(
  label: String,
  icon: ImageVector,
  isSelected: Boolean,
  onClick: () -> Unit,
  testTag: String? = null
) {
  val bg by animateColorAsState(
    targetValue = if (isSelected) EcoDeepGreen else EcoSurface,
    label = "filter_bg"
  )

  val textColor by animateColorAsState(
    targetValue = if (isSelected) EcoSurface else EcoDeepGreen,
    label = "filter_text"
  )

  val shadow by animateDpAsState(
    targetValue = if (isSelected) 1.dp else 2.5.dp,
    label = "filter_shadow"
  )

  NeobrutalistCard(
    backgroundColor = bg,
    borderColor = EcoDeepGreen,
    shadowColor = EcoDeepGreen,
    borderWidth = 2.dp,
    shadowOffset = shadow,
    onClick = onClick,
    testTag = testTag
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(
        imageVector = icon,
        contentDescription = null,
        tint = textColor,
        modifier = Modifier.size(15.dp)
      )
      Spacer(modifier = Modifier.width(6.dp))
      Text(
        text = label,
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        color = textColor
      )
    }
  }
}

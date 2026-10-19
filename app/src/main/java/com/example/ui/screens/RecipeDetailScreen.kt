package com.example.ui.screens

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Recipe
import com.example.ui.components.NeobrutalistBadge
import com.example.ui.components.NeobrutalistButton
import com.example.ui.components.NeobrutalistCard
import com.example.ui.components.NeobrutalistCheckbox
import com.example.ui.components.NeobrutalistFavoriteButton
import com.example.ui.components.NeobrutalistIconButton
import com.example.ui.theme.EcoDeepGreen
import com.example.ui.theme.EcoInk
import com.example.ui.theme.EcoLeafGreen
import com.example.ui.theme.EcoLime
import com.example.ui.theme.EcoParchment
import com.example.ui.theme.EcoSurface
import com.example.ui.theme.SpaceGrotesk

@Composable
fun RecipeDetailScreen(
  recipe: Recipe,
  isFavorite: Boolean,
  onToggleFavorite: () -> Unit,
  onBack: () -> Unit
) {
  BackHandler { onBack() }

  val context = LocalContext.current
  val checkedIngredients = remember { mutableStateMapOf<Int, Boolean>() }

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .background(EcoParchment)
      .testTag("recipe_detail_screen"),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // 1. Navigation Top Bar
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        NeobrutalistButton(
          text = "Voltar",
          icon = Icons.AutoMirrored.Filled.ArrowBack,
          onClick = onBack,
          backgroundColor = EcoSurface,
          textColor = EcoDeepGreen,
          borderWidth = 2.dp,
          shadowOffset = 2.5.dp,
          testTag = "btn_back"
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          NeobrutalistFavoriteButton(
            isFavorite = isFavorite,
            onToggle = onToggleFavorite,
            testTag = "btn_detail_favorite"
          )
          NeobrutalistIconButton(
            icon = Icons.Default.Share,
            contentDescription = "Compartilhar",
            backgroundColor = EcoSurface,
            iconTint = EcoDeepGreen,
            borderWidth = 2.dp,
            shadowOffset = 2.5.dp,
            onClick = {
              val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, recipe.title)
                putExtra(
                  Intent.EXTRA_TEXT,
                  "${recipe.title}\nAproveitamento: ${recipe.wasteHighlight}\n\nIngredientes:\n${recipe.ingredients.joinToString("\n• ")}"
                )
              }
              context.startActivity(Intent.createChooser(shareIntent, "Compartilhar receita"))
            },
            testTag = "btn_share"
          )
        }
      }
    }

    // 2. Title & Overview Card
    item {
      NeobrutalistCard(
        backgroundColor = EcoSurface,
        borderColor = EcoDeepGreen,
        shadowColor = EcoDeepGreen,
        borderWidth = 2.dp,
        shadowOffset = 3.5.dp,
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          NeobrutalistBadge(
            text = recipe.category.label,
            backgroundColor = EcoLime,
            textColor = EcoDeepGreen,
            borderWidth = 1.5.dp,
            shadowOffset = 1.5.dp
          )

          Spacer(modifier = Modifier.height(10.dp))

          Text(
            text = recipe.title,
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = EcoDeepGreen,
            lineHeight = 26.sp
          )

          Spacer(modifier = Modifier.height(6.dp))

          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.Eco,
              contentDescription = null,
              tint = EcoLeafGreen,
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
              text = "Aproveitamento: ${recipe.wasteHighlight}",
              fontFamily = SpaceGrotesk,
              fontWeight = FontWeight.Bold,
              fontSize = 13.sp,
              color = EcoDeepGreen
            )
          }

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = recipe.intro,
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = EcoInk.copy(alpha = 0.85f),
            lineHeight = 20.sp
          )

          Spacer(modifier = Modifier.height(12.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            NeobrutalistBadge(
              text = "${recipe.prepTimeMinutes} min",
              icon = Icons.Default.Schedule,
              backgroundColor = EcoParchment,
              textColor = EcoDeepGreen,
              borderWidth = 1.dp,
              shadowOffset = 1.dp
            )
            NeobrutalistBadge(
              text = recipe.servings,
              icon = Icons.Default.People,
              backgroundColor = EcoParchment,
              textColor = EcoDeepGreen,
              borderWidth = 1.dp,
              shadowOffset = 1.dp
            )
            NeobrutalistBadge(
              text = recipe.studentGroup,
              icon = Icons.Default.School,
              backgroundColor = EcoParchment,
              textColor = EcoDeepGreen,
              borderWidth = 1.dp,
              shadowOffset = 1.dp
            )
          }
        }
      }
    }

    // 3. Ingredients Section with Animated Progress Bar
    item {
      val doneCount = checkedIngredients.values.count { it }
      val totalCount = recipe.ingredients.size
      val progress = if (totalCount > 0) doneCount.toFloat() / totalCount else 0f
      val animatedProgress by animateFloatAsState(targetValue = progress, label = "progress")

      Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "Ingredientes",
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = EcoDeepGreen
          )

          AnimatedContent(
            targetState = doneCount,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "counter"
          ) { count ->
            NeobrutalistBadge(
              text = "$count de $totalCount marcados",
              icon = if (count == totalCount) Icons.Default.CheckCircle else null,
              backgroundColor = if (count == totalCount) EcoLime else EcoSurface,
              textColor = EcoDeepGreen,
              borderWidth = 1.5.dp,
              shadowOffset = 1.5.dp
            )
          }
        }

        // Clean Progress Bar
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(7.dp)
            .background(EcoSurface, RoundedCornerShape(2.dp))
            .border(1.5.dp, EcoDeepGreen, RoundedCornerShape(2.dp))
        ) {
          Box(
            modifier = Modifier
              .fillMaxHeight()
              .fillMaxWidth(animatedProgress)
              .background(EcoLeafGreen, RoundedCornerShape(1.dp))
          )
        }
      }
    }

    itemsIndexed(recipe.ingredients) { index, ingredient ->
      val isChecked = checkedIngredients[index] ?: false

      NeobrutalistCard(
        backgroundColor = if (isChecked) EcoParchment.copy(alpha = 0.6f) else EcoSurface,
        borderColor = EcoDeepGreen,
        shadowColor = EcoDeepGreen,
        borderWidth = 1.5.dp,
        shadowOffset = 2.dp,
        modifier = Modifier.fillMaxWidth(),
        onClick = { checkedIngredients[index] = !isChecked },
        testTag = "ingredient_item_$index"
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          NeobrutalistCheckbox(
            checked = isChecked,
            onCheckedChange = { checkedIngredients[index] = it }
          )
          Spacer(modifier = Modifier.width(10.dp))
          Text(
            text = ingredient,
            fontFamily = SpaceGrotesk,
            fontSize = 14.sp,
            color = if (isChecked) EcoDeepGreen.copy(alpha = 0.5f) else EcoInk,
            textDecoration = if (isChecked) TextDecoration.LineThrough else TextDecoration.None,
            lineHeight = 19.sp,
            modifier = Modifier.weight(1f)
          )
        }
      }
    }

    // 4. Instructions Section
    item {
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "Modo de preparo",
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = EcoDeepGreen
      )
    }

    itemsIndexed(recipe.instructions) { index, step ->
      val stepNum = index + 1

      NeobrutalistCard(
        backgroundColor = EcoSurface,
        borderColor = EcoDeepGreen,
        shadowColor = EcoDeepGreen,
        borderWidth = 1.5.dp,
        shadowOffset = 2.dp,
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
          verticalAlignment = Alignment.Top
        ) {
          Box(
            modifier = Modifier
              .size(24.dp)
              .background(EcoLime, RoundedCornerShape(2.dp))
              .border(1.5.dp, EcoDeepGreen, RoundedCornerShape(2.dp)),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "$stepNum",
              fontFamily = SpaceGrotesk,
              fontWeight = FontWeight.Bold,
              fontSize = 12.sp,
              color = EcoDeepGreen
            )
          }

          Spacer(modifier = Modifier.width(10.dp))

          Text(
            text = step,
            fontFamily = SpaceGrotesk,
            fontSize = 14.sp,
            color = EcoInk,
            lineHeight = 20.sp,
            modifier = Modifier.weight(1f)
          )
        }
      }
    }

    // 5. Sustainability Tip Box
    item {
      NeobrutalistCard(
        backgroundColor = EcoLime,
        borderColor = EcoDeepGreen,
        shadowColor = EcoDeepGreen,
        borderWidth = 2.dp,
        shadowOffset = 3.dp,
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.Lightbulb,
              contentDescription = null,
              tint = EcoDeepGreen,
              modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Dica de aproveitamento",
              fontFamily = SpaceGrotesk,
              fontWeight = FontWeight.Bold,
              fontSize = 13.sp,
              color = EcoDeepGreen
            )
          }
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = recipe.sustainabilityTip,
            fontFamily = SpaceGrotesk,
            fontSize = 13.sp,
            color = EcoDeepGreen,
            lineHeight = 18.sp
          )
        }
      }
    }

    // Bottom back button
    item {
      Spacer(modifier = Modifier.height(6.dp))
      NeobrutalistButton(
        text = "Voltar ao catálogo",
        icon = Icons.AutoMirrored.Filled.ArrowBack,
        onClick = onBack,
        backgroundColor = EcoDeepGreen,
        textColor = EcoSurface,
        modifier = Modifier.fillMaxWidth()
      )
      Spacer(modifier = Modifier.height(16.dp))
    }
  }
}

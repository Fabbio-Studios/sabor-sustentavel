package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.data.RecipeData
import com.example.ui.screens.AboutProjectScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.RecipeDetailScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.NeoBackground

sealed interface AppScreen {
  data object Home : AppScreen
  data class RecipeDetail(val recipeId: String) : AppScreen
  data object About : AppScreen
}

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        Scaffold(
          modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars),
          containerColor = NeoBackground
        ) { innerPadding ->
          Box(
            modifier = Modifier
              .fillMaxSize()
              .padding(innerPadding)
              .background(NeoBackground)
          ) {
            SaborSustentavelApp()
          }
        }
      }
    }
  }
}

@Composable
fun SaborSustentavelApp() {
  var currentScreen by remember { mutableStateOf<AppScreen>(AppScreen.Home) }
  var favoriteRecipeIds by remember { mutableStateOf(setOf("bolo-casca-banana")) }

  val toggleFavorite: (String) -> Unit = { id ->
    favoriteRecipeIds = if (favoriteRecipeIds.contains(id)) {
      favoriteRecipeIds - id
    } else {
      favoriteRecipeIds + id
    }
  }

  AnimatedContent(
    targetState = currentScreen,
    transitionSpec = { fadeIn() togetherWith fadeOut() },
    label = "screen_transition"
  ) { targetScreen ->
    when (targetScreen) {
      is AppScreen.Home -> {
        HomeScreen(
          onSelectRecipe = { recipe ->
            currentScreen = AppScreen.RecipeDetail(recipe.id)
          },
          onOpenAbout = { currentScreen = AppScreen.About },
          favoriteRecipeIds = favoriteRecipeIds,
          onToggleFavorite = toggleFavorite
        )
      }

      is AppScreen.RecipeDetail -> {
        val recipe = RecipeData.recipes.find { it.id == targetScreen.recipeId }
          ?: RecipeData.recipes.first()
        RecipeDetailScreen(
          recipe = recipe,
          isFavorite = favoriteRecipeIds.contains(recipe.id),
          onToggleFavorite = { toggleFavorite(recipe.id) },
          onBack = { currentScreen = AppScreen.Home }
        )
      }

      is AppScreen.About -> {
        AboutProjectScreen(
          onBack = { currentScreen = AppScreen.Home }
        )
      }
    }
  }
}

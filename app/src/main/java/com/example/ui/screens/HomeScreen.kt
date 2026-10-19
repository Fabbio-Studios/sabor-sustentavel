package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DinnerDining
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.RecipeData
import com.example.model.Recipe
import com.example.model.RecipeCategory
import com.example.ui.components.NeobrutalistBadge
import com.example.ui.components.NeobrutalistButton
import com.example.ui.components.NeobrutalistCard
import com.example.ui.components.NeobrutalistFavoriteButton
import com.example.ui.components.NeobrutalistFilterTab
import com.example.ui.components.NeobrutalistIconButton
import com.example.ui.theme.EcoDeepGreen
import com.example.ui.theme.EcoInk
import com.example.ui.theme.EcoLeafGreen
import com.example.ui.theme.EcoLime
import com.example.ui.theme.EcoParchment
import com.example.ui.theme.EcoRustRed
import com.example.ui.theme.EcoSurface
import com.example.ui.theme.SpaceGrotesk

@Composable
fun HomeScreen(
  onSelectRecipe: (Recipe) -> Unit,
  onOpenAbout: () -> Unit,
  favoriteRecipeIds: Set<String>,
  onToggleFavorite: (String) -> Unit
) {
  var searchQuery by remember { mutableStateOf("") }
  var selectedCategory by remember { mutableStateOf(RecipeCategory.TODAS) }
  var showOnlyFavorites by remember { mutableStateOf(false) }

  val focusManager = LocalFocusManager.current
  val featuredRecipe = remember { RecipeData.getFeaturedRecipe() }

  val filteredRecipes = remember(searchQuery, selectedCategory, showOnlyFavorites, favoriteRecipeIds) {
    var list = RecipeData.filterRecipes(selectedCategory, searchQuery)
    if (showOnlyFavorites) {
      list = list.filter { favoriteRecipeIds.contains(it.id) }
    }
    list
  }

  BoxWithConstraints(
    modifier = Modifier
      .fillMaxSize()
      .background(EcoParchment)
  ) {
    val isWideScreen = maxWidth >= 600.dp
    val gridColumns = if (isWideScreen) 2 else 1

    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .testTag("home_screen"),
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      // 1. Header with branding and actions
      item {
        CleanHeader(
          onOpenAbout = onOpenAbout,
          showOnlyFavorites = showOnlyFavorites,
          onToggleShowFavorites = { showOnlyFavorites = !showOnlyFavorites },
          favoritesCount = favoriteRecipeIds.size
        )
      }

      // 2. Search Bar
      item {
        CleanSearchBar(
          query = searchQuery,
          onQueryChange = { searchQuery = it },
          onClear = { searchQuery = "" },
          onSearch = { focusManager.clearFocus() }
        )
      }

      // 3. Category Filter Tabs
      item {
        CategoryTabsRow(
          selectedCategory = selectedCategory,
          onCategorySelect = { selectedCategory = it }
        )
      }

      // 4. Recipe of the Day Hero
      if (searchQuery.isBlank() && selectedCategory == RecipeCategory.TODAS && !showOnlyFavorites) {
        item {
          RecipeOfTheDayCard(
            recipe = featuredRecipe,
            isFavorite = favoriteRecipeIds.contains(featuredRecipe.id),
            onToggleFavorite = { onToggleFavorite(featuredRecipe.id) },
            onClick = { onSelectRecipe(featuredRecipe) }
          )
        }
      }

      // 5. Section Title & Counter
      item {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = if (showOnlyFavorites) Icons.Default.Bookmark else Icons.Default.Eco,
              contentDescription = null,
              tint = if (showOnlyFavorites) EcoRustRed else EcoDeepGreen,
              modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = if (showOnlyFavorites) "Salvas" else "Catálogo",
              fontFamily = SpaceGrotesk,
              fontWeight = FontWeight.Bold,
              fontSize = 17.sp,
              color = EcoDeepGreen
            )
          }

          NeobrutalistBadge(
            text = "${filteredRecipes.size} receitas",
            backgroundColor = EcoSurface,
            textColor = EcoDeepGreen,
            borderWidth = 1.5.dp,
            shadowOffset = 2.dp
          )
        }
      }

      // 6. Recipe Cards Responsive Grid
      if (filteredRecipes.isEmpty()) {
        item {
          CleanEmptyState(
            isFavoritesEmpty = showOnlyFavorites && favoriteRecipeIds.isEmpty(),
            onReset = {
              searchQuery = ""
              selectedCategory = RecipeCategory.TODAS
              showOnlyFavorites = false
            }
          )
        }
      } else {
        if (gridColumns == 1) {
          filteredRecipes.forEach { recipe ->
            item(key = recipe.id) {
              CleanRecipeCard(
                recipe = recipe,
                isFavorite = favoriteRecipeIds.contains(recipe.id),
                onToggleFavorite = { onToggleFavorite(recipe.id) },
                onClick = { onSelectRecipe(recipe) },
                modifier = Modifier.fillMaxWidth()
              )
            }
          }
        } else {
          val pairs = filteredRecipes.chunked(2)
          pairs.forEach { rowRecipes ->
            item(key = rowRecipes.joinToString { it.id }) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
              ) {
                rowRecipes.forEach { recipe ->
                  Box(modifier = Modifier.weight(1f)) {
                    CleanRecipeCard(
                      recipe = recipe,
                      isFavorite = favoriteRecipeIds.contains(recipe.id),
                      onToggleFavorite = { onToggleFavorite(recipe.id) },
                      onClick = { onSelectRecipe(recipe) },
                      modifier = Modifier.fillMaxWidth()
                    )
                  }
                }
                if (rowRecipes.size == 1) {
                  Spacer(modifier = Modifier.weight(1f))
                }
              }
            }
          }
        }
      }

      item {
        Spacer(modifier = Modifier.height(20.dp))
      }
    }
  }
}

@Composable
private fun CleanHeader(
  onOpenAbout: () -> Unit,
  showOnlyFavorites: Boolean,
  onToggleShowFavorites: () -> Unit,
  favoritesCount: Int
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      NeobrutalistCard(
        backgroundColor = EcoLime,
        borderColor = EcoDeepGreen,
        shadowColor = EcoDeepGreen,
        borderWidth = 2.dp,
        shadowOffset = 2.5.dp,
        modifier = Modifier.size(42.dp)
      ) {
        Image(
          painter = painterResource(id = R.drawable.ic_app_foreground),
          contentDescription = "Logo",
          modifier = Modifier.fillMaxSize(),
          contentScale = ContentScale.Crop
        )
      }
      Spacer(modifier = Modifier.width(10.dp))
      Column {
        Text(
          text = "Sabor Sustentável",
          fontFamily = SpaceGrotesk,
          fontWeight = FontWeight.Bold,
          fontSize = 17.sp,
          color = EcoDeepGreen
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.School,
            contentDescription = null,
            tint = EcoLeafGreen,
            modifier = Modifier.size(12.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "7º Ano • Cozinha Sem Desperdício",
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            color = EcoDeepGreen.copy(alpha = 0.85f)
          )
        }
      }
    }

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      NeobrutalistIconButton(
        icon = if (showOnlyFavorites) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
        contentDescription = "Favoritas",
        backgroundColor = if (showOnlyFavorites) EcoRustRed else EcoSurface,
        iconTint = if (showOnlyFavorites) EcoSurface else EcoDeepGreen,
        borderWidth = 2.dp,
        shadowOffset = 2.5.dp,
        onClick = onToggleShowFavorites,
        testTag = "btn_favorites"
      )
      NeobrutalistIconButton(
        icon = Icons.Default.Info,
        contentDescription = "Sobre o Projeto",
        backgroundColor = EcoSurface,
        iconTint = EcoDeepGreen,
        borderWidth = 2.dp,
        shadowOffset = 2.5.dp,
        onClick = onOpenAbout,
        testTag = "btn_about"
      )
    }
  }
}

@Composable
private fun CleanSearchBar(
  query: String,
  onQueryChange: (String) -> Unit,
  onClear: () -> Unit,
  onSearch: () -> Unit
) {
  NeobrutalistCard(
    backgroundColor = EcoSurface,
    borderColor = EcoDeepGreen,
    shadowColor = EcoDeepGreen,
    borderWidth = 2.dp,
    shadowOffset = 3.dp,
    modifier = Modifier.fillMaxWidth()
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 12.dp, vertical = 10.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(
        imageVector = Icons.Default.Search,
        contentDescription = "Buscar",
        tint = EcoDeepGreen,
        modifier = Modifier.size(20.dp)
      )
      Spacer(modifier = Modifier.width(8.dp))
      Box(modifier = Modifier.weight(1f)) {
        if (query.isEmpty()) {
          Text(
            text = "Buscar por casca, talo, receita...",
            fontFamily = SpaceGrotesk,
            fontSize = 14.sp,
            color = EcoDeepGreen.copy(alpha = 0.5f)
          )
        }
        BasicTextField(
          value = query,
          onValueChange = onQueryChange,
          textStyle = TextStyle(
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = EcoInk
          ),
          cursorBrush = SolidColor(EcoDeepGreen),
          singleLine = true,
          keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
          keyboardActions = KeyboardActions(onSearch = { onSearch() }),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("search_input")
        )
      }
      if (query.isNotEmpty()) {
        Spacer(modifier = Modifier.width(6.dp))
        Icon(
          imageVector = Icons.Default.Clear,
          contentDescription = "Limpar",
          tint = EcoDeepGreen,
          modifier = Modifier
            .size(18.dp)
            .clickable { onClear() }
        )
      }
    }
  }
}

@Composable
private fun CategoryTabsRow(
  selectedCategory: RecipeCategory,
  onCategorySelect: (RecipeCategory) -> Unit
) {
  val scrollState = rememberScrollState()

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .horizontalScroll(scrollState),
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    val items = listOf(
      Triple(RecipeCategory.TODAS, "Todas", Icons.Default.AllInclusive),
      Triple(RecipeCategory.PRATOS_PRINCIPAIS, "Pratos", Icons.Default.DinnerDining),
      Triple(RecipeCategory.SOBREMESAS, "Sobremesas", Icons.Default.Cake),
      Triple(RecipeCategory.LANCHES, "Lanches", Icons.Default.LunchDining)
    )

    items.forEach { (cat, label, icon) ->
      NeobrutalistFilterTab(
        label = label,
        icon = icon,
        isSelected = selectedCategory == cat,
        onClick = { onCategorySelect(cat) },
        testTag = "filter_${cat.name.lowercase()}"
      )
    }
  }
}

@Composable
private fun RecipeOfTheDayCard(
  recipe: Recipe,
  isFavorite: Boolean,
  onToggleFavorite: () -> Unit,
  onClick: () -> Unit
) {
  NeobrutalistCard(
    backgroundColor = EcoLime,
    borderColor = EcoDeepGreen,
    shadowColor = EcoDeepGreen,
    borderWidth = 2.dp,
    shadowOffset = 3.5.dp,
    modifier = Modifier.fillMaxWidth(),
    testTag = "recipe_of_the_day_card"
  ) {
    Column(modifier = Modifier.padding(14.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        NeobrutalistBadge(
          text = "Receita do dia",
          icon = Icons.Default.Star,
          backgroundColor = EcoDeepGreen,
          textColor = EcoSurface,
          borderColor = EcoDeepGreen,
          borderWidth = 1.5.dp,
          shadowOffset = 2.dp
        )
        NeobrutalistFavoriteButton(
          isFavorite = isFavorite,
          onToggle = onToggleFavorite
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = recipe.title,
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 19.sp,
        color = EcoDeepGreen,
        lineHeight = 23.sp
      )

      Spacer(modifier = Modifier.height(4.dp))

      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          imageVector = Icons.Default.Eco,
          contentDescription = null,
          tint = EcoDeepGreen,
          modifier = Modifier.size(15.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
          text = "Aproveita: ${recipe.wasteHighlight}",
          fontFamily = SpaceGrotesk,
          fontWeight = FontWeight.Bold,
          fontSize = 12.sp,
          color = EcoDeepGreen
        )
      }

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = recipe.intro,
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        color = EcoInk,
        lineHeight = 18.sp
      )

      Spacer(modifier = Modifier.height(12.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
          NeobrutalistBadge(
            text = "${recipe.prepTimeMinutes} min",
            icon = Icons.Default.Schedule,
            backgroundColor = EcoSurface,
            textColor = EcoDeepGreen
          )
          NeobrutalistBadge(
            text = recipe.servings,
            icon = Icons.Default.People,
            backgroundColor = EcoSurface,
            textColor = EcoDeepGreen
          )
        }

        NeobrutalistButton(
          text = "Ver receita",
          icon = Icons.AutoMirrored.Filled.ArrowForward,
          onClick = onClick,
          backgroundColor = EcoDeepGreen,
          textColor = EcoSurface,
          borderWidth = 2.dp,
          shadowOffset = 2.5.dp,
          testTag = "btn_view_featured_recipe"
        )
      }
    }
  }
}

@Composable
private fun CleanRecipeCard(
  recipe: Recipe,
  isFavorite: Boolean,
  onToggleFavorite: () -> Unit,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  NeobrutalistCard(
    backgroundColor = EcoSurface,
    borderColor = EcoDeepGreen,
    shadowColor = EcoDeepGreen,
    borderWidth = 2.dp,
    shadowOffset = 3.dp,
    modifier = modifier,
    onClick = onClick,
    testTag = "recipe_card_${recipe.id}"
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        NeobrutalistBadge(
          text = recipe.category.label,
          backgroundColor = EcoLime,
          textColor = EcoDeepGreen,
          borderColor = EcoDeepGreen,
          borderWidth = 1.5.dp,
          shadowOffset = 1.5.dp
        )
        NeobrutalistFavoriteButton(
          isFavorite = isFavorite,
          onToggle = onToggleFavorite
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = recipe.title,
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = EcoDeepGreen,
        lineHeight = 20.sp
      )

      Spacer(modifier = Modifier.height(4.dp))

      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          imageVector = Icons.Default.Eco,
          contentDescription = null,
          tint = EcoLeafGreen,
          modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
          text = "Usa: ${recipe.wasteHighlight}",
          fontFamily = SpaceGrotesk,
          fontWeight = FontWeight.Bold,
          fontSize = 12.sp,
          color = EcoDeepGreen
        )
      }

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = recipe.intro,
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        color = EcoInk.copy(alpha = 0.85f),
        lineHeight = 18.sp,
        maxLines = 2
      )

      Spacer(modifier = Modifier.height(10.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
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
        }

        NeobrutalistButton(
          text = "Abrir",
          icon = Icons.AutoMirrored.Filled.ArrowForward,
          onClick = onClick,
          backgroundColor = EcoDeepGreen,
          textColor = EcoSurface,
          borderWidth = 1.5.dp,
          shadowOffset = 2.dp
        )
      }
    }
  }
}

@Composable
private fun CleanEmptyState(
  isFavoritesEmpty: Boolean,
  onReset: () -> Unit
) {
  NeobrutalistCard(
    backgroundColor = EcoSurface,
    borderColor = EcoDeepGreen,
    shadowColor = EcoDeepGreen,
    borderWidth = 2.dp,
    shadowOffset = 3.dp,
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Icon(
        imageVector = if (isFavoritesEmpty) Icons.Default.BookmarkBorder else Icons.Default.Search,
        contentDescription = null,
        tint = if (isFavoritesEmpty) EcoRustRed else EcoDeepGreen,
        modifier = Modifier.size(36.dp)
      )
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = if (isFavoritesEmpty) "Nenhuma receita salva" else "Nenhum resultado",
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        color = EcoDeepGreen
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = if (isFavoritesEmpty)
          "Toque no marcador para salvar receitas nesta lista."
        else
          "Nenhuma receita encontrada com esse termo.",
        fontFamily = SpaceGrotesk,
        fontSize = 13.sp,
        color = EcoInk.copy(alpha = 0.8f)
      )
      Spacer(modifier = Modifier.height(12.dp))
      NeobrutalistButton(
        text = "Limpar busca",
        onClick = onReset,
        backgroundColor = EcoDeepGreen,
        textColor = EcoSurface,
        borderWidth = 1.5.dp,
        shadowOffset = 2.dp
      )
    }
  }
}

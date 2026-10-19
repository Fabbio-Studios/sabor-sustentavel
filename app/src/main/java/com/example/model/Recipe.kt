package com.example.model

enum class RecipeCategory(val label: String) {
  TODAS("Todas"),
  PRATOS_PRINCIPAIS("Pratos Principais"),
  SOBREMESAS("Sobremesas"),
  LANCHES("Lanches")
}

enum class WastePart(val label: String, val iconLabel: String) {
  TODOS("Todos", "🌱"),
  CASCAS("Cascas", "🍌"),
  TALOS("Talos", "🥬"),
  FOLHAS("Folhas", "🥕"),
  SEMENTES("Sementes", "🎃")
}

data class Recipe(
  val id: String,
  val title: String,
  val category: RecipeCategory,
  val wastePart: WastePart,
  val wasteHighlight: String,
  val prepTimeMinutes: Int,
  val servings: String,
  val difficulty: String,
  val intro: String,
  val ingredients: List<String>,
  val instructions: List<String>,
  val sustainabilityTip: String,
  val nutritionFact: String,
  val accentColorHex: Long,
  val studentGroup: String,
  val emoji: String,
  val isFeatured: Boolean = false
)

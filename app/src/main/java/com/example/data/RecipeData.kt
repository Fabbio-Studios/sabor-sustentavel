package com.example.data

import com.example.model.Recipe
import com.example.model.RecipeCategory
import com.example.model.WastePart

object RecipeData {
  val recipes = listOf(
    Recipe(
      id = "bolo-casca-banana",
      title = "Bolo de Casca de Banana",
      category = RecipeCategory.SOBREMESAS,
      wastePart = WastePart.CASCAS,
      wasteHighlight = "Cascas de banana",
      prepTimeMinutes = 45,
      servings = "10 porções",
      difficulty = "Fácil",
      intro = "As cascas batidas no liquidificador deixam a massa úmida e reduzem o descarte de bananas maduras.",
      ingredients = listOf(
        "4 cascas de banana lavadas",
        "2 ovos",
        "2 xícaras de farinha de trigo ou aveia",
        "1 xícara de açúcar mascavo",
        "1/2 xícara de óleo",
        "1 colher de sopa de canela em pó",
        "1 colher de sopa de fermento em pó",
        "1 pitada de sal"
      ),
      instructions = listOf(
        "Lave as cascas com água e deixe 10 minutos em água com um pouco de vinagre.",
        "Bata as cascas no liquidificador com os ovos e o óleo até virar um purê.",
        "Em uma tigela, misture a farinha, o açúcar, a canela e o sal.",
        "Junte o conteúdo do liquidificador aos secos e mexa bem.",
        "Adicione o fermento e misture devagar.",
        "Despeje em forma untada e asse a 180°C por cerca de 35 minutos."
      ),
      sustainabilityTip = "Bananas bem maduras deixam a massa mais doce e dispensam parte do açúcar.",
      nutritionFact = "A casca concentra fibras e potássio que normalmente vão para o lixo.",
      accentColorHex = 0xFFFFDE00,
      studentGroup = "7º Ano B",
      emoji = "",
      isFeatured = true
    ),
    Recipe(
      id = "sopa-talos-legumes",
      title = "Sopa de Talos e Legumes",
      category = RecipeCategory.PRATOS_PRINCIPAIS,
      wastePart = WastePart.TALOS,
      wasteHighlight = "Talos de couve e brócolis",
      prepTimeMinutes = 35,
      servings = "6 porções",
      difficulty = "Fácil",
      intro = "Cozimento simples de talos de verduras com batata e cebola, batido até virar creme.",
      ingredients = listOf(
        "2 xícaras de talos picados (couve, brócolis e salsa)",
        "2 batatas médias com casca bem lavada",
        "1 cenoura com casca",
        "1 cebola picada",
        "2 dentes de alho",
        "1 litro de água",
        "2 colheres de sopa de azeite",
        "Sal e pimenta a gosto"
      ),
      instructions = listOf(
        "Refogue o alho e a cebola no azeite até dourarem.",
        "Acrescente os talos, a cenoura e as batatas picadas e refogue por 3 minutos.",
        "Adicione a água e tempere com sal e pimenta.",
        "Cozinhe em fogo médio por 20 minutos até os talos amaciarem.",
        "Bata com mixer ou no liquidificador até obter consistência cremosa."
      ),
      sustainabilityTip = "Congele os talos picados durante a semana em um pote fechado até juntar a quantidade necessária.",
      nutritionFact = "Talos de brócolis e couve têm fibras espessas e cálcio preservado após o cozimento.",
      accentColorHex = 0xFF22C55E,
      studentGroup = "7º Ano A",
      emoji = "",
      isFeatured = false
    ),
    Recipe(
      id = "doce-casca-melancia",
      title = "Doce de Casca de Melancia",
      category = RecipeCategory.SOBREMESAS,
      wastePart = WastePart.CASCAS,
      wasteHighlight = "Parte branca da melancia",
      prepTimeMinutes = 50,
      servings = "8 porções",
      difficulty = "Médio",
      intro = "A entrecasca branca da fruta cortada em cubos e cozida em calda rala com canela e cravo.",
      ingredients = listOf(
        "500g de casca de melancia (apenas a parte branca)",
        "1 xícara e meia de açúcar",
        "2 cravos-da-índia",
        "1 canela em pau",
        "1 xícara de água",
        "Suco de meio limão"
      ),
      instructions = listOf(
        "Descasque a película verde escura da casca e corte a parte branca em cubos.",
        "Coloque os cubos em uma panela com o açúcar, a água, a canela e os cravos.",
        "Cozinhe em fogo baixo por cerca de 35 minutos até ficarem translúcidos.",
        "Pingue o suco de limão e deixe a calda apurar por mais 5 minutos.",
        "Espere esfriar antes de guardar na geladeira em pote fechado."
      ),
      sustainabilityTip = "A casca representa quase um terço do peso total da melancia e rende uma compota durável.",
      nutritionFact = "A parte branca tem água, fibras e textura crocante semelhante à do mamão verde.",
      accentColorHex = 0xFFFF4081,
      studentGroup = "7º Ano C",
      emoji = "",
      isFeatured = false
    ),
    Recipe(
      id = "pao-folhas-beterraba",
      title = "Pão de Beterraba e Folhas",
      category = RecipeCategory.LANCHES,
      wastePart = WastePart.FOLHAS,
      wasteHighlight = "Folhas e ramas de beterraba",
      prepTimeMinutes = 60,
      servings = "12 fatias",
      difficulty = "Médio",
      intro = "Pão caseiro preparado com o purê da raiz cozida com casca e as folhas refogadas na massa.",
      ingredients = listOf(
        "1 xícara de purê de beterraba cozida com casca",
        "1 xícara de folhas de beterraba picadas e refogadas",
        "3 xícaras de farinha de trigo",
        "1 sachê (10g) de fermento biológico seco",
        "1 colher de sopa de açúcar",
        "1/4 xícara de óleo",
        "1 colher de chá de sal",
        "1/2 xícara de água morna"
      ),
      instructions = listOf(
        "Dissolva o fermento na água morna com o açúcar por 5 minutos.",
        "Misture o purê de beterraba, o óleo, o sal e o fermento ativado em uma tigela.",
        "Junte a farinha aos poucos e sove por 10 minutos até a massa soltar das mãos.",
        "Incorpore as folhas refogadas na massa.",
        "Deixe a massa descansar coberta por 40 minutos.",
        "Modele o pão e asse a 180°C por cerca de 30 minutos."
      ),
      sustainabilityTip = "Ao comprar beterraba, evite descartar os ramos: eles podem ser refogados como couve ou usados em massas.",
      nutritionFact = "As folhas da beterraba têm ferro e vitamina A em quantidade comparável à do espinafre.",
      accentColorHex = 0xFFFF6D00,
      studentGroup = "7º Ano B",
      emoji = "",
      isFeatured = false
    ),
    Recipe(
      id = "salada-ramas-cenoura",
      title = "Salada com Ramas de Cenoura",
      category = RecipeCategory.PRATOS_PRINCIPAIS,
      wastePart = WastePart.FOLHAS,
      wasteHighlight = "Ramas verdes de cenoura",
      prepTimeMinutes = 15,
      servings = "4 porções",
      difficulty = "Fácil",
      intro = "Folhas jovens de cenoura fatiadas cruas com maçã e temperadas com azeite e limão.",
      ingredients = listOf(
        "2 xícaras de folhas de cenoura lavadas",
        "1 cenoura ralada com casca",
        "1 maçã fatiada fina",
        "2 colheres de sopa de sementes de girassol tostadas",
        "3 colheres de sopa de azeite",
        "1 colher de sopa de suco de limão",
        "Sal a gosto"
      ),
      instructions = listOf(
        "Lave bem as ramas e separe apenas as folhas, descartando talos muito duros.",
        "Rale a cenoura lavada sem descascar.",
        "Junte as ramas, a cenoura e a maçã fatiada em uma tigela.",
        "Misture o azeite, o limão e o sal em um copo e regue sobre a salada.",
        "Finalize com as sementes tostadas por cima."
      ),
      sustainabilityTip = "Ramas que sobrarem podem ser batidas com azeite, alho e sal para fazer molho pesto.",
      nutritionFact = "As folhas da cenoura contêm vitamina C e sabor herbáceo parecido com o da salsa.",
      accentColorHex = 0xFF00B0FF,
      studentGroup = "7º Ano A",
      emoji = "",
      isFeatured = false
    ),
    Recipe(
      id = "farofa-sementes-casca-abobora",
      title = "Farofa de Sementes e Casca de Abóbora",
      category = RecipeCategory.LANCHES,
      wastePart = WastePart.SEMENTES,
      wasteHighlight = "Sementes e casca de abóbora",
      prepTimeMinutes = 25,
      servings = "8 porções",
      difficulty = "Fácil",
      intro = "Sementes tostadas na frigideira misturadas com tiras da casca refogada e farinha de mandioca.",
      ingredients = listOf(
        "1 xícara de sementes de abóbora limpas e secas",
        "1 xícara de casca de abóbora ralada ou em tiras",
        "2 xícaras de farinha de mandioca",
        "1 cebola picada",
        "3 colheres de sopa de azeite ou manteiga",
        "Sal e cheiro-verde a gosto"
      ),
      instructions = listOf(
        "Toste as sementes em frigideira seca em fogo baixo por 4 minutos até dourarem. Reserve.",
        "Na mesma frigideira, refogue a cebola e as tiras de casca no azeite por 4 minutos.",
        "Adicione a farinha de mandioca e mexa em fogo baixo até dourar.",
        "Desligue o fogo, misture as sementes tostadas, o sal e o cheiro-verde."
      ),
      sustainabilityTip = "Seque as sementes com um pano limpo antes de tostar para dourarem uniformemente sem queimar.",
      nutritionFact = "Sementes de abóbora fornecem magnésio e gorduras insaturadas.",
      accentColorHex = 0xFFFFDE00,
      studentGroup = "7º Ano C",
      emoji = "",
      isFeatured = false
    )
  )

  fun getFeaturedRecipe(): Recipe = recipes.first { it.isFeatured }

  fun filterRecipes(category: RecipeCategory, query: String): List<Recipe> {
    return recipes.filter { recipe ->
      val matchesCategory = category == RecipeCategory.TODAS || recipe.category == category
      val matchesQuery = query.isBlank() ||
        recipe.title.contains(query, ignoreCase = true) ||
        recipe.intro.contains(query, ignoreCase = true) ||
        recipe.wasteHighlight.contains(query, ignoreCase = true) ||
        recipe.ingredients.any { it.contains(query, ignoreCase = true) }
      matchesCategory && matchesQuery
    }
  }
}

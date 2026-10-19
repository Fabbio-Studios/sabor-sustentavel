package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.RecipeData
import com.example.model.RecipeCategory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Sabor Sustentável", appName)
  }

  @Test
  fun `verify recipes data loaded correctly`() {
    val recipes = RecipeData.recipes
    assertTrue(recipes.size >= 5)

    val featured = RecipeData.getFeaturedRecipe()
    assertNotNull(featured)
    assertEquals("bolo-casca-banana", featured.id)

    val desserts = RecipeData.filterRecipes(RecipeCategory.SOBREMESAS, "")
    assertTrue(desserts.isNotEmpty())

    val bananaSearch = RecipeData.filterRecipes(RecipeCategory.TODAS, "banana")
    assertTrue(bananaSearch.any { it.title.contains("Banana", ignoreCase = true) })
  }
}

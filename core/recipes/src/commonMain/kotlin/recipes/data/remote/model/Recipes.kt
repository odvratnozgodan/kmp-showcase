package recipes.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Recipes(val limit: Int, val recipes: List<Recipe>, val skip: Int, val total: Int)

@Serializable
data class Recipe(val id: Int, val image: String, val name: String, val tags: List<String>, val userId: Int)

@Serializable
data class RecipeDetails(
    val caloriesPerServing: Int,
    val cookTimeMinutes: Int,
    val cuisine: String,
    val difficulty: String,
    val id: Int,
    val image: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val mealType: List<String>,
    val name: String,
    val prepTimeMinutes: Int,
    val rating: Float,
    val reviewCount: Int,
    val servings: Int,
    val tags: List<String>,
    val userId: Int,
)

val TestRecipe = Recipe(
    id = 1,
    name = "Classic Margherita Pizza",
    tags = listOf(
        "Pizza",
        "Italian"
    ),
    userId = 45,
    image = "https://cdn.dummyjson.com/recipe-images/1.webp"
)
val TestRecipeDetails = RecipeDetails(
    id = 1,
    name = "Classic Margherita Pizza",
    ingredients = listOf(
        "Pizza dough",
        "Tomato sauce",
        "Fresh mozzarella cheese",
        "Fresh basil leaves",
        "Olive oil",
        "Salt and pepper to taste"
    ),
    instructions = listOf(
        "Preheat the oven to 475°F (245°C).",
        "Roll out the pizza dough and spread tomato sauce evenly.",
        "Top with slices of fresh mozzarella and fresh basil leaves.",
        "Drizzle with olive oil and season with salt and pepper.",
        "Bake in the preheated oven for 12-15 minutes or until the crust is golden brown.",
        "Slice and serve hot."
    ),
    prepTimeMinutes = 20,
    cookTimeMinutes = 15,
    servings = 4,
    difficulty = "Easy",
    cuisine = "Italian",
    caloriesPerServing = 300,
    tags = listOf(
        "Pizza",
        "Italian"
    ),
    userId = 45,
    image = "https://cdn.dummyjson.com/recipe-images/1.webp",
    rating = 4.6f,
    reviewCount = 3,
    mealType = listOf("Dinner")
)

package recipes.data.remote.service

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

class RecipesService {

    fun getRecipes(startIndex: Int = 0, maxResults: Int = 10): HttpRequestBuilder.() -> Unit = {
        method = HttpMethod.Get
        url("recipes")
        parameter("skip", startIndex)
        parameter("limit", maxResults)
        parameter("select", "name,image,tags,userId")
    }

    fun getRecipe(id: Int): HttpRequestBuilder.() -> Unit = {
        method = HttpMethod.Get
        url("recipes/$id")
    }
}

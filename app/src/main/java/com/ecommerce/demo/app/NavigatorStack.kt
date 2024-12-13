package com.ecommerce.demo.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ecommerce.demo.app.screens.CartScreen
import com.ecommerce.demo.app.screens.ProductDetailsScreen
import com.ecommerce.demo.app.screens.ProductsListScreen
import kotlinx.serialization.Serializable

@Composable
fun navigationStack(): NavHostController {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SearchProductRoute) {
        composable<SearchProductRoute> {
            ProductsListScreen(navController)
        }

        composable<ProductDetailsRoute> {
            val args = it.toRoute<ProductDetailsRoute>()
            ProductDetailsScreen(navController, args.asin)
        }

        composable<CartRoute> {
            CartScreen()
        }
    }

    return navController
}


@Serializable
object SearchProductRoute

@Serializable
data class ProductDetailsRoute (
    val asin: String
)

@Serializable
object CartRoute



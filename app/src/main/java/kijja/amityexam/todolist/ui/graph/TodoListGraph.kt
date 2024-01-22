package kijja.amityexam.todolist.ui.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kijja.amityexam.todolist.ui.screen.detail.DetailListContainer
import kijja.amityexam.todolist.ui.screen.user.UserListContainer

@Composable
fun TodoListGraph() {
    // Create a NavHost with a NavController to navigate between destinations
    val navController = rememberNavController()

    // Define the navigation graph with start destination and composable destinations
    NavHost(
        navController = navController,
        startDestination = TodoListDestinations.USER_LIST.route,
    ) {
        // Composable for the User List screen
        composable(TodoListDestinations.USER_LIST.route) {
            UserListContainer(
                navHostController = navController,
            )
        }

        // Composable for the Detail List screen with a dynamic userId argument
        composable(
            TodoListDestinations.DETAIL_LIST.route + "/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType }),
        ) { backStackEntry ->
            DetailListContainer(
                // Extract userId from the navigation arguments
                userId = backStackEntry.arguments?.getInt("userId"),
            )
        }
    }
}

package kijja.amityexam.todolist.ui.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kijja.amityexam.todolist.ui.screen.detail.DetailListContainer
import kijja.amityexam.todolist.ui.screen.user.UserListContainer

@Composable
fun TodoListGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TodoListDestinations.USER_LIST.route,
    ) {
        composable(TodoListDestinations.USER_LIST.route) {
            UserListContainer(
                navHostController = navController
            )
        }
        composable(TodoListDestinations.DETAIL_LIST.route) {
            DetailListContainer(
                navHostController = navController
            )
        }
    }
}

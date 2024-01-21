package kijja.amityexam.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import kijja.amityexam.todolist.ui.screen.MainScreen
import kijja.amityexam.todolist.ui.theme.TodoListTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListTheme {
                MainScreen()
            }
        }
    }
}

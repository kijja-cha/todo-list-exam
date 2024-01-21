package kijja.amityexam.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dagger.hilt.android.AndroidEntryPoint
import kijja.amityexam.todolist.ui.graph.TodoListGraph
import kijja.amityexam.todolist.ui.theme.TodoListTheme

@AndroidEntryPoint
class TodoListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListTheme {
                Surface(modifier = Modifier.background(Color.White)) {
                    TodoListGraph()
                }
            }
        }
    }
}

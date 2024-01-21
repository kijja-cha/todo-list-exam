package kijja.amityexam.todolist.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kijja.amityexam.todolist.ui.theme.TodoListTheme

@Composable
fun LoadingView() {
    // Display a loading indicator in the center of the screen
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewLoadingView() {
    TodoListTheme {
        LoadingView()
    }
}

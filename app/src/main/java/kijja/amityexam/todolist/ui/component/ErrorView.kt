package kijja.amityexam.todolist.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kijja.amityexam.todolist.R
import kijja.amityexam.todolist.ui.theme.TodoListFonts
import kijja.amityexam.todolist.ui.theme.TodoListTheme

@Composable
fun ErrorView() {
    // Display an error message in the center of the screen
    Box(
        contentAlignment = Alignment.Center,
        modifier =
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
    ) {
        // Vertical column layout for the error message and retry button
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Display a header for the error message
            Text(
                text = stringResource(id = R.string.common_error_title),
                style =
                TextStyle(
                    fontSize = 20.sp,
                    fontFamily = TodoListFonts,
                    fontWeight = FontWeight.Bold,
                ),
            )

            // Display the error message with a light gray color
            Text(
                text = stringResource(id = R.string.common_error_message),
                style =
                TextStyle(
                    fontSize = 16.sp,
                    fontFamily = TodoListFonts,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.light_gray),
                ),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewErrorView() {
    TodoListTheme {
        ErrorView()
    }
}

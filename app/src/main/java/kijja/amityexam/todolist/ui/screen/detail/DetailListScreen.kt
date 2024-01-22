package kijja.amityexam.todolist.ui.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kijja.amityexam.todolist.data.model.TodoListEntity
import kijja.amityexam.todolist.ui.theme.TodoListFonts
import kijja.amityexam.todolist.ui.theme.TodoListTheme

@Composable
fun DetailListScreen(
    data: List<TodoListEntity>?,
    onItemCheckedChange: (TodoListEntity) -> Unit,
) {
    // Remember the lazy list state to maintain scroll position
    val lazyListState = rememberLazyListState()

    // Column layout to arrange the TodoItemList and the custom floating action button
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        // Box to contain the TodoItemList, allowing it to take available vertical space
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
        ) {
            // Display the list of to-do items
            TodoItemList(
                lazyListState,
                todoList = data.orEmpty(),
                onItemCheckedChange = onItemCheckedChange,
            )
        }
    }
}

@Composable
private fun TodoItemList(
    lazyListState: LazyListState,
    todoList: List<TodoListEntity>,
    onItemCheckedChange: (TodoListEntity) -> Unit,
) {
    // LazyColumn to efficiently display a list of to-do items
    LazyColumn(
        state = lazyListState,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        // Iterate through the list of to-do items and create items based on their data
        items(
            count = todoList.size,
            key = { index ->
                // Use a unique key for each item based on userId and id
                todoList[index].userId.toString() + todoList[index].id.toString()
            },
            itemContent = { index ->
                val todoItem = todoList[index]

                // Row containing the checkbox and text
                Row(Modifier.fillMaxWidth()) {
                    // Use remember to keep track of the checked state
                    val checkedState = remember { mutableStateOf(todoItem.status ?: false) }

                    // Checkbox for marking the item as checked or unchecked
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = {
                            checkedState.value = it
                            // Invoke the provided callback when the checked state changes
                            onItemCheckedChange.invoke(todoItem.copy(status = it))
                        },
                    )

                    // Text displaying the title of the to-do item
                    Text(
                        text = todoItem.title.orEmpty(),
                        style =
                            if (checkedState.value) {
                                // Apply different text style if the item is checked
                                TextStyle(
                                    fontFamily = TodoListFonts,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    textDecoration = TextDecoration.LineThrough,
                                )
                            } else {
                                TextStyle(
                                    fontFamily = TodoListFonts,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                            },
                        modifier =
                            Modifier
                                .padding(start = 8.dp)
                                .fillMaxHeight()
                                .align(Alignment.CenterVertically),
                    )
                }
            },
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewTodoItemListScreen() {
    TodoListTheme {
        TodoItemList(
            lazyListState = rememberLazyListState(),
            todoList =
                listOf(
                    TodoListEntity(
                        userId = 1,
                        id = 1,
                        title = "Title 1",
                        status = false,
                    ),
                    TodoListEntity(
                        userId = 1,
                        id = 2,
                        title = "Title 2",
                        status = true,
                    ),
                ),
            onItemCheckedChange = { },
        )
    }
}

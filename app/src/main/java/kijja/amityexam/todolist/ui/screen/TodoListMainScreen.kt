package kijja.amityexam.todolist.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kijja.amityexam.todolist.R
import kijja.amityexam.todolist.data.model.TodoListEntity
import kijja.amityexam.todolist.ui.theme.TodoListFonts

@Composable
fun MainScreen(todoListViewModel: TodoListViewModel = viewModel()) {
    val screenState by todoListViewModel.screenState.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()

    LaunchedEffect(Unit) {
        todoListViewModel.initLoadData()
    }

    when (val state = screenState) {
        TodoListState.Error -> {
            ErrorScreen(
                message = stringResource(id = R.string.common_error_message),
                todoListViewModel::loadData,
            )
        }

        TodoListState.Loading -> {
            LoadingScreen()
        }

        is TodoListState.Success -> {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize(),
            ) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .weight(1f),
                ) {
                    PortraitCoinItemList(
                        lazyListState,
                        state.data.orEmpty(),
//                        viewModel::clickCoinItem,
                    )
                }
            }
        }
    }
}

@Composable
private fun PortraitCoinItemList(
    lazyListState: LazyListState,
    data: List<TodoListEntity>,
//    onItemClick: () -> Unit,
) {
    LazyColumn(
        state = lazyListState,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(
            count = data.size,
            key = {
                data[it].userId.toString() + data[it].id.toString()
            },
            itemContent = { index ->
                val item = data[index]
                Column {
                    Text(
                        item.title.orEmpty(),
                        style =
                            TextStyle(
                                fontFamily = TodoListFonts,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                            ),
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }
            },
        )
    }
}

@Composable
private fun LoadingScreen() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorScreen(
    message: String,
    onRetryClick: () -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Sorry",
                style =
                    TextStyle(
                        fontSize = 20.sp,
                        fontFamily = TodoListFonts,
                        fontWeight = FontWeight.Bold,
                    ),
            )
            Text(
                text = message,
                style =
                    TextStyle(
                        fontSize = 16.sp,
                        fontFamily = TodoListFonts,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.light_gray),
                    ),
            )
            Button(onClick = onRetryClick) {
                Text(text = stringResource(id = R.string.retry))
            }
        }
    }
}

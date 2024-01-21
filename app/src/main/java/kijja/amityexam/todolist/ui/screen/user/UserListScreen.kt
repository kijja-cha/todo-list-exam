package kijja.amityexam.todolist.ui.screen.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kijja.amityexam.todolist.ui.theme.TodoListFonts
import kijja.amityexam.todolist.ui.theme.TodoListTheme

@Composable
fun UserListScreen(
    data: List<Int>?,
    onClickUser: (String) -> Unit,
) {
    // Remember the lazy list state to maintain scroll position
    val lazyListState = rememberLazyListState()

    // Column layout to arrange the UserList and provide space for future components
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        // Box to contain the UserList, allowing it to take available vertical space
        Box(
            modifier =
            Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            // Display the list of users
            UserList(
                lazyListState,
                userList = data.orEmpty(),
                onClickUser = onClickUser,
            )
        }
    }
}

@Composable
private fun UserList(
    lazyListState: LazyListState,
    userList: List<Int>,
    onClickUser: (String) -> Unit,
) {
    // LazyColumn to efficiently display a list of users
    LazyColumn(
        state = lazyListState,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        // Iterate through the list of users and create items based on their data
        items(
            count = userList.size,
            key = { index ->
                // Use a unique key for each item based on index and user id
                index.toString() + userList[index].toString()
            },
            itemContent = { index ->
                val userId = userList[index].toString()

                // Card containing user information, clickable to trigger onClickUser
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onClickUser.invoke(userId) },
                    shape = RoundedCornerShape(12.dp)
                ) {
                    // Row containing user icon and text
                    Row(modifier = Modifier.padding(16.dp)) {
                        // User icon
                        Icon(
                            imageVector = Icons.Rounded.AccountCircle,
                            contentDescription = "",
                            modifier = Modifier.size(36.dp),
                        )

                        // Text displaying the user id
                        Text(
                            text = "User: $userId",
                            style =
                            TextStyle(
                                fontFamily = TodoListFonts,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                            ),
                            modifier =
                            Modifier
                                .padding(start = 8.dp)
                                .fillMaxHeight()
                                .align(Alignment.CenterVertically),
                        )
                    }
                }
            },
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewUserListScreen() {
    TodoListTheme {
        UserList(
            lazyListState = rememberLazyListState(),
            userList =
                listOf(1, 2, 3, 4, 5),
            onClickUser = { },
        )
    }
}

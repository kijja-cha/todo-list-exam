package kijja.amityexam.todolist.viewmodel

import io.mockk.*
import kijja.amityexam.todolist.data.repository.TodoListRepository
import kijja.amityexam.todolist.ui.screen.user.UserListViewModel
import kijja.amityexam.todolist.ui.screen.user.UserListViewState
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserListViewModelTest {
    // Mock dependencies
    private val todoListRepository: TodoListRepository = mockk()

    // Class under test
    private lateinit var userListViewModel: UserListViewModel

    @Before
    fun setup() {
        // Initialize the class under test with the mock dependency
        userListViewModel = UserListViewModel(todoListRepository)
    }

    @After
    fun tearDown() {
        // Clear all mock calls after each test
        clearAllMocks()
    }

    @Test
    fun `initLoadData should trigger data loading if not initialized`() =
        runTest {
            // Mock that data has not been initialized
            every { todoListRepository.getUserList() } returns flowOf(listOf(1, 2, 3))

            // Call the function under test
            userListViewModel.initLoadData()

            // Verify that getUserList was called once
            verify(exactly = 1) { todoListRepository.getUserList() }

            // Verify that the screen state was updated to success
            assertEquals(UserListViewState.Success(listOf(1, 2, 3)), userListViewModel.screenState.value)
        }

    @Test
    fun `initLoadData should not trigger data loading if already initialized`() =
        runTest {
            // Mock that data has been initialized
            userListViewModel.initLoadData()

            // Call the function under test again
            userListViewModel.initLoadData()

            // Verify that getUserList was not called again
            verify(exactly = 0) { todoListRepository.getUserList() }
        }

    @Test
    fun `loadData should update screen state to success if data is not empty`() =
        runTest {
            // Mock a successful data loading response
            every { todoListRepository.getUserList() } returns flowOf(listOf(1, 2, 3))

            // Call the function under test
            userListViewModel.loadData()

            // Verify that getUserList was called once
            verify(exactly = 1) { todoListRepository.getUserList() }

            // Verify that the screen state was updated to success
            assertEquals(UserListViewState.Success(listOf(1, 2, 3)), userListViewModel.screenState.value)
        }

    @Test
    fun `loadData should update screen state to error if data is empty`() =
        runTest {
            // Mock an empty data loading response
            every { todoListRepository.getUserList() } returns flowOf(emptyList())

            // Call the function under test
            userListViewModel.loadData()

            // Verify that getUserList was called once
            verify(exactly = 1) { todoListRepository.getUserList() }

            // Verify that the screen state was updated to error
            assertEquals(UserListViewState.Error, userListViewModel.screenState.value)
        }

    @Test
    fun `loadData should update screen state to error on exception`() =
        runTest {
            // Mock an exception during data loading
            every { todoListRepository.getUserList() } throws Exception()

            // Call the function under test
            userListViewModel.loadData()

            // Verify that getUserList was called once
            verify(exactly = 1) { todoListRepository.getUserList() }

            // Verify that the screen state was updated to error
            assertEquals(UserListViewState.Error, userListViewModel.screenState.value)
        }
}

package kijja.amityexam.todolist.viewmodel

import io.mockk.*
import kijja.amityexam.todolist.data.model.TodoListEntity
import kijja.amityexam.todolist.data.repository.TodoListRepository
import kijja.amityexam.todolist.ui.screen.detail.DetailListViewModel
import kijja.amityexam.todolist.ui.screen.detail.DetailListViewState
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DetailListViewModelTest {
    // Mock dependencies
    private val todoListRepository: TodoListRepository = mockk()

    // Class under test
    private lateinit var detailListViewModel: DetailListViewModel

    private val item1 =
        TodoListEntity(
            id = 1,
            userId = 1,
            title = "Title1",
            status = false,
        )

    private val item2 =
        TodoListEntity(
            id = 2,
            userId = 1,
            title = "Title2",
            status = true,
        )

    private val item3 =
        TodoListEntity(
            id = 3,
            userId = 2,
            title = "Title3",
            status = true,
        )

    @Before
    fun setup() {
        // Initialize the class under test with the mock dependency
        detailListViewModel = DetailListViewModel(todoListRepository)
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
            every { todoListRepository.getTodoListByUserId(any()) } returns flowOf(listOf(item1, item2, item3))

            // Call the function under test
            detailListViewModel.initLoadData(userId = 1)

            // Verify that getTodoListByUserId was called once
            verify(exactly = 1) { todoListRepository.getTodoListByUserId(1) }

            // Verify that the screen state was updated to success
            assertEquals(DetailListViewState.Success(listOf(item1, item2, item3)), detailListViewModel.screenState.value)
        }

    @Test
    fun `initLoadData should not trigger data loading if already initialized`() =
        runTest {
            // Mock that data has been initialized
            detailListViewModel.initLoadData(userId = 1)

            // Call the function under test again
            detailListViewModel.initLoadData(userId = 1)

            // Verify that getTodoListByUserId was not called again
            verify(exactly = 0) { todoListRepository.getTodoListByUserId(any()) }
        }

    @Test
    fun `loadData should update screen state to success if data is not empty`() =
        runTest {
            // Mock a successful data loading response
            every { todoListRepository.getTodoListByUserId(any()) } returns flowOf(listOf(item1, item2, item3))

            // Call the function under test
            detailListViewModel.loadData(userId = 1)

            // Verify that getTodoListByUserId was called once
            verify(exactly = 1) { todoListRepository.getTodoListByUserId(1) }

            // Verify that the screen state was updated to success
            assertEquals(DetailListViewState.Success(listOf(item1, item2, item3)), detailListViewModel.screenState.value)
        }

    @Test
    fun `loadData should update screen state to error if data is empty`() =
        runTest {
            // Mock an empty data loading response
            every { todoListRepository.getTodoListByUserId(any()) } returns flowOf(emptyList())

            // Call the function under test
            detailListViewModel.loadData(userId = 1)

            // Verify that getTodoListByUserId was called once
            verify(exactly = 1) { todoListRepository.getTodoListByUserId(1) }

            // Verify that the screen state was updated to error
            assertEquals(DetailListViewState.Error, detailListViewModel.screenState.value)
        }

    @Test
    fun `loadData should update screen state to error on exception`() =
        runTest {
            // Mock an exception during data loading
            every { todoListRepository.getTodoListByUserId(any()) } throws Exception()

            // Call the function under test
            detailListViewModel.loadData(userId = 1)

            // Verify that getTodoListByUserId was called once
            verify(exactly = 1) { todoListRepository.getTodoListByUserId(1) }

            // Verify that the screen state was updated to error
            assertEquals(DetailListViewState.Error, detailListViewModel.screenState.value)
        }

    @Test
    fun `onCheckedItemChange should update the to-do list item in the repository`() =
        runTest {
            // Mock a successful update response
            coEvery { todoListRepository.updateTodoList(item1) } just Runs

            // Call the function under test
            detailListViewModel.onCheckedItemChange(item1)

            // Verify that updateTodoList was called once with the correct item
            coVerify(exactly = 1) { todoListRepository.updateTodoList(item1) }
        }
}

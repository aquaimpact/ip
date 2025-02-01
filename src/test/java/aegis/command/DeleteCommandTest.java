package aegis.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aegis.exception.TaskInputException;
import aegis.storage.FileSave;
import aegis.task.Task;
import aegis.task.TaskList;
import aegis.task.Todo;

public class DeleteCommandTest {

    private static final String TEST_FILE_PATH = "test_save.txt";
    private TaskList taskList;
    private FileSave fileSave;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        fileSave = new FileSave(TEST_FILE_PATH);
    }

    @AfterEach
    void tearDown() {
        // Delete the test file after each test
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void execute_emptyTaskList_taskInputExceptionThrown() {
        DeleteCommand deleteCommand = new DeleteCommand(0);
        assertThrows(TaskInputException.class, () -> deleteCommand.execute(taskList, fileSave));
    }

    @Test
    void execute_validIndex_taskRemovedFromList() throws IOException, TaskInputException {
        Task task = new Todo("Task to delete");
        taskList.addTask(task);
        assertEquals(1, taskList.getSize());

        DeleteCommand deleteCommand = new DeleteCommand(0);
        deleteCommand.execute(taskList, fileSave);

        assertEquals(0, taskList.getSize());
    }

    @Test
    void execute_validIndex_correctTaskDeleted() throws IOException, TaskInputException {
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);

        DeleteCommand deleteCommand = new DeleteCommand(0);
        deleteCommand.execute(taskList, fileSave);

        assertEquals(1, taskList.getSize());
        assertEquals("Task 2", taskList.getTasks().get(0).getTaskName());
    }

}

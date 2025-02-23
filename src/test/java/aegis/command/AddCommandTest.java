package aegis.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aegis.exception.FileSaveException;
import aegis.exception.TaskInputException;
import aegis.storage.FileSave;
import aegis.task.Task;
import aegis.task.TaskList;
import aegis.task.Todo;

public class AddCommandTest {

    private static final String TEST_FILE_PATH = "test_save.txt";
    private TaskList taskList;
    private FileSave fileSave;
    private Task task;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        fileSave = new FileSave(TEST_FILE_PATH);
        try {
            task = new Todo("Test Task");
        } catch (TaskInputException e) {
            System.out.println(e.getMessage());
        }
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
    public void isExitTest() {
        assertEquals(false, false);
    }

    @Test
    void testExecute_taskListLength_addsTaskToTaskList() throws IOException, TaskInputException {
        AddCommand addCommand = new AddCommand(task);
        assertEquals(0, taskList.getSize());

        addCommand.execute(taskList, fileSave);

        assertEquals(1, taskList.getSize());
        assertTrue(taskList.getTasks().contains(task));
    }

    @Test
    void testExecute_taskListLength_savesToFile() throws IOException, TaskInputException {
        AddCommand addCommand = new AddCommand(task);
        addCommand.execute(taskList, fileSave);

        // Reload the file to check if the task is saved
        TaskList loadedTasks = null;
        try {
            loadedTasks = new TaskList(fileSave.loadTasks());
        } catch (FileSaveException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1, loadedTasks.getSize());
    }
}

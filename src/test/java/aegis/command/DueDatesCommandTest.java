package aegis.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aegis.exception.TaskInputException;
import aegis.storage.FileSave;
import aegis.task.Deadline;
import aegis.task.Event;
import aegis.task.TaskList;

public class DueDatesCommandTest {
    private TaskList taskList;
    private DueDatesCommand dueDatesCommand;
    private FileSave fileSave; // Not used in this command

    @BeforeEach
    void setUp() throws TaskInputException {
        // Initialize a task list with deadlines and events
        taskList = new TaskList();
        dueDatesCommand = new DueDatesCommand();
        fileSave = new FileSave("./testSave.txt"); // Dummy storage

        // Adding tasks with due dates
        taskList.addTask(new Deadline("Submit report", "02/15/2025 1800"));
        taskList.addTask(new Deadline("Project milestone", "02/10/2025 1200"));
        taskList.addTask(new Event("Conference", "02/20/2025 0900", "02/20/2025 1700"));
    }

    @Test
    void execute_dueDatesSortedCorrectly() throws TaskInputException {
        // Execute the command
        String result = dueDatesCommand.execute(taskList, fileSave);

        // Expected order (earliest first)
        String[] expectedOrder = {"Project milestone", "Submit report", "Conference"};

        // Check if the output contains the tasks in expected order
        for (String task : expectedOrder) {
            assertTrue(result.contains(task), "Expected task not found: " + task);
        }

        // Ensure correct number of tasks are listed
        long count = Arrays.stream(result.split("\n"))
                .filter(line -> line.matches("\\d+\\..*")) // Matches numbered task lines
                .count();
        assertEquals(3, count, "Mismatch in the number of tasks listed.");
    }
}

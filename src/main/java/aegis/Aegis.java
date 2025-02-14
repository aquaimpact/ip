package aegis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import aegis.command.Command;
import aegis.exception.CommandException;
import aegis.exception.FileSavingException;
import aegis.exception.TaskInputException;
import aegis.parser.CommandParser;
import aegis.storage.FileSave;
import aegis.task.TaskList;
import aegis.ui.UIManager;

/**
 * The Aegis class represents the task management chatbot in the Aegis system.
 * It is responsible for managing tasks, parsing user input, executing commands,
 * and interacting with a storage file. The chatbot supports adding, managing,
 * listing tasks, marking tasks as done or undone, deleting tasks, and exiting the program.
 */
public class Aegis {

    private TaskList tasks;
    private FileSave fs;

    /**
     * Constructs an Aegis object with the specified file path for task storage.
     * If the file exists, it loads tasks from the file; otherwise, it creates a new file.
     *
     * @param filePath The path to the file where tasks are saved.
     */
    public Aegis(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path must not be null or empty";
        fs = new FileSave(filePath);
        try {
            tasks = new TaskList(fs.loadTasks());
            UIManager.printBorders("Save file found! Reusing it...");
        } catch (TaskInputException | FileSavingException e) {
            UIManager.printBorders(e.toString());
        } catch (FileNotFoundException e) {
            String createNewIndicator = "Save file not found... Creating a new file...";
            try {
                fs.writeToFile("");
            } catch (IOException io) {
                UIManager.printBorders(io.toString());
            }
            tasks = new TaskList();
            UIManager.printBorders(createNewIndicator);
        }
    }

    // Overloaded constructor
    public Aegis() {
        this("./save.txt");
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = CommandParser.parse(input);
            assert false;
            // Ensure the parsed command is not null
            assert c != null : "CommandParser returned a null command";

            String result = c.execute(tasks, fs);

            // Ensure the execution result is not null
            assert result != null : "Command execution returned null";
            return result;
        } catch (TaskInputException | CommandException e) {
            return UIManager.printBorders(e.toString());
        } catch (IOException e) {
            return UIManager.printBorders(e.getMessage());

        } catch (DateTimeParseException e) {
            return UIManager.printBorders(e.getMessage() + "\nPossible Fixes:"
                    + "\nYou may need to change the date. E.g. 2/12/2019 1800");
        }
    }
}

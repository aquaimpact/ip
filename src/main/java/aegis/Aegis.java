package aegis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

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

    /**
     * Runs the chatbot, handling user input, parsing commands, and executing tasks.
     * The chatbot will continue to run until the exit command is triggered.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        String input;

        // Printing the logo and welcome message
        UIManager.welcomeMessage();

        boolean isExit = false;
        while (!isExit) {
            input = sc.nextLine();
            try {
                Command c = CommandParser.parse(input);
                c.execute(tasks, fs);
                isExit = c.isExit();
            } catch (TaskInputException | CommandException e) {
                UIManager.printBorders(e.toString());
            } catch (IOException e) {
                UIManager.printBorders(e.getMessage());
            } catch (DateTimeParseException e) {
                UIManager.printBorders(e.getMessage() + "\nPossible Fixes:"
                        + "\nYou may need to change the date. E.g. 2/12/2019 1800");
            }
        }
        sc.close();
    }

    /**
     * The main method that starts the Aegis chatbot. It creates an Aegis object
     * with a specified file path and runs the chatbot.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Aegis("./save.txt").run();
    }
}

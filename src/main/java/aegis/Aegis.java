package aegis;

import aegis.command.Command;
import aegis.exception.CommandException;
import aegis.exception.FileSavingException;
import aegis.exception.TaskInputException;
import aegis.parser.CommandParser;
import aegis.storage.FileSave;
import aegis.task.TaskList;
import aegis.ui.UIManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * aegis.Aegis is a task management chatbot that supports adding managing, and
 * listing tasks. It also allows marking tasks as done or undone, deleting tasks,
 * and exiting the program.
 */
public class Aegis {

    private TaskList tasks;
    private FileSave fs;

    public Aegis(String filePath) {
        fs = new FileSave(filePath);
        try {
            tasks = new TaskList(fs.loadTasks());
            UIManager.printBorders("Save file found! Reuisng it...");
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
                UIManager.printBorders(e.getMessage() + "\nPossible Fixes:\nYou may need to change the date. E.g. 2/12/2019 1800");
            }
        }
        sc.close();
    }


    public static void main(String[] args) {
        new Aegis("src/../save.txt").run();
    }
}
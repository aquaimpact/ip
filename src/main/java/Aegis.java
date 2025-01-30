import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Aegis is a task management chatbot that supports adding managing, and
 * listing tasks. It also allows marking tasks as done or undone, deleting tasks,
 * and exiting the program.
 */
public class Aegis {

    /**
     * Enum representing the types of commands supported by the application.
     */
    public enum CommandType {
        LIST, MARK, UNMARK, DELETE, BYE, TODO, DEADLINE, EVENT, DUEDATES
    }

    private static ArrayList<Task> tasks = new ArrayList<>();

    private static final String FILE_PATH = "src/../save.txt";

    /**
     * Prints out the text formatted with the boundary lines for users.
     * .
     * @param msg the text to be printed out
     */
    public static void printBorders(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(msg);
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints out a formatted line whenever a user adds an item to the list.
     *
     * @param task The task that was added.
     */
    public static void printOnItemsAdd(Task task){
        printBorders("Got it. I've added this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Determines the type of command based on the user's input
     *
     * @param  input The input string entered by the user.
     * @return The determined {@link CommandType}.
     * @throws CommandException If the command is invalid or unrecognized.
     */
    private static CommandType determineCommandType(String input) throws CommandException {
        if (input.matches(".*\\bbye\\b.*")) {
            return CommandType.BYE;
        }
        else if (input.matches(".*\\blist\\b.*")) {
            return CommandType.LIST;
        }
        else if (input.matches(".*\\bmark\\b.*")) {
            return CommandType.MARK;
        }
        else if (input.matches(".*\\bunmark\\b.*")) {
            return CommandType.UNMARK;
        }
        else if (input.matches(".*\\bdelete\\b.*")) {
            return CommandType.DELETE;
        }
        else if (input.matches(".*\\btodo\\b.*")) {
            return CommandType.TODO;
        }
        else if (input.matches(".*\\bdeadline\\b.*")) {
            return CommandType.DEADLINE;
        }
        else if (input.matches(".*\\bevent\\b.*")) {
            return CommandType.EVENT;
        }
        else if (input.matches(".*\\bduedates\\b.*")) {
            return CommandType.DUEDATES;
        }
        else throw new CommandException(input);
    }

    /**
     * Based on the CommandType, the input will be handled differently.
     * This method returns a boolean flag which indicates if the system
     * should get out of the loop and quit.
     *
     * @param  ct The type of command to be executed.
     * @param  input The input string associated with the command.
     * @return True if the program should exit, false otherwise.
     * @throws TaskInputException If the command input is invalid or incomplete.
     */
    private static boolean handleCommand(CommandType ct, String input) throws TaskInputException, DateTimeParseException {
        String[] inputArray = input.split(" ");
        boolean mustExit = false;
        switch (ct) {
            case BYE -> {
                printBorders("Goodbye! Thanks for using! Hope to see you again soon!");
                mustExit = true;
            }
            case LIST -> {
                String output = "Here are the tasks in your list:";
                for (int i = 1; i <= tasks.size(); i++) {
                    Task task = tasks.get(i - 1);
                    output += ("\n"+ i + "." + task.toString());
                }
                printBorders(output);
            }
            case MARK -> {
                // Error Handling
                if(inputArray.length == 1) throw new TaskInputException("You did not specify which task to mark done!");
                if(tasks.isEmpty()) throw new TaskInputException("No task available to mark!");

                //Marking the task
                Task task = tasks.get(Integer.parseInt(inputArray[1]) - 1);
                task.markAsDone();
                printBorders("Nice! I've marked this task as done:\n" + task);
            }
            case UNMARK -> {
                // Error Handling
                if(inputArray.length == 1) throw new TaskInputException("You did not specify which task to unmark!");
                if(tasks.isEmpty()) throw new TaskInputException("No task available to unmark!");

                // Unmarking the task
                Task task = tasks.get(Integer.parseInt(inputArray[1]) - 1);
                task.markAsUndone();
                printBorders("OK, I've marked this task as not done yet:\n" + task);
            }
            case DELETE -> {
                // Error Handling
                if(inputArray.length == 1) throw new TaskInputException("You did not specify which task to delete!");
                if(tasks.isEmpty()) throw new TaskInputException("No task available to delete!");

                // Deleting the task
                Task task = tasks.get(Integer.parseInt(inputArray[1]) - 1);
                tasks.remove(Integer.parseInt(inputArray[1]) - 1);
                printBorders("Noted. I've removed this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.");
            }
            case TODO -> {
                String res = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));
                Task todoItm = new Todo(res);
                tasks.add(todoItm);
                printOnItemsAdd(todoItm);
            }
            case DEADLINE -> {
                String[] res = input.split(" /by ");
                if(res.length < 2) throw new TaskInputException("You did not specify a by date!"); // Error Handling
                String taskName = res[0].substring(8).trim();
                String by = res[1].trim();
                Task deadlineItm = new Deadline(taskName, by);
                tasks.add(deadlineItm);
                printOnItemsAdd(deadlineItm);
            }
            case EVENT -> {
                String[] res = input.split(" /from | /to ");
                if(res.length < 3) throw new TaskInputException("You did not specify a from or to date!"); // Error Handling
                String taskName = res[0].substring(5).trim();
                String from = res[1].trim();
                String to = res[2].trim();
                Task eventItm = new Event(taskName, from, to);
                tasks.add(eventItm);
                printOnItemsAdd(eventItm);
            }
            case DUEDATES -> {
                ArrayList<Task> tasksCopy = new ArrayList<>(tasks);
                Collections.sort(tasksCopy);
                String output = "Here are the upcoming duedates in your list:";
                for (int i = 1; i <= tasksCopy.size(); i++) {
                    Task task = tasksCopy.get(i - 1);
                    if(task instanceof Todo) continue;
                    output += ("\n"+ i + "." + task.toString());
                }
                printBorders(output);
            }
        }
        return mustExit;
    }

    public static void main(String[] args) {

        //Loading File
        try {
            tasks = FileSave.loadTasks(FILE_PATH);
            printBorders("Save file found! Reuisng it...");
        } catch (TaskInputException | FileSavingException e) {
            printBorders(e.toString());
        } catch (FileNotFoundException e) {
            String createNewIndicator = "Save file not found... Creating a new file...";
            try {
                FileSave.writeToFile(FILE_PATH, "");
            } catch (IOException io) {
                printBorders(io.toString());
            }
            printBorders(createNewIndicator);
        }

        String logo = """
                     █████╗ ███████╗ ██████╗ ██╗███████╗
                    ██╔══██╗██╔════╝██╔════╝ ██║██╔════╝
                    ███████║███████╗██║  ███╗██║███████╗
                    ██╔══██║██╔════╝██║   ██║██║╚════██║
                    ██║  ██║███████║╚██████╔╝██║███████║
                    ╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═╝╚══════╝
                """;

        Scanner sc = new Scanner(System.in);
        String input;

        // Printing the logo and welcome message
        System.out.println(logo);
        printBorders("Hello! I'm AEGIS\nWhat can I do for you?");

        while (true) {
            input = sc.nextLine();
            try {
                CommandType commandType = determineCommandType(input);
                boolean mustExit = handleCommand(commandType, input);
                FileSave.writeToFile(FILE_PATH, tasks);
                if(mustExit) break;
            } catch (TaskInputException | CommandException e) {
                printBorders(e.toString());
            } catch (IOException e) {
                printBorders(e.getMessage());
            } catch (DateTimeParseException e) {
                printBorders(e.getMessage() + "\nPossible Fixes:\nYou may need to change the date. E.g. 2/12/2019 1800");
            }
        }
        sc.close();
    }
}
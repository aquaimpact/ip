import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CommandParser {
    /**
     * Determines the type of command based on the user's input
     *
     * @param  input The input string entered by the user.
     * @return The determined {@link Aegis.CommandType}.
     * @throws CommandException If the command is invalid or unrecognized.
     */
    public static Aegis.CommandType determineCommandType(String input) throws CommandException {
        if (input.matches(".*\\bbye\\b.*")) {
            return Aegis.CommandType.BYE;
        }
        else if (input.matches(".*\\blist\\b.*")) {
            return Aegis.CommandType.LIST;
        }
        else if (input.matches(".*\\bmark\\b.*")) {
            return Aegis.CommandType.MARK;
        }
        else if (input.matches(".*\\bunmark\\b.*")) {
            return Aegis.CommandType.UNMARK;
        }
        else if (input.matches(".*\\bdelete\\b.*")) {
            return Aegis.CommandType.DELETE;
        }
        else if (input.matches(".*\\btodo\\b.*")) {
            return Aegis.CommandType.TODO;
        }
        else if (input.matches(".*\\bdeadline\\b.*")) {
            return Aegis.CommandType.DEADLINE;
        }
        else if (input.matches(".*\\bevent\\b.*")) {
            return Aegis.CommandType.EVENT;
        }
        else if (input.matches(".*\\bduedates\\b.*")) {
            return Aegis.CommandType.DUEDATES;
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
    public static boolean handleCommand(TaskList taskList, Aegis.CommandType ct, String input) throws TaskInputException, DateTimeParseException {
        String[] inputArray = input.split(" ");
        boolean mustExit = false;
        switch (ct) {
            case BYE -> {
                UIManager.quitMessage();
                mustExit = true;
            }
            case LIST -> {
                ArrayList<Task> tasks = taskList.getTasks();
                String output = "Here are the tasks in your list:";
                for (int i = 1; i <= tasks.size(); i++) {
                    Task task = tasks.get(i - 1);
                    output += ("\n"+ i + "." + task.toString());
                }
                UIManager.printBorders(output);
            }
            case MARK -> {
                // Error Handling
                if(inputArray.length == 1) throw new TaskInputException("You did not specify which task to mark done!");

                //Marking the task
                taskList.markTaskAsDone(Integer.parseInt(inputArray[1]) - 1);
                Task t = taskList.getTask(Integer.parseInt(inputArray[1]) - 1);
                UIManager.printBorders("Nice! I've marked this task as done:\n" + t);
            }
            case UNMARK -> {
                // Error Handling
                if(inputArray.length == 1) throw new TaskInputException("You did not specify which task to unmark!");

                // Unmarking the task
                taskList.markTaskAsUndone(Integer.parseInt(inputArray[1]) - 1);
                Task t = taskList.getTask(Integer.parseInt(inputArray[1]) - 1);
                UIManager.printBorders("OK, I've marked this task as not done yet:\n" + t);
            }
            case DELETE -> {
                // Error Handling
                if(inputArray.length == 1) throw new TaskInputException("You did not specify which task to delete!");

                // Deleting the task
                Task t = taskList.removeTask(Integer.parseInt(inputArray[1]) - 1);
                UIManager.printBorders("Noted. I've removed this task:\n" + t + "\nNow you have " + taskList.getTasks().size() + " tasks in the list.");
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
                UIManager.printBorders(output);
            }
        }
        return mustExit;
    }
}

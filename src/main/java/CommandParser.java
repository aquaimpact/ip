import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CommandParser {

    /**
     * Enum representing the types of commands supported by the application.
     */
    private enum CommandType {
        LIST, MARK, UNMARK, DELETE, BYE, TODO, DEADLINE, EVENT, DUEDATES
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
    private static Command handleCommand(TaskList taskList, CommandType ct, String input) throws TaskInputException, DateTimeParseException {
        String[] inputArray = input.split(" ");
        boolean mustExit = false;
        switch (ct) {
            case BYE -> {
                UIManager.quitMessage();
                mustExit = true;
            }
            case LIST -> {
                return new ListCommand();
            }
            case MARK -> {
                return new MarkOrUnmarkCommand(true, Integer.parseInt(inputArray[1]) - 1);
            }
            case UNMARK -> {
                return new MarkOrUnmarkCommand(false, Integer.parseInt(inputArray[1]) - 1);
            }
            case DELETE -> {
                return new DeleteClass(Integer.parseInt(inputArray[1]) - 1);
            }
            case TODO -> {
                String res = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));
                Task todoItm = new Todo(res);

                taskList.addTask(todoItm);
                UIManager.printOnItemsAdd(todoItm, taskList.getTasks().size());
            }
            case DEADLINE -> {
                String[] res = input.split(" /by ");
                if(res.length < 2) throw new TaskInputException("You did not specify a by date!"); // Error Handling
                String taskName = res[0].substring(8).trim();
                String by = res[1].trim();
                Task deadlineItm = new Deadline(taskName, by);

                taskList.addTask(deadlineItm);
                UIManager.printOnItemsAdd(deadlineItm, taskList.getTasks().size());
            }
            case EVENT -> {
                String[] res = input.split(" /from | /to ");
                if(res.length < 3) throw new TaskInputException("You did not specify a from or to date!"); // Error Handling
                String taskName = res[0].substring(5).trim();
                String from = res[1].trim();
                String to = res[2].trim();
                Task eventItm = new Event(taskName, from, to);

                taskList.addTask(eventItm);
                UIManager.printOnItemsAdd(eventItm, taskList.getTasks().size());
            }
            case DUEDATES -> {
                ArrayList<Task> sortedTasks = taskList.getSortedDueDates();
                String output = "Here are the upcoming duedates in your list:";
                for (int i = 1; i <= sortedTasks.size(); i++) {
                    Task task = sortedTasks.get(i - 1);
                    if(task instanceof Todo) continue;
                    output += ("\n"+ i + "." + task.toString());
                }
                UIManager.printBorders(output);
            }
        }
        return mustExit;
    }

    public static boolean parseCommand (TaskList taskList, String input) throws CommandException, TaskInputException {
        CommandType commandType = determineCommandType(input);
        return handleCommand(taskList, commandType, input);
    }
}

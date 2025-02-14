package aegis.parser;

import java.time.format.DateTimeParseException;
import java.util.Arrays;

import aegis.command.AddCommand;
import aegis.command.Command;
import aegis.command.DeleteCommand;
import aegis.command.DueDatesCommand;
import aegis.command.ExitCommand;
import aegis.command.FindCommand;
import aegis.command.ListCommand;
import aegis.command.MarkOrUnmarkCommand;
import aegis.exception.CommandException;
import aegis.exception.TaskInputException;
import aegis.task.Deadline;
import aegis.task.Event;
import aegis.task.Task;
import aegis.task.Todo;

/**
 * The CommandParser class is responsible for parsing user input into commands.
 * It determines the type of command based on the input string and returns the corresponding command object.
 */
public class CommandParser {

    /**
     * Enum representing the types of commands supported by the application.
     */
    private enum CommandType {
        LIST, MARK, UNMARK, DELETE, BYE, TODO, DEADLINE, EVENT, DUEDATES, FIND
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
        } else if (input.matches(".*\\blist\\b.*")) {
            return CommandType.LIST;
        } else if (input.matches(".*\\bmark\\b.*")) {
            return CommandType.MARK;
        } else if (input.matches(".*\\bunmark\\b.*")) {
            return CommandType.UNMARK;
        } else if (input.matches(".*\\bdelete\\b.*")) {
            return CommandType.DELETE;
        } else if (input.matches(".*\\btodo\\b.*")) {
            return CommandType.TODO;
        } else if (input.matches(".*\\bdeadline\\b.*")) {
            return CommandType.DEADLINE;
        } else if (input.matches(".*\\bevent\\b.*")) {
            return CommandType.EVENT;
        } else if (input.matches(".*\\bduedates\\b.*")) {
            return CommandType.DUEDATES;
        } else if (input.matches(".*\\bfind\\b.*")) {
            return CommandType.FIND;
        } else {
            throw new CommandException(input);
        }
    }

    /**
     * Based on the CommandType, the input will be handled differently.
     * This method returns a boolean flag which indicates if the system
     * should get out of the loop and quit.
     *
     * @param  input The input string associated with the command.
     * @return True if the program should exit, false otherwise.
     * @throws TaskInputException If the command input is invalid or incomplete.
     */
    public static Command parse(String input) throws TaskInputException, DateTimeParseException, CommandException {
        String[] inputArray = input.split(" ");
        CommandType commandType = determineCommandType(input);

        switch (commandType) {
        case BYE:
            return new ExitCommand();

        case LIST:
            return new ListCommand();

        case MARK:
            if (inputArray.length == 1) {
                throw new TaskInputException("You did not specify which task to mark done!");
            }
            return new MarkOrUnmarkCommand(true, Integer.parseInt(inputArray[1]) - 1);

        case UNMARK:
            if (inputArray.length == 1) {
                throw new TaskInputException("You did not specify which task to unmark!");
            }
            return new MarkOrUnmarkCommand(false, Integer.parseInt(inputArray[1]) - 1);

        case DELETE:
            if (inputArray.length == 1) {
                throw new TaskInputException("You did not specify which task to delete!");
            }
            return new DeleteCommand(Integer.parseInt(inputArray[1]) - 1);

        case TODO:
            String todoDescription = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));
            Task todoItem = new Todo(todoDescription);
            return new AddCommand(todoItem);
        case DEADLINE:
            String[] deadlineParts = input.split(" /by ");
            if (deadlineParts.length < 2) {
                throw new TaskInputException("You did not specify a by date!");
            }
            String deadlineDescription = deadlineParts[0].substring(8).trim();
            String deadlineDate = deadlineParts[1].trim();
            Task deadlineItem = new Deadline(deadlineDescription, deadlineDate);
            return new AddCommand(deadlineItem);

        case EVENT:
            String[] eventParts = input.split(" /from | /to ");
            if (eventParts.length < 3) {
                throw new TaskInputException("You did not specify a from or to date!");
            }
            String eventDescription = eventParts[0].substring(5).trim();
            String eventFrom = eventParts[1].trim();
            String eventTo = eventParts[2].trim();
            Task eventItem = new Event(eventDescription, eventFrom, eventTo);
            return new AddCommand(eventItem);

        case DUEDATES:
            return new DueDatesCommand();

        case FIND:
            String searchTerm = input.substring(4).trim();
            return new FindCommand(searchTerm);
        default:
            throw new CommandException("Unrecognized command: " + input);
        }
    }
}

package aegis.ui;

import aegis.task.Task;

/**
 * Represents the user interface manager for the aegis.Aegis chatbot.
 * The UIManager handles displaying messages, borders, and task-related outputs to the user.
 */
public class UIManager {

    /**
     * Displays a welcome message to the user, including a logo and introductory message.
     */
    public static void welcomeMessage() {
        String logo = """
                     █████╗ ███████╗ ██████╗ ██╗███████╗
                    ██╔══██╗██╔════╝██╔════╝ ██║██╔════╝
                    ███████║███████╗██║  ███╗██║███████╗
                    ██╔══██║██╔════╝██║   ██║██║╚════██║
                    ██║  ██║███████║╚██████╔╝██║███████║
                    ╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═╝╚══════╝
                """;
        System.out.println(logo);
        printBorders("Hello! I'm AEGIS\nWhat can I do for you?");
    }

    /**
     * Displays a quit message to the user, thanking them for using the application.
     */
    public static void quitMessage() {
        printBorders("Goodbye! Thanks for using! Hope to see you again soon!");
    }

    /**
     * Prints out the provided message with boundary lines, giving it a framed appearance.
     *
     * @param msg The message to be printed out.
     */
    public static void printBorders(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(msg);
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints out a message confirming the addition of a new task to the task list.
     * It displays the added task and the new size of the task list.
     *
     * @param task The task that was added.
     * @param newArraySize The new size of the task list after the task was added.
     */
    public static void printOnItemsAdd(Task task, int newArraySize) {
        printBorders("Got it. I've added this task:\n"
                + task + "\nNow you have " + newArraySize + " tasks in the list.");
    }
}

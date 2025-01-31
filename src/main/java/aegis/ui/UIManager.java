package aegis.ui;

import aegis.task.Task;

public class UIManager {
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

    public static void quitMessage() {
        printBorders("Goodbye! Thanks for using! Hope to see you again soon!");
    }

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
    public static void printOnItemsAdd(Task task, int newArraySize){
        printBorders("Got it. I've added this task:\n" + task + "\nNow you have " + newArraySize + " tasks in the list.");
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class Aegis {
    public static void main(String[] args) {
        String logo = """
                     █████╗ ███████╗ ██████╗ ██╗███████╗
                    ██╔══██╗██╔════╝██╔════╝ ██║██╔════╝
                    ███████║███████╗██║  ███╗██║███████╗
                    ██╔══██║██╔════╝██║   ██║██║╚════██║
                    ██║  ██║███████║╚██████╔╝██║███████║
                    ╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═╝╚══════╝
                """;


        String welcomeMessage = """
                ____________________________________________________________
                 Hello! I'm AEGIS
                 What can I do for you?
                ____________________________________________________________
                """;

        String exitMessage = """
                ____________________________________________________________
                 Goodbye! Thanks for using! Hope to see you again soon!
                ____________________________________________________________
                """;

        Scanner sc = new Scanner(System.in);
        String input;

        //User setup
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println(logo + "\n" + welcomeMessage);

        while (true) {
            input = sc.nextLine();
            String[] inputArray = input.split(" ");

            // Main Commands
            if (inputArray[0].equals("bye")) {
                System.out.println(exitMessage);
                break;
            } else if (inputArray[0].equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= tasks.size(); i++) {
                    Task task = tasks.get(i - 1);
                    System.out.println(i + ".[" + task.getStatusIcon() + "] " + task.getTaskName());
                }
                System.out.println("____________________________________________________________\n");
            } else if (inputArray[0].equals("mark")) {
                Task task = tasks.get(Integer.parseInt(inputArray[1]) - 1);
                task.markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + task.getStatusIcon() + "] " + task.getTaskName());
                System.out.println("____________________________________________________________\n");
            } else if (inputArray[0].equals("unmark")) {
                Task task = tasks.get(Integer.parseInt(inputArray[1]) - 1);
                task.markAsUndone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + task.getStatusIcon() + "] " + task.getTaskName());
                System.out.println("____________________________________________________________\n");
            } else {
                System.out.println("____________________________________________________________\n" +
                        " You have added: \"" + input + "\"\n" +
                        "____________________________________________________________\n");
                tasks.add(new Task(input));

            }
        }
        sc.close();
    }
}

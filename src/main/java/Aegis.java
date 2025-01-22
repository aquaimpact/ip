import java.util.ArrayList;
import java.util.Arrays;
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
                ____________________________________________________________""";

        Scanner sc = new Scanner(System.in);
        String input;

        //User setup
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println(logo + "\n" + welcomeMessage);

        while (true) {
            input = sc.nextLine();
            String[] inputArray = input.split(" ");

            // Main Commands
            if (input.matches(".*\\bbye\\b.*")) {
                System.out.println(exitMessage);
                break;
            } else if (input.matches(".*\\blist\\b.*")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= tasks.size(); i++) {
                    Task task = tasks.get(i - 1);
                    System.out.println(i + "." + task.toString());
                }
                System.out.println("____________________________________________________________\n");
            } else if (input.matches(".*\\bmark\\b.*")) {
                Task task = tasks.get(Integer.parseInt(inputArray[1]) - 1);
                task.markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);
                System.out.println("____________________________________________________________\n");
            } else if (input.matches(".*\\bunmark\\b.*")) {
                Task task = tasks.get(Integer.parseInt(inputArray[1]) - 1);
                task.markAsUndone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task);
                System.out.println("____________________________________________________________\n");
            } else if (input.matches(".*\\btodo\\b.*")) {
                String res = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));
                Task todoItm = new Todo(res);
                tasks.add(todoItm);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(todoItm);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________\n");
            } else if (input.matches(".*\\bdeadline\\b.*")) {
                String[] res = input.split(" /by ");
                String taskName = res[0].substring(9).trim();
                String by = res[1].trim();
                Task deadlineItm = new Deadline(taskName, by);
                tasks.add(deadlineItm);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(deadlineItm);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________\n");
            } else if (input.matches(".*\\bevent\\b.*")) {
                String[] res = input.split(" /from | /to ");
                String taskName = res[0].substring(6).trim();
                String from = res[1].trim();
                String to = res[2].trim();
                Task eventItm = new Event(taskName, from , to);
                tasks.add(eventItm);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(eventItm);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________\n");
            }
        }
        sc.close();
    }
}

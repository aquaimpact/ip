import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Aegis {

    public static void printBorders(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(msg);
        System.out.println("____________________________________________________________\n");
    }


    public static void printOnItemsAdd(Task task, int size){
        printBorders("Got it. I've added this task:\n" + task + "\nNow you have " + size + " tasks in the list.");
    }

    public static void main(String[] args) {
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

        //User setup
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println(logo);
        printBorders("Hello! I'm AEGIS\nWhat can I do for you?");

        while (true) {
            input = sc.nextLine();
            String[] inputArray = input.split(" ");
            try {
                // Main Commands
                if (input.matches(".*\\bbye\\b.*")) {
                    printBorders("Goodbye! Thanks for using! Hope to see you again soon!");
                    break;
                }
                else if (input.matches(".*\\blist\\b.*")) {
                    String output = "Here are the tasks in your list:";
                    for (int i = 1; i <= tasks.size(); i++) {
                        Task task = tasks.get(i - 1);
                        output += ("\n"+ i + "." + task.toString());
                    }
                    printBorders(output);
                }
                else if (input.matches(".*\\bmark\\b.*")) {
                    if(inputArray.length == 1) throw new TaskInputException("You did not specify which task to mark done!");
                    if(tasks.isEmpty()) throw new TaskInputException("No task available to mark!");
                    Task task = tasks.get(Integer.parseInt(inputArray[1]) - 1);
                    task.markAsDone();
                    printBorders("Nice! I've marked this task as done:\n" + task);
                }
                else if (input.matches(".*\\bunmark\\b.*")) {
                    if(inputArray.length == 1) throw new TaskInputException("You did not specify which task to unmark!");
                    if(tasks.isEmpty()) throw new TaskInputException("No task available to unmark!");
                    Task task = tasks.get(Integer.parseInt(inputArray[1]) - 1);
                    task.markAsUndone();
                    printBorders("OK, I've marked this task as not done yet:\n" + task);
                }
                else if (input.matches(".*\\bdelete\\b.*")) {
                    if(inputArray.length == 1) throw new TaskInputException("You did not specify which task to delete!");
                    if(tasks.isEmpty()) throw new TaskInputException("No task available to delete!");
                    Task task = tasks.get(Integer.parseInt(inputArray[1]) - 1);
                    tasks.remove(Integer.parseInt(inputArray[1]) - 1);
                    printBorders("Noted. I've removed this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.");
                }
                else if (input.matches(".*\\btodo\\b.*")) {
                    String res = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));
                    Task todoItm = new Todo(res);
                    tasks.add(todoItm);
                    printOnItemsAdd(todoItm, tasks.size());
                }
                else if (input.matches(".*\\bdeadline\\b.*")) {
                    String[] res = input.split(" /by ");
                    if(res.length < 2) throw new TaskInputException("You did not specify a by date!");
                    String taskName = res[0].substring(8).trim();
                    String by = res[1].trim();
                    Task deadlineItm = new Deadline(taskName, by);
                    tasks.add(deadlineItm);
                    printOnItemsAdd(deadlineItm, tasks.size());
                }
                else if (input.matches(".*\\bevent\\b.*")) {
                    String[] res = input.split(" /from | /to ");
                    if(res.length < 3) throw new TaskInputException("You did not specify a from or to date!");
                    String taskName = res[0].substring(5).trim();
                    String from = res[1].trim();
                    String to = res[2].trim();
                    Task eventItm = new Event(taskName, from, to);
                    tasks.add(eventItm);
                    printOnItemsAdd(eventItm, tasks.size());
                }
                else throw new CommandException(input);
            } catch (TaskInputException | CommandException t) {
                printBorders(t.toString());
            }
        }
        sc.close();
    }
}
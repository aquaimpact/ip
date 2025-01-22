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
        ArrayList<String> commands = new ArrayList<>();

        System.out.println(logo + "\n" + welcomeMessage);

        while (true) {
            input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println(exitMessage);
                break;
            }
            else if(input.equals("list")) {
                System.out.println("\n____________________________________________________________");
                System.out.println("Here are the current list of items:");
                for(int i = 1; i <= commands.size(); i++) {
                    System.out.println(i + ". " + commands.get(i-1));
                }
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("\n____________________________________________________________" +
                        " You have added: \"" + input + "\"\n" +
                        "____________________________________________________________\n");
                commands.add(input);
            }
        }
        sc.close();
    }
}

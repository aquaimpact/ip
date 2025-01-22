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


        System.out.println(logo + "\n" + welcomeMessage);

        while (true) {
            input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println(exitMessage);
                break;
            }

            System.out.println("____________________________________________________________\n" +
                               " You have input: \"" + input +"\"\n" +
                               "____________________________________________________________");
        }
        sc.close();
    }
}

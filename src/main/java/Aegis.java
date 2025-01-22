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
         Bye. Hope to see you again soon!
        ____________________________________________________________
        """;
        System.out.println(logo);
        System.out.println(welcomeMessage + exitMessage);

    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileSave {

    public static void writeToFile(String filepath, String text) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        fw.write(text);
        fw.close();
    }

    public static void writeToFile(String filepath, ArrayList<Task> task) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        String finalStr = "";
        for(Task t: task) {
            finalStr += t.toCSV() + System.lineSeparator();
        }
        fw.write(finalStr);
        fw.close();
    }

    public static boolean appendToFile(String filepath, String text) throws IOException {
        FileWriter fw = new FileWriter(filepath, true);
        fw.write(text);
        fw.close();
        return true;
    }

    public static ArrayList<Task> loadTasks(String filepath) throws FileNotFoundException, TaskInputException, FileSavingException {
        File f = new File(filepath);
        Scanner s = new Scanner(f);

        ArrayList<Task> arr = new ArrayList<>();

        while(s.hasNext()){
            String[] taskData = s.nextLine().split("\\|\\|");

            if(taskData.length == 1) throw new FileSavingException("Cannot load task! Possibly wrong file format?");
            Task t;
            if (taskData[0].equals("T")) {
                t = new Todo(taskData[2]);
            } else if (taskData[0].equals("D")) {
                t = new Deadline(taskData[2], taskData[3]);
            } else if (taskData[0].equals("E")){
                t = new Event(taskData[2], taskData[3], taskData[4]);
            } else {
                throw new FileSavingException("Task of type: " + taskData[0] + " cannot be found!");
            }

            int isMarked = Integer.parseInt(taskData[1]);
            if (isMarked == 1) t.markAsDone();
            else t.markAsUndone();

            arr.add(t);
        }

        return arr;
    }
}

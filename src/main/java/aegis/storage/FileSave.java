package aegis.storage;

import aegis.exception.FileSavingException;
import aegis.exception.TaskInputException;
import aegis.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileSave {

    private String filePath;

    public FileSave (String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(text);
        fw.close();
    }

    public void writeToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String finalStr = "";
        for(Task t: taskList.getTasks()) {
            finalStr += t.toCSV() + System.lineSeparator();
        }
        fw.write(finalStr);
        fw.close();
    }

    public boolean appendToFile(String filepath, String text) throws IOException {
        FileWriter fw = new FileWriter(filepath, true);
        fw.write(text);
        fw.close();
        return true;
    }

    public ArrayList<Task> loadTasks() throws FileNotFoundException, TaskInputException, FileSavingException, DateTimeParseException {
        File f = new File(filePath);
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
                throw new FileSavingException("aegis.task.Task of type: " + taskData[0] + " cannot be found!");
            }

            int isMarked = Integer.parseInt(taskData[1]);
            if (isMarked == 1) t.markAsDone();
            else t.markAsUndone();

            arr.add(t);
        }

        return arr;
    }
}

package aegis.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import aegis.exception.FileSaveException;
import aegis.exception.TaskInputException;
import aegis.task.Deadline;
import aegis.task.Event;
import aegis.task.Task;
import aegis.task.TaskList;
import aegis.task.Todo;

/**
 * The FileSave class handles all file operations related to saving and loading tasks.
 * It provides methods to write tasks to a file, append text to an existing file, and load tasks from a saved file.
 */

public class FileSave {

    private String filePath;

    /**
     * Constructor to initialize the file path where tasks will be stored.
     *
     * @param filePath The file path to store the task data.
     */
    public FileSave(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the task list to the file specified by the filePath in CSV format.
     * Each task is represented by its CSV string format.
     *
     * @param taskList The list of tasks to write to the file.
     * @throws IOException If an I/O error occurs during writing.
     */
    public void writeToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String finalStr = "";
        for (Task t: taskList.getTasks()) {
            finalStr += t.toCsv() + System.lineSeparator();
        }
        fw.write(finalStr);
        fw.close();
    }

    /**
     * Loads tasks from the file specified by the file path.
     * Each task is parsed from its CSV representation in the file.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws FileNotFoundException If the file does not exist.
     * @throws TaskInputException If there is an issue with the task input format.
     * @throws FileSaveException If the file format is incorrect or the task type cannot be identified.
     * @throws DateTimeParseException If a date field is incorrectly formatted.
     */
    public ArrayList<Task> loadTasks() throws IOException, TaskInputException, FileSaveException, DateTimeParseException {
        File f = new File(filePath);
        if (!f.exists()) {
            File dir = new File("data");
            dir.mkdirs();
            f = new File(dir, "AegisSave.txt");
            f.createNewFile();
        }

        Scanner s = new Scanner(f);

        ArrayList<Task> tasks = new ArrayList<>();

        while (s.hasNext()) {
            String[] taskInputs = s.nextLine().split("\\|\\|");

            if (taskInputs.length == 1) {
                throw new FileSaveException("Cannot load task! Possibly wrong file format?");
            }

            Task t;
            if (taskInputs[0].equals("T")) {
                t = new Todo(taskInputs[2]);
            } else if (taskInputs[0].equals("D")) {
                t = new Deadline(taskInputs[2], taskInputs[3]);
            } else if (taskInputs[0].equals("E")) {
                t = new Event(taskInputs[2], taskInputs[3], taskInputs[4]);
            } else {
                throw new FileSaveException("aegis.task.Task of type: " + taskInputs[0] + " cannot be found!");
            }

            int isMarked = Integer.parseInt(taskInputs[1]);
            if (isMarked == 1) {
                t.markAsDone();
            } else {
                t.markAsUndone();
            }

            tasks.add(t);
        }

        return tasks;
    }
}

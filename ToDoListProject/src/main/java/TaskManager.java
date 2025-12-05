import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private final String FILE_NAME = "tasks.txt";

    public TaskManager() {
        loadFromFile();
    }


    private void renumberTasks() {
        int id = 1;
        for (Task t : tasks) {
            t.setId(id++);
        }
        saveToFile();
    }

    public void addTask(String desc) {
        tasks.add(new Task(0, desc));
        renumberTasks();
    }

    public boolean updateTask(int id, String newDesc) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setDescription(newDesc);
                renumberTasks();
                return true;
            }
        }
        return false;
    }

    public boolean markCompleted(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setCompleted(true);
                renumberTasks();
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                tasks.remove(t);
                renumberTasks();
                return true;
            }
        }
        return false;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public List<Task> getCompletedTasks() {
        List<Task> done = new ArrayList<>();
        for (Task t : tasks) {
            if (t.isCompleted()) done.add(t);
        }
        return done;
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Task t : tasks) {
                pw.println(t.getId() + "," + t.getDescription() + "," + t.isCompleted());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                Task t = new Task(Integer.parseInt(p[0]), p[1]);
                t.setCompleted(Boolean.parseBoolean(p[2]));
                tasks.add(t);
            }
            renumberTasks();
        } catch (Exception e) {
        }
    }
}

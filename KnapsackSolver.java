import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KnapsackSolver {
    public static List<Task> solve(List<Task> tasks, int maxTime) {
        List<FractionalTask> result = new ArrayList<>();
        List<Task> sortedTasks = new ArrayList<>(tasks);

        // Sort tasks by priority per time unit (descending)
        Collections.sort(sortedTasks, (a, b) -> Double.compare(
            (double)b.priority / b.time, (double)a.priority / a.time
        ));

        int remainingTime = maxTime;

        for (Task task : sortedTasks) {
            if (task.time <= remainingTime) {
                result.add(new FractionalTask(task, 1.0)); // full task
                remainingTime -= task.time;
            } else {
                double fraction = (double) remainingTime / task.time;
                result.add(new FractionalTask(task, fraction)); // partial task
                break;
            }
        }

        // Convert FractionalTasks to Task strings with adjusted labels
        List<Task> finalTasks = new ArrayList<>();
        for (FractionalTask ft : result) {
            if (ft.fraction == 1.0) {
                finalTasks.add(ft.task);
            } else {
                finalTasks.add(new Task(
                    ft.task.name + String.format(" (%.0f%%)", ft.fraction * 100),
                    (int)(ft.task.time * ft.fraction),
                    (int)(ft.task.priority * ft.fraction)
                ));
            }
        }

        return finalTasks;
    }
}

// Helper class to represent partially selected tasks
class FractionalTask {
    Task task;
    double fraction;

    public FractionalTask(Task task, double fraction) {
        this.task = task;
        this.fraction = fraction;
    }
}

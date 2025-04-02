import java.util.ArrayList;
import java.util.List;

public class KnapsackSolver {
    public static List<Task> solve(List<Task> tasks, int maxTime) {
        int n = tasks.size();
        int[][] dp = new int[n + 1][maxTime + 1];
        
        // Build DP table
        for (int i = 1; i <= n; i++) {
            Task task = tasks.get(i - 1);
            for (int w = 1; w <= maxTime; w++) {
                if (task.time <= w) {
                    dp[i][w] = Math.max(
                        dp[i - 1][w],
                        dp[i - 1][w - task.time] + task.priority
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        
        // Backtrack to find selected tasks
        List<Task> result = new ArrayList<>();
        int w = maxTime;
        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                Task task = tasks.get(i - 1);
                result.add(task);
                w -= task.time;
            }
        }
        
        return result;
    }
}
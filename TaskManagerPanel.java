import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class TaskManagerPanel extends JPanel {
    private List<Task> tasks = new ArrayList<>();
    private List<Task> optimalTasks = new ArrayList<>();
    
    public void addTask(Task task) {
        tasks.add(task);
        repaint();
    }
    
    public void calculateOptimalSchedule(int maxTime) {
        optimalTasks = KnapsackSolver.solve(tasks, maxTime);
        repaint();
    }
    
    public void reset() {
        tasks.clear();
        optimalTasks.clear();
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = 30;
        
        // Draw all tasks
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("All Tasks:", 10, y);
        y += 20;
        
        for (Task task : tasks) {
            boolean isSelected = optimalTasks.contains(task);
            g.setColor(isSelected ? Color.GREEN : Color.LIGHT_GRAY);
            g.fillRect(10, y, 200, 25);
            
            g.setColor(Color.BLACK);
            g.drawString(task.toString(), 15, y + 18);
            y += 30;
        }
        
        // Draw optimal solution
        if (!optimalTasks.isEmpty()) {
            y += 20;
            g.setColor(Color.BLACK);
            g.drawString("Optimal Schedule (" + getTotalTime(optimalTasks) + " hrs):", 10, y);
            y += 20;
            
            for (Task task : optimalTasks) {
                g.setColor(Color.GREEN);
                g.fillRect(10, y, 200, 25);
                
                g.setColor(Color.BLACK);
                g.drawString(task.toString(), 15, y + 18);
                y += 30;
            }
        }
        
        setPreferredSize(new Dimension(250, y));
    }
    
    private int getTotalTime(List<Task> tasks) {
        return tasks.stream().mapToInt(t -> t.time).sum();
    }
}
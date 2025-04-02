import java.awt.*;
import javax.swing.*;

public class KnapsackTimeManager extends JFrame {
    private TaskManagerPanel taskPanel;
    private JTextField timeField;
    private JButton addButton, calculateButton, resetButton;

    public KnapsackTimeManager() {
        setTitle("Knapsack Time Manager");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(1, 5));
        inputPanel.add(new JLabel("Task:"));
        JTextField taskField = new JTextField();
        inputPanel.add(taskField);
        
        inputPanel.add(new JLabel("Time (hrs):"));
        JTextField hoursField = new JTextField();
        inputPanel.add(hoursField);
        
        inputPanel.add(new JLabel("Priority (1-10):"));
        JTextField priorityField = new JTextField();
        inputPanel.add(priorityField);

        addButton = new JButton("Add Task");
        addButton.addActionListener(e -> {
            try {
                String name = taskField.getText();
                int time = Integer.parseInt(hoursField.getText());
                int priority = Integer.parseInt(priorityField.getText());
                taskPanel.addTask(new Task(name, time, priority));
                taskField.setText("");
                hoursField.setText("");
                priorityField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!");
            }
        });
        inputPanel.add(addButton);
        add(inputPanel, BorderLayout.NORTH);

        // Task display panel
        taskPanel = new TaskManagerPanel();
        add(new JScrollPane(taskPanel), BorderLayout.CENTER);

        // Control panel
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Available Time (hrs):"));
        timeField = new JTextField(5);
        controlPanel.add(timeField);
        
        calculateButton = new JButton("Optimize Schedule");
        calculateButton.addActionListener(e -> {
            try {
                int maxTime = Integer.parseInt(timeField.getText());
                taskPanel.calculateOptimalSchedule(maxTime);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid time!");
            }
        });
        controlPanel.add(calculateButton);
        
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> taskPanel.reset());
        controlPanel.add(resetButton);
        
        add(controlPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KnapsackTimeManager app = new KnapsackTimeManager();
            app.setVisible(true);
        });
    }
}
public class Task {
    String name;
    int time;
    int priority;
    
    public Task(String name, int time, int priority) {
        this.name = name;
        this.time = time;
        this.priority = priority;
    }
    
    @Override
    public String toString() {
        return String.format("%s (%d hrs, priority %d)", name, time, priority);
    }
}
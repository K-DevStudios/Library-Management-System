import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a task (ticket) in the project management system.
 * <p>
 *     Each task contains an ID, Title, description, priority level, and due date.
 *     The class is used to store and managed task related data within the project
 * </p>
 */

public class Task { //Objects created for Task
    int taskID;
    String title;
    String description;
    String priority;
    LocalDate dueDate;
    User assignedUser;

    /**
     * A task (ticket) in the project management system
     *
     * @param taskID the unique identifier for the task
     * @param title the title of the task
     * @param description a detailed description of the task
     * @param priority a priority level of the task
     * @param dueDate the due date for the task
     * @param assignedUser the user assigned to this task
     */

    public Task(int taskID, String title, String description, String priority, LocalDate dueDate, User assignedUser) {
        this.taskID = taskID; //ID number
        this.title = title; //Title
        this.description = description; //Description
        this.priority = priority; //Priority
        this.dueDate = dueDate; //Due Date
        this.assignedUser = assignedUser;
    }

    //Getters and Setters
    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    //Statement that loads at the end with all ticket credentials

    /**
     * Returns a formatted string representation of the task.
     *
     * @return formatted task details as a string.
     */
    @Override
    public String toString() {
        String userName;

        if (assignedUser == null) {
            userName = "Unassigned";
        } else {
            userName = assignedUser.toString();
        }

        return "Task ID: " + taskID +
                ", Title: " + title +
                ", Description: " + description +
                ", Priority: " + priority +
                ", Due Date: " + dueDate +
                ", Ticket Claimed by: " + userName;
    }
}
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the project management system
 * <p>
 * Each user has a unique ID, name, role, and a list of assigned tasks.
 * This class is responsible for assigning tasks to users.
 * </p>
 */
public class User {
    private final int userID;
    private String userName;
    private String userEmail;
    private String role;

    List<Task> tasks = new ArrayList<>();

    /**
     * Creates a new User instance
     *
     * @param userID the unique identifier for the user
     * @param userName the name of the user
     * @param userEmail the email address of the user
     * @param role the role of the user
     */

    //Constructors
    public User(int userID, String userName, String userEmail, String role) {
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.role = role;
    }

    /**
     * Gets the user ID
     * @return user ID number
     */

    //Getters and Setters
    public int getUserID() {
        return userID;
    }

    /**
     * Updates the user's name
     *
     * @return updated user name
     */

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Returns list of assigned task from specific user
     *
     * @return list of assigned tasks
     */

    public List<Task> getAssignedTasks(){ //Gets displayed list of tasks assigned to user
        return tasks;
    }

    /**
     * Assigns task to user
     *
     * @param task the task to remove
     * @return if the task was added, it will return true otherwise it will return null.
     */

    public boolean assignTask(Task task){ //assigns task to user
        if(task == null){
            return false;
        } else if(tasks.contains(task)){
            return false;
        }
        tasks.add(task);
        return true;
    }

    /**
     * Removes task for user list
     * @param task the task to remove
     * @return true if the task was successfully removed, false if task is null
     * or not assigned to the user
     */

    public boolean removeTask(Task task){ //removes task
        if(task == null){
            return false;
        } else if(tasks.contains(task)){
            tasks.remove(task);
            return true;
        }
        return false;
    }


}



import java.util.ArrayList;
import java.util.List;
/**
 * Provides search functionality for a collection of Task objects.
 * <p>
 *     This class allows filtering tasks based on keywords, task ID, task Title, and assigned user ID.
 *     Each method returns a list of matching tasks
 * </p>
 */

public class CustomSearch {
    public List<Task>searchByKeyword(String keyword, List<Task> tasks) {
        List<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(task);
            }
        } return results;
    }

    /**
     *
     * @param taskId the id of the task to search for
     * @param tasks tasks the list of tasks to search through
     * @return list of matching tasks (or an empty list if none)
     */

    public List<Task> searchByTaskID(int taskId, List<Task> tasks) {
        List<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskID() == taskId) {
                results.add(task);
            }
        } return results;
    }

    /**
     *
     * @param taskTitle the text to search for in task titles
     * @param tasks tasks the list of tasks to search through
     * @return a list of tasks whose descriptions match the keyword
     */

    public List<Task> searchByName(String taskTitle, List<Task> tasks) {
        List<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if(task.getTitle().toLowerCase().contains(taskTitle.toLowerCase())) {
                results.add(task);
            }
        } return results;
    }

    /**
     *
     * @param userID id of the user assigned to task
     * @param tasks the list of tasks to search through
     * @return a list containing the matching task, or empty if not found
     */

    public List<Task> searchByUserid(int userID, List<Task> tasks) {
        List<Task> results = new ArrayList<>();
        for (Task task : tasks) {
          if(task.getAssignedUser() != null) {
              if (task.getAssignedUser().getUserID() == userID) {
                  results.add(task);
              }
          }
        } return results;
    }
}

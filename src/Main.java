import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Starts console-based project maanagement system
 * <p>
 *     Displays a menu loop that handles user input and preforms CRUD operations
 *     on tasks.
 */
public class Main {
    static List<Project> projects = new ArrayList<Project>();
    public static void main(String[] args) {

        List<User> users = new ArrayList<User>();
        CustomSearch customSearch = new CustomSearch();

        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("*** KIRA: PROJECT MANAGEMENT SYSTEM ***");
            System.out.println("Please choose from options below:");
            System.out.println("1. Display project tickets");
            System.out.println("2. Create project ticket");
            System.out.println("3. Search project ticket");
            System.out.println("4. Clear project ticket");
            System.out.println("5. Update project ticket");
            System.out.println("6. Load project data");
            System.out.println("7. Exit");


            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if(projects.isEmpty()) {
                        System.out.println("There are no projects to display");
                    } else {
                        for(Project project : projects) {
                            System.out.println(project);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter Project ID: ");
                    int projectID = scanner.nextInt();
                    scanner.nextLine();

                    boolean found = false; //found set to false
                    for(Project project : projects) { //loops through project, if project with similar project ID is created
                        if(project.getProjectID() == projectID) { //program prints system error then breaks.
                            found = true;
                            break;
                        }
                    }
                    if(found) {
                        System.out.println("Project already exists with that ID"); //system comment after project is found
                        break;
                    }

                    System.out.println("Enter Project Name: ");
                    String projectName = scanner.nextLine();
                    if(projectName.equals("")) {
                        System.out.println("Please enter Project Name");
                    }

                    System.out.println("Enter Project Description: ");
                    String projectDescription = scanner.nextLine();
                    if(projectDescription.equals("")) {
                        System.out.println("Please enter Project Description");
                    }

                    LocalDate projectStartDate = null; //Date validation
                    while(projectStartDate == null) {
                        System.out.println("Enter Project Due Date (YYYY-MM-DD): ");
                        String startDate = scanner.nextLine();

                        try{
                            projectStartDate = LocalDate.parse(startDate); //catches input for date if date is invalid
                        } catch (DateTimeParseException e){
                            System.out.println("Please enter valid date (YYYY-MM-DD): ");
                        }
                    }


                    LocalDate projectEndDate = null;
                    while(projectEndDate == null) {
                        System.out.println("Enter Project Due Date (YYYY-MM-DD): ");
                        String endDate = scanner.nextLine();

                        try{
                            projectEndDate = LocalDate.parse(endDate); //catches input for date if date is invalid
                        } catch (DateTimeParseException e){
                            System.out.println("Please enter valid date (YYYY-MM-DD): ");
                        }
                    }

                    Project newProject = new Project(
                            projectID,
                            projectName,
                            projectDescription,
                            projectStartDate,
                            projectEndDate,
                            null
                    );

                    projects.add(newProject);
                    System.out.println("Project created successfully!");
                    break;
                case 3:
                    List<Task> allTasks = new ArrayList<>();
                    for (Project project : projects) {
                        allTasks.addAll(project.getTasks());
                    }
                        if(allTasks.isEmpty()) {
                            System.out.println("There are no tasks to search");
                        } else{
                            System.out.println("Select search option:");
                            System.out.println("1. Search by keyword");
                            System.out.println("2. Search by task ID");
                            System.out.println("3. Search by task name");
                            System.out.println("4. Search by user ID");
                            int choice3 = scanner.nextInt();
                            scanner.nextLine();
                            List<Task> searchResults = new ArrayList<>();
                            switch (choice3) {
                                case 1:
                                    System.out.println("Enter keyword: ");
                                    String keyword = scanner.nextLine();
                                    searchResults = customSearch.searchByKeyword(keyword, allTasks);
                                    break;
                                case 2:
                                    System.out.println("Enter task ID: ");
                                    int taskID = scanner.nextInt();
                                    searchResults = customSearch.searchByTaskID(taskID, allTasks);
                                    break;
                                case 3:
                                    System.out.println("Enter task name: ");
                                    String taskName = scanner.nextLine();
                                    searchResults = customSearch.searchByName(taskName, allTasks);
                                    break;
                                case 4:
                                    System.out.println("Enter user ID: ");
                                    int userID = scanner.nextInt();
                                    searchResults = customSearch.searchByUserid(userID, allTasks);
                                    break;
                            }
                            if(searchResults.isEmpty()) {
                                System.out.println("There are no tasks to search");
                            } else{
                                for(Task searchResult : searchResults) {
                                    System.out.println(searchResult);
                                }
                            }
                        }
                    break;
                case 4: {
                    if(projects.isEmpty()) {
                        System.out.println("No projects available. Create or load a project first");
                    } else {
                        System.out.println("Enter Project ID:");
                        int projectID2 = scanner.nextInt();
                        scanner.nextLine();

                        Project selectedProject = null;
                        for(Project project : projects) {
                            if(project.getProjectID() == projectID2) {
                                selectedProject = project;
                                break;
                            }
                        }

                        if (selectedProject == null) {
                            System.out.println("Project not found.");
                        } else {
                            System.out.println("Enter Task ID: ");
                            int taskID = scanner.nextInt();
                            scanner.nextLine();

                            Task selectedTask = null;
                            for(Task task : selectedProject.getTasks()) {
                                if(task.getTaskID() == taskID) {
                                    selectedTask = task;
                                    break;
                                }
                            }

                            if (selectedTask == null) {
                                System.out.println("Task could not be found.");
                            } else {
                                if(selectedProject.removeTask(selectedTask)) {
                                    System.out.println("Project Ticket Completed Successfully!");
                                } else {
                                    System.out.println("Task could not be removed.");
                                }
                            }
                        }
                    }
                    break;
                }

                case 5: {
                    if(projects.isEmpty()) {
                        System.out.println("There are no projects to display");
                    } else {
                        System.out.println("Enter Project ID:");
                        int projectID3 = scanner.nextInt();
                        scanner.nextLine();

                        Project selectedProject = null;
                        for(Project project : projects) {
                            if(project.getProjectID() == projectID3) {
                                selectedProject = project;
                                break;
                            }
                        }

                        if (selectedProject == null) {
                            System.out.println("Project not found.");
                        } else {
                            System.out.println("Enter Task ID: ");
                            int taskID = scanner.nextInt();
                            scanner.nextLine();

                            Task selectedTask = null;
                            for(Task task : selectedProject.getTasks()) {
                                if(task.getTaskID() == taskID) {
                                    selectedTask = task;
                                    break;
                                }
                            }

                            if(selectedTask == null) {
                                System.out.println("Task could not be found.");
                            } else {
                                System.out.println("Ticket update options");
                                System.out.println("1. Update Title");
                                System.out.println("2. Update Description");
                                System.out.println("3. Update Priority");
                                System.out.println("4. Update Due Date");

                                int choice4 = scanner.nextInt();
                                scanner.nextLine();

                                switch (choice4) {
                                    case 1:
                                        System.out.println("Enter new title: ");
                                        String newTitle = scanner.nextLine();
                                        selectedTask.setTitle(newTitle);
                                        System.out.println("Title updated successfully!");
                                        break;
                                    case 2:
                                        System.out.println("Enter new description: ");
                                        String newDescription = scanner.nextLine();
                                        selectedTask.setDescription(newDescription);
                                        System.out.println("Description updated successfully!");
                                        break;
                                    case 3:
                                        System.out.println("Enter new priority: ");
                                        String newPriority = scanner.nextLine();
                                        selectedTask.setPriority(newPriority);
                                        System.out.println("Priority updated successfully!");
                                        break;
                                    case 4:
                                        LocalDate newDate = null;

                                        while (newDate == null) {
                                            System.out.println("Enter new due date (YYYY-MM-DD): "); //checks user input for valid date
                                            String newDueDate = scanner.nextLine();

                                            try {
                                                newDate = LocalDate.parse(newDueDate);
                                            } catch (Exception e) {
                                                System.out.println("Invalid Due Date! Use YYYY-MM-DD.");
                                            }
                                        }

                                        selectedTask.setDueDate(newDate);
                                        System.out.println("Due date updated successfully!");
                                        break;
                                    default:
                                        System.out.println("Invalid choice.");
                                        break;
                                }
                            }
                        }
                    }
                    break;
                }
                case 6:
                    // Inform the user that sample data is being loaded
                    System.out.println("Loading sample project data...");

                    // Create sample projects with IDs, names, descriptions, and dates
                    Project project1 = new Project(
                            1,
                            "Website Redesign",
                            "Update company website layout and functionality",
                            LocalDate.of(2024, 1, 1),
                            LocalDate.of(2024, 6, 1),
                            null
                    );
                    Project project2 = new Project(
                            2,
                            "Mobile App Development",
                            "Develop a mobile version of the company platform",
                            LocalDate.of(2024, 2, 1),
                            LocalDate.of(2024, 8, 1),
                            null
                    );
                    // Create tasks that will belong to the projects
                    Task task1 = new Task(
                            101,
                            "Design homepage",
                            "Create layout and visual design for homepage",
                            "High",
                            LocalDate.of(2024, 3, 1),
                            null
                    );
                    Task task2 = new Task(
                            102,
                            "Build login system",
                            "Develop authentication system for users",
                            "Medium",
                            LocalDate.of(2024, 4, 1),
                            null
                    );
                    Task task3 = new Task(
                            201,
                            "Design mobile UI",
                            "Create mobile interface mockups",
                            "High",
                            LocalDate.of(2024, 3, 15),
                            null
                    );
                    // Add tasks to their corresponding projects
                    project1.addTask(task1);
                    project1.addTask(task2);
                    project2.addTask(task3);

                    // Add the projects (with their tasks) to the main project list
                    projects.add(project1);
                    projects.add(project2);

                    // Confirm that sample data was loaded successfully
                    System.out.println("Sample project data loaded successfully!");

                    break;
                case 7:
                    isRunning = false;
                    System.out.println("Bye! :)");
                    break;


                    default:
                        System.out.println("Invalid menu choice. Please try again.");
                        break;
            }
        }
    }

    /**
     * Returns a string representation of all projects.
     *
     * @param projects returns list of projects to display
     * @return returns formatted string list of all projects
     */

    public static String getAllProjects(List<Project> projects) {
        StringBuilder projectsString = new StringBuilder();
        for (Project project : projects) {
            projectsString.append(project.toString()).append("\n");
        }
        return projectsString.toString();
    }

    /**
     * Loads project data from file into memory.
     *
     * @param filePath path to the file containing project data
     * @return success or error print statement
     */

    public static String loadProjects(String filePath){
        if(filePath == null || filePath.trim().isEmpty()){
            return "No project file provided.";
        }
        projects.clear(); //clears input box in case filled
        try{
            Scanner fileScanner = new Scanner(new File(filePath));
            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine().trim();
                if(line.isEmpty()){
                    continue;
                }
                String[] parts =  line.split(",");
                int projectID = Integer.parseInt(parts[0].trim());
                String projectName = parts[1].trim();
                String projectDescription = parts[2].trim();
                LocalDate projectStartDate = LocalDate.parse(parts[3].trim());
                LocalDate projectEndDate = LocalDate.parse(parts[4].trim());

                Project newProject = new Project(projectID,projectName,projectDescription,projectStartDate,projectEndDate,null);
                    projects.add(newProject);
            }
            fileScanner.close();
            return "Tickets loaded successfully!";
        } catch (Exception e) {
            return "Error while loading projects file!";
        }

        }

    /**
     * Searches all projects for a keyword match
     *
     * @param keyword text to search for inside projects.
     * @return matching projects or a message if none found
     */

    public static String searchbyKeyword(String keyword){
        if(keyword == null || keyword.trim().isEmpty()){
            return "Please enter a valid keyword";
        }
        String projectsString = "";
        for(Project project : projects){
            String text = project.toString().toLowerCase();
            if(text.contains(keyword.trim().toLowerCase())){
                projectsString = projectsString + project.toString() + "\n";
            }
        }
        if(projectsString.isEmpty()){
            return "No Tickets to show";
        }
        return projectsString;
        }

    /**
     * Removes projects with an ID match
     *
     * @param projectsString returns list of projects as strings
     * @return Message displaying successfully removed task or error message
     */

    public static String removeProjects(String projectsString){
        if(projectsString.trim().isEmpty()){
            return "Please enter a valid project ID";
        }
        try{
            int projectID = Integer.parseInt(projectsString.trim());

            for(Project project : projects){
                if(projectID == project.getProjectID()){
                    projects.remove(project);
                    return "Project " + project.getProjectID() + " has been removed successfully!";
                }
            }
        } catch(NumberFormatException e){
            return "Please enter a valid project ID";
        }
            return projectsString;
        }

    /**
     * Creates the task, allowing user to add id number, title, and description for each task.
     *
     * @param idText ID number
     * @param nameText Title of task
     * @param descriptionText Description of task
     * @return Message following successful creation of task with following parameters or error message.
     */

    public static String createProjects(String idText, String nameText, String descriptionText){
        if(idText == null || idText.trim().isEmpty() || nameText == null || nameText.trim().isEmpty() || descriptionText == null || descriptionText.trim().isEmpty()){
            return "Please fill all the fields in your ticket!";
        }
        try{
            int projectID = Integer.parseInt(idText);
            for(Project project : projects){
                if(projectID == project.getProjectID()){
                    return "Project already exists with that ID";
                }
            }
            Project newProject = new Project(projectID,nameText.trim(),descriptionText.trim(),LocalDate.now(),LocalDate.now().plusDays(30), null);
            projects.add(newProject);
            return "Project created successfully!";
        } catch(NumberFormatException e){
            return "Please enter a valid project ID";
        }
        }

    /**
     * Updates internal project infomation and then updates task ticket
     *
     * @param idText ID number
     * @param nameText Title of task
     * @param descriptionText Description of task
     * @return updated project information on ticket list.
     */

    public static String updateProjects(String idText, String nameText, String descriptionText){
        if(idText == null){
            return "Please enter a valid project ID";
        }
        try{
            int projectID = Integer.parseInt(idText);
            for(Project project : projects){
                if(projectID == project.getProjectID()){
                    if(nameText == null || nameText.trim().isEmpty() || nameText.trim().isEmpty()){
                        project.setProjectName(nameText.trim());
                    }
                    if(descriptionText == null || descriptionText.trim().isEmpty()){
                        project.setProjectDescription(descriptionText.trim());
                    }
                }
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
            return "Project updated successfully!";
        }
    }



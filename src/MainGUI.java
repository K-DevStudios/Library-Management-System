import javax.swing.*;
import java.awt.*;

/**
 * Main GUI for the Project Management System
 * <p>
 *     This class starts the application window using Java Swing and
 *     connects UI components to database operations like creating,
 *     updating, deleting, displaying, or searching for tasks.
 * </p>
 * The interface includes:
 * <ul>
 *     <li>A file/load for displaying stored projects</li>
 *     <li>An input panel for entering task/project data</li>
 *     <li>A control panel for CRUD operations and search functionality</li>
 * </ul>
 */

/**
 * Launches the KOTA Project Management System Main GUI
 * <p>
 *     This method initializes the database table, builds the Swing UI,
 *     and sets up all event listeners for user interaction.
 * </p>
 */
public class MainGUI {
    public static void main(String[] args) {
        Database.createTable(); //creates table for database

        JFrame frame = new JFrame("KOTA: Project Management System"); //Window for GUY
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(); //creates a panel
        frame.add(mainPanel); //puts panel in window
        mainPanel.setLayout(new BorderLayout()); //shows how to organize components

        JPanel filePanel = new JPanel(); //creates another panel
        JTextArea outputArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);
        mainPanel.add(filePanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        filePanel.setLayout(new FlowLayout()); //FlowLayout sets things from left to right

        JLabel fileLabel = new JLabel("File Path:"); //Text showing on screen
        JTextField filePath = new JTextField(10); //Place where user inputs file destination
        JButton loadButton = new JButton("Load File"); //'JButton' creates a clickable button
        loadButton.addActionListener(e -> {
            String path = filePath.getText();
            StringBuilder sb = new StringBuilder();
            for (String p : Database.getAllProjects()) { //gets projects from database
                sb.append(p).append("\n");
            }
            outputArea.setText(sb.toString());
        });

        filePanel.add(fileLabel); //Adds items to panel
        filePanel.add(filePath); //Adds area for file path destination
        filePanel.add(loadButton); //adds button to load file

        //Panel for areas where user enters information

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3,2));
        JLabel id =  new JLabel("ID:");
        JTextField idField = new JTextField();
        JLabel name =  new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel description =  new JLabel("Description:");
        JTextField descriptionField = new JTextField();
        //Adds labels and text fields to panels
        inputPanel.add(id); //Id panel
        inputPanel.add(idField); //Area for id
        inputPanel.add(name); //name panel
        inputPanel.add(nameField); //area for name
        inputPanel.add(description); //Description panel
        inputPanel.add(descriptionField); //area for description

        mainPanel.add(inputPanel, BorderLayout.WEST); //adds the panel in west area

        //Buttons for CRUD menu
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton displayTask = new JButton("Show Tasks");
        displayTask.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (String p : Database.getAllProjects()) {
                sb.append(p).append("\n");
            }
            outputArea.setText(sb.toString()); //displays projects in main project area that are loaded
        });
        JButton createTask = new JButton("Create Task Ticket");
        createTask.addActionListener(e -> {
            int id2 = Integer.parseInt(idField.getText()); //when button is pressed, these items are added

            Database.insertProject(
                    id2,
                    nameField.getText(),
                    descriptionField.getText(),
                    "2026-01-01",
                    "2026-12-31"
            );

            outputArea.setText("Project added to database"); //output text
        });
        JButton deleteTask = new JButton("Close Task Ticket");
        deleteTask.addActionListener(e -> {
            outputArea.setText(Main.removeProjects(idField.getText()));
        });
        JButton updateTask = new JButton("Update Task Ticket"); //updates task ticket
        updateTask.addActionListener(e -> {
            outputArea.setText(Main.updateProjects(idField.getText(), nameField.getText(), descriptionField.getText()));
        });
        JButton customSearchTask = new JButton("Search For Task"); //searches for task
        customSearchTask.addActionListener(e -> {
            outputArea.setText(Main.searchbyKeyword(nameField.getText()));
        });
        JButton exitApp = new JButton("Exit"); //exits program
        exitApp.addActionListener(e -> {
           System.exit(0);
        });


        buttonPanel.add(displayTask); //displays button for 'display task'
        buttonPanel.add(createTask); //displays button for 'create task'
        buttonPanel.add(deleteTask); // displays button for 'delete task'
        buttonPanel.add(updateTask); // displays  button for 'update task'
        buttonPanel.add(customSearchTask); //displays button for 'custom search task'
        buttonPanel.add(exitApp); //displays button for 'exit'

        mainPanel.add(buttonPanel,BorderLayout.SOUTH); //Puts to bottom

        frame.setResizable(true); //makes window resizable
        frame.setVisible(true); //makes window visible
    }
}

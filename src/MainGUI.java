import javax.swing.*;
import java.awt.*;

public class MainGUI {
    public static void main(String[] args) {
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
            outputArea.setText(Main.loadProjects(path));
        });

        filePanel.add(fileLabel); //Adds items to panel
        filePanel.add(filePath);
        filePanel.add(loadButton);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3,2));
        JLabel id =  new JLabel("ID:");
        JTextField idField = new JTextField();
        JLabel name =  new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel description =  new JLabel("Description:");
        JTextField descriptionField = new JTextField();
        //Adds labels and text fields to panels
        inputPanel.add(id);
        inputPanel.add(idField);
        inputPanel.add(name);
        inputPanel.add(nameField);
        inputPanel.add(description);
        inputPanel.add(descriptionField);

        mainPanel.add(inputPanel, BorderLayout.WEST);

        //Buttons for CRUD menu
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton displayTask = new JButton("Show Tasks");
        displayTask.addActionListener(e -> {
            outputArea.setText(Main.getAllProjects(Main.projects)); //displays projects in main project area
        });
        JButton createTask = new JButton("Create Task Ticket");
        createTask.addActionListener(e -> {
            outputArea.setText(Main.createProjects(idField.getText(), nameField.getText(), descriptionField.getText()));
        });
        JButton deleteTask = new JButton("Close Task Ticket");
        deleteTask.addActionListener(e -> {
            outputArea.setText(Main.removeProjects(idField.getText()));
        });
        JButton updateTask = new JButton("Update Task Ticket");
        updateTask.addActionListener(e -> {
            outputArea.setText(Main.updateProjects(idField.getText(), nameField.getText(), descriptionField.getText()));
        });
        JButton customSearchTask = new JButton("Search for task");
        customSearchTask.addActionListener(e -> {
            outputArea.setText(Main.searchbyKeyword(nameField.getText()));
        });
        JButton exitApp = new JButton("Exit");
        exitApp.addActionListener(e -> {
           System.exit(0);
        });


        buttonPanel.add(displayTask);
        buttonPanel.add(createTask);
        buttonPanel.add(deleteTask);
        buttonPanel.add(updateTask);
        buttonPanel.add(customSearchTask);
        buttonPanel.add(exitApp);

        mainPanel.add(buttonPanel,BorderLayout.SOUTH); //Puts to bottom

        frame.setResizable(true);
        frame.setVisible(true); //makes window visible
    }
}

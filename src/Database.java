import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    // STEP 1: Connect to SQLite
    public static Connection connect() { //connects to database and returns connection object
        try {
            String url = "jdbc:sqlite:projects.db"; //creates and uses sqlite file called projects.db
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage()); //catches error exception
            return null;
        }
    }

    // STEP 2: Create table
    public static void createTable() { //Creates table if project doesnt exist
        String sql = "CREATE TABLE IF NOT EXISTS projects (" +
                "id INTEGER, " +
                "name TEXT, " +
                "description TEXT, " +
                "startDate TEXT, " +
                "endDate TEXT)";

        try (Connection conn = connect(); //runs connection to SQL
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql); //runs the command

        } catch (SQLException e) { //handles errors
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    // STEP 3: Insert project
    public static void insertProject(int id, String name, String description, //inserts new row in database
                                     String startDate, String endDate) {

        String sql = "INSERT INTO projects VALUES (?, ?, ?, ?, ?)"; //SQL insert with placeholders

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            //finds values and adds them
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, description);
            pstmt.setString(4, startDate);
            pstmt.setString(5, endDate);

            pstmt.executeUpdate(); //executes insert, and adds table to row

        } catch (SQLException e) {
            System.out.println("Error inserting project: " + e.getMessage());
        }
    }

    // STEP 4: Read all projects
    public static List<String> getAllProjects() { //Lists all projects
        List<String> projects = new ArrayList<>();

        String sql = "SELECT * FROM projects";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String project =
                        rs.getInt("id") + ", " +
                                rs.getString("name") + ", " +
                                rs.getString("description") + ", " +
                                rs.getString("startDate") + ", " +
                                rs.getString("endDate");

                projects.add(project);
            }

        } catch (SQLException e) {
            System.out.println("Error reading projects: " + e.getMessage());
        }

        return projects;
    }
}
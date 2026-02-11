import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in); //Scanner for reading user entry

        System.out.println("Please specify a file path: "); //Prompt for file path
        String filePath = input.nextLine(); //Waits for user read input
        Path path = Paths.get(filePath); //Creates file path getter

        ArrayList<Patron> patron = new ArrayList<>(); //Creates new patron in ArrayList

        try(BufferedReader br = Files.newBufferedReader(path)) { //Read file from the line
            String line;
            while ((line = br.readLine()) != null) { //While entry line is not empty
                String[] tokens = line.split(","); //Splits data field from string array
                if(tokens.length == 4) {
                    String id = tokens[0].trim(); //trim helps correct inconsistent spacing after commas
                    String name = tokens[1].trim();
                    String address = tokens[2].trim();
                    double fineAmount = Double.parseDouble(tokens[3].trim());

                    Patron patron1 = new Patron(tokens[0], tokens[1], tokens[2], Double.parseDouble(tokens[3])); //Creates Patron object with ID and name tokens
                    patron.add(patron1); //Stores it in list
                } else{
                    System.out.println("Skipping invalid line " + line);
                }
            }

        } catch (IOException e) { //Standard file exception
            System.err.println("Error opening file: " + e.getMessage());
            throw new RuntimeException(e);
        }


        System.out.println("FILE PATH: " + path); //Shows file path

        System.out.println(" --- LIBRARY PATRONS --- "); //List of library patrons

        for (Patron p : patron) { //For every patron in the list, print patron
            System.out.println(p);
        }

        boolean isRunning = true; //condition to run loop while program is running

        while(isRunning) { //while program is running, loop keeps displayed option.
            System.out.println(" *** LIBRARY MANAGEMENT SYSTEM *** "); //displayed menu options
            System.out.println("Choose an option from the menu: ");
            System.out.println("1. Add Patron");
            System.out.println("2. Remove Patron");
            System.out.println("3. View Patron by ID");
            System.out.println("4. List all Patrons");
            System.out.println("5. Exit Program");
            String option = input.nextLine();

            //Switch statement for selecting options
            boolean foundPatron = false;
            switch (option) {
                case "1": //Case for adding users
                    String id;
                    while (true) {
                        System.out.print("Enter 7-digit Patron ID: "); //While loop for validating id digit entry
                        id = input.nextLine();
                        if (id.matches("\\d{7}")) {
                            break;
                        } else {
                            System.out.println("Invalid ID!!! ID MUST BE 7 DIGITS LONG!");
                        }
                    }
                    System.out.print("Enter Patron name: ");
                    String name = input.nextLine();
                    System.out.print("Enter Patron address: ");
                    String address = input.nextLine();
                    double fineAmount;
                    while (true) {
                        System.out.print("Enter a fine amount ($0 - $200): ");
                        try {
                            fineAmount = Double.parseDouble(input.nextLine());

                            if (fineAmount >= 0 && fineAmount <= 200) {
                                break;
                            } else {
                                System.out.println("Invalid fine amount ($0 - $200)!");
                            }
                        } catch (NumberFormatException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    Patron patron1 = new Patron(id, name, address, fineAmount); //patron object created and added
                    patron.add(patron1);
                    System.out.println("Patron added successfully!");
                    break;
                case "2": //case for removing patrons
                    System.out.print("Enter Patron ID: ");
                    String id2 = input.nextLine();
                    boolean found = false;

                    for (int i = 0; i < patron.size(); i++) {
                        if (patron.get(i).getId().equals(id2)) { //based on size of list, if patron id matches id in list, patron is removed
                            patron.remove(i);
                            found = true;
                            System.out.println("Patron removed successfully!");
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Patron not found!");
                    }
                    break;

                case "3":
                    System.out.print("Enter Patron ID: ");
                    String searchID = input.nextLine().trim();

                    foundPatron = false;

                    for (Patron p : patron) {
                        if (p.getId().trim().equals(searchID)) {
                            System.out.println("PATRON FOUND:");
                            System.out.println(p);
                            foundPatron = true;
                            break;
                        }
                    }

            if (!foundPatron) {
                System.out.println("Patron not found!");
            }
            break;

            case "4": //case for viewing full list of patrons
                System.out.println(" --- LIBRARY PATRONS --- "); //List of library patrons
                for (Patron p : patron) { //For every patron in the list, print patron
                    System.out.println(p);
                }
                break;


            case "5": //case for saving and closing program.
                System.out.println(" --- CLOSING PROGRAM --- ");
                try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
                    for (Patron p : patron) {
                        bufferedWriter.write(p.getId() + "," + p.getName() + "," + p.getAddress() + "," + p.getFineAmount());
                        bufferedWriter.newLine();
                    }
                    System.out.println("Changes Saved!");
                } catch (IOException e) {
                    System.err.println("Error saving file: " + e.getMessage());
                }
                isRunning = false;
                break;
            default:
                System.out.println("INVALID OPTION");
        }
}
    }
}

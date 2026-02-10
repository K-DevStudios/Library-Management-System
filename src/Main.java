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
                String[] tokens = line.split(" "); //Splits data field from string array

                Patron patron1 = new Patron(tokens[0], tokens[1]); //Creates Patron object with ID and name tokens
                patron.add(patron1); //Stores it in list
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
    System.out.println("3. List all Patrons");
    System.out.println("4. Exit Program");
    String option = input.nextLine();

    //Switch statement for selecting options
    switch (option) {
        case "1": //Case for adding users
            System.out.print("Enter Patron ID: ");
            String id = input.nextLine();
            System.out.print("Enter Patron name: ");
            String name = input.nextLine();
            Patron patron1 = new Patron(id, name); //patron object created and added
            patron.add(patron1);
            System.out.println("Patron added successfully!");
            break;
        case "2": //case for removing patrons
            System.out.print("Enter Patron ID: ");
            String id2 = input.nextLine();
            boolean found = false;

            for(int i = 0; i < patron.size(); i++) {
                if(patron.get(i).getId().equals(id2)) { //based on size of list, if patron id matches id in list, patron is removed
                    patron.remove(i);
                    found = true;
                    System.out.println("Patron removed successfully!");
                    break;
                }
            } if(!found) {
                System.out.println("Patron not found!");
        }
            break;
        case "3": //case for viewing full list of patrons
            System.out.println(" --- LIBRARY PATRONS --- "); //List of library patrons
            for (Patron p : patron) { //For every patron in the list, print patron
                System.out.println(p);
            }
            break;
        case "4": //case for saving and closing program.
            System.out.println(" --- CLOSING PROGRAM --- ");
            try(BufferedWriter bufferedWriter = Files.newBufferedWriter(path)){
                for(Patron p : patron) {
                    bufferedWriter.write(p.getId() + " " + p.getName());
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

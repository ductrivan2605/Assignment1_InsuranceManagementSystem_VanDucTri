import ClaimProcessManager.ClaimProcessManagerImpl;
import ClaimProcessManager.ClaimProcessManager;
import entities.Claim;
import entities.Customer;

import java.io.*;
import java.util.*;

public class InsuranceClaimsSystem {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // Initialize system with sample data from files
        List<Customer> customers = readCustomersFromFile("customers.txt");
        List<Claim> claims = readClaimsFromFile("claims.txt");

        // Initialize ClaimProcessManager
        ClaimProcessManager claimProcessManager = new ClaimProcessManagerImpl();
        for (Claim claim : claims) {
            claimProcessManager.add(claim);
        }
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Add a new claim");
            System.out.println("2. View all claims");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewClaim(claimProcessManager);
                    break;
                case 2:
                    viewAllClaims(claimProcessManager);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        // Update files before exiting
        writeClaimsToFile("claims.txt", claims);
    }
    private static Date parseDate(String dateStr) {
        try {
            String[] parts = dateStr.split("-");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            return new Date(day, month, year);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Invalid date format. Please enter date in DD-MM-YYYY format.");
            return null;
        }
    }

    private static void addNewClaim(ClaimProcessManager claimProcessManager) {
        System.out.println("Adding a new claim...");

        // Collect claim information
        System.out.print("Enter claim ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter claim date (DD-MM-YYYY): ");
        String claimDateStr = scanner.nextLine();
        Date claimDate = parseDate(claimDateStr);

        System.out.print("Enter insured person: ");
        String insuredPerson = scanner.nextLine();

        System.out.print("Enter card number: ");
        String cardNumber = scanner.nextLine();

        System.out.print("Enter exam date (DD-MM-YYYY): ");
        String examDateStr = scanner.nextLine();
        Date examDate = parseDate(examDateStr);

        List<String> documents = new ArrayList<>();
        boolean addMoreDocuments = true;
        while (addMoreDocuments) {
            System.out.print("Enter document name (Enter 'done' to finish adding documents): ");
            String documentName = scanner.nextLine();
            if (documentName.equalsIgnoreCase("done")) {
                addMoreDocuments = false;
            } else {
                documents.add(documentName);
            }
        }

        System.out.print("Enter claim amount: ");
        double claimAmount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter status (New, Processing, Done): ");
        String status = scanner.nextLine();

        System.out.println("Enter receiver banking information:");
        System.out.print("Bank: ");
        String bank = scanner.nextLine();

        System.out.print("Receiver Name: ");
        String receiverName = scanner.nextLine();

        System.out.print("Receiver Number: ");
        String receiverNumber = scanner.nextLine();

        // Create a new claim object
        Claim newClaim = new Claim(id, claimDate, insuredPerson, cardNumber, examDate, documents, claimAmount, status, bank, receiverName, receiverNumber);

        // Add the new claim
        claimProcessManager.add(newClaim);

        System.out.println("New claim added successfully.");
    }
    private static void viewAllClaims(ClaimProcessManager claimProcessManager) {
        System.out.println("All claims:");
        List<Claim> allClaims = claimProcessManager.getAll();
        for (Claim claim : allClaims) {
            System.out.println(claim.id + " - " + claim.status);
        }
    }
    public static List<Customer> readCustomersFromFile(String filename) {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Implement logic to read customers from file
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return customers;
    }

    // Sample method to read claims from file
    public static List<Claim> readClaimsFromFile(String filename) {
        List<Claim> claims = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Implement logic to read claims from file
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return claims;
    }

    public static void writeClaimsToFile(String filename, List<Claim> claims) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Implement logic to write claims to file
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}

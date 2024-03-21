/**
 * @author <Van Duc Tri - s3978223>
 */
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
        List<Customer> customers = readCustomersFromFile("data/customers.txt");
        List<Claim> claims = readClaimsFromFile("data/claims.txt");

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
        writeClaimsToFile("data/claims.txt", claimProcessManager.getAll());
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
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String id = parts[0];
                String fullName = parts[1] + " " + parts[2]; // Full name may consists of first name and last name
                // Create Customer object and add to list
                customers.add(new Customer(id, fullName, null, null));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return customers;
    }
    public static List<Claim> readClaimsFromFile(String filename) {
        List<Claim> claims = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String id = parts[0];
                Date claimDate = parseDate(parts[1]);
                String insuredPerson = parts[2];
                String cardNumber = parts[3];
                Date examDate = parseDate(parts[4]);
                List<String> documents = new ArrayList<>();
                for (int i = 5; i < parts.length - 4; i++) {
                    documents.add(parts[i]);
                }
                double claimAmount = Double.parseDouble(parts[parts.length - 4]);
                String status = parts[parts.length - 3];
                String bank = parts[parts.length - 2];
                String receiverName = parts[parts.length - 1];
                String receiverNumber = parts[parts.length];
                claims.add(new Claim(id, claimDate, insuredPerson, cardNumber, examDate, documents, claimAmount, status, bank, receiverName, receiverNumber));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return claims;
    }

    public static void writeClaimsToFile(String filename, List<Claim> claims) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Claim claim : claims) {
                writer.write(claim.getId() + " " + claim.getClaimDate() + " " + claim.getInsuredPerson() + " " + claim.getCardNumber() + " " + claim.getExamDate());
                for (String document : claim.getDocuments()) {
                    writer.write(" " + document);
                }
                writer.write(" " + claim.getClaimAmount() + " " + claim.getStatus() + " " + claim.getReceiverBank() + " " + claim.getReceiverName() + " " + claim.getReceiverNumber());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}

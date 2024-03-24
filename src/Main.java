/**
 * @author <Van Duc Tri - s3978223>
 */
import ClaimProcessManager.ClaimProcessManagerImpl;
import ClaimProcessManager.ClaimProcessManager;
import entities.Claim;
import entities.Customer;

import java.io.*;
import java.util.*;

public class Main {
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
            System.out.println("3. Update a claim");
            System.out.println("4. Delete a claim");
            System.out.println("5. Find a claim");
            System.out.println("6. Add a Customer");
            System.out.println("7. Update a Customer");
            System.out.println("8. Find a Customer by ID");
            System.out.println("9. Find a Customer by Name");
            System.out.println("10. Exit");
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
                    updateClaim(claimProcessManager);
                    break;
                case 4:
                    deleteClaim(claimProcessManager);
                    break;
                case 5:
                    getSingleClaim(claimProcessManager);
                    break;
                case 6:
                    exit = true;
                    break;
                case 7:
                    exit = true;
                    break;
                case 8:
                    exit = true;
                    break;
                case 9:
                    exit = true;
                    break;
                case 10:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        // Update files before exiting
        writeClaimsToFile("data/claims.txt", claimProcessManager.getAll());
    }

    public static Date parseDate(String dateStr) {
        try {
            String[] parts = dateStr.split("-");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid date format. Please enter date in DD-MM-YYYY format.");
            }
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            return new Date(day, month, year);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid date format. Please enter date in DD-MM-YYYY format.");
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
    private static void updateClaim(ClaimProcessManager claimProcessManager) {
        System.out.print("Enter the ID of the claim to update: ");
        String claimId = scanner.nextLine();
        Claim existingClaim = claimProcessManager.getOne(claimId);
        if (existingClaim != null) {
            System.out.println("Enter new details for the claim (leave blank to keep existing):");

            // Update claim date
            System.out.print("New claim date (DD-MM-YYYY): ");
            String newClaimDateStr = scanner.nextLine();
            Date newClaimDate = newClaimDateStr.isEmpty() ? existingClaim.getClaimDate() : parseDate(newClaimDateStr);

            // Update insured person
            System.out.print("New insured person: ");
            String newInsuredPerson = scanner.nextLine();
            if (newInsuredPerson.isEmpty()) {
                newInsuredPerson = existingClaim.getInsuredPerson();
            }

            // Update card number
            System.out.print("New card number: ");
            String newCardNumber = scanner.nextLine();
            if (newCardNumber.isEmpty()) {
                newCardNumber = existingClaim.getCardNumber();
            }

            // Update exam date
            System.out.print("New exam date (DD-MM-YYYY): ");
            String newExamDateStr = scanner.nextLine();
            Date newExamDate = newExamDateStr.isEmpty() ? existingClaim.getExamDate() : parseDate(newExamDateStr);

            // Update documents (if any)
            List<String> newDocuments = new ArrayList<>();
            boolean addMoreDocuments = true;
            while (addMoreDocuments) {
                System.out.print("Enter new document name (Enter 'done' to finish adding documents): ");
                String newDocumentName = scanner.nextLine();
                if (newDocumentName.equalsIgnoreCase("done")) {
                    addMoreDocuments = false;
                } else {
                    newDocuments.add(newDocumentName);
                }
            }

            // Update claim amount
            System.out.print("New claim amount: ");
            String newClaimAmountStr = scanner.nextLine();
            double newClaimAmount = newClaimAmountStr.isEmpty() ? existingClaim.getClaimAmount() : Double.parseDouble(newClaimAmountStr);

            // Update status
            System.out.print("New status (New, Processing, Done): ");
            String newStatus = scanner.nextLine();
            if (newStatus.isEmpty()) {
                newStatus = existingClaim.getStatus();
            }

            // Update receiver banking information
            System.out.println("Enter new receiver banking information:");
            System.out.print("New bank: ");
            String newBank = scanner.nextLine();
            if (newBank.isEmpty()) {
                newBank = existingClaim.getReceiverBank();
            }

            System.out.print("New receiver Name: ");
            String newReceiverName = scanner.nextLine();
            if (newReceiverName.isEmpty()) {
                newReceiverName = existingClaim.getReceiverName();
            }

            System.out.print("New receiver Number: ");
            String newReceiverNumber = scanner.nextLine();
            if (newReceiverNumber.isEmpty()) {
                newReceiverNumber = existingClaim.getReceiverNumber();
            }

            // Create the updated claim object
            Claim updatedClaim = new Claim(claimId, newClaimDate, newInsuredPerson, newCardNumber, newExamDate, newDocuments, newClaimAmount, newStatus, newBank, newReceiverName, newReceiverNumber);

            // Update the claim
            claimProcessManager.update(updatedClaim);
            System.out.println("Claim with ID " + claimId + " updated successfully.");
        } else {
            System.out.println("Claim with ID " + claimId + " not found.");
        }
    }
    private static void deleteClaim(ClaimProcessManager claimProcessManager) {
        System.out.print("Enter the ID of the claim to delete: ");
        String claimId = scanner.nextLine();
        Claim existingClaim = claimProcessManager.getOne(claimId);
        if (existingClaim != null) {
            claimProcessManager.delete(claimId);
            System.out.println("Claim with ID " + claimId + " deleted successfully.");
        } else {
            System.out.println("Claim with ID " + claimId + " not found.");
        }
    }

    private static void getSingleClaim(ClaimProcessManager claimProcessManager) {
        System.out.print("Enter the ID of the claim to retrieve: ");
        String claimId = scanner.nextLine();
        Claim claim = claimProcessManager.getOne(claimId);
        if (claim != null) {
            // Display the details of the retrieved claim
            System.out.println("Claim ID: " + claim.getId());
            System.out.println("Claim Status: " + claim.getStatus());
            // Display other claim details as needed
        } else {
            System.out.println("Claim with ID " + claimId + " not found.");
        }
    }

    public static List<Customer> readCustomersFromFile(String filename) {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String fullName = parts[1];
                boolean isPolicyHolder = Boolean.parseBoolean(parts[2]);
                Customer policyHolder = null;
                List<Customer> dependents = new ArrayList<>();

                if (!isPolicyHolder) {
                    // If the customer is a dependent, find and associate with the corresponding policy holder
                    String policyHolderId = parts[3];
                    // Find the policy holder in the existing customer list
                    for (Customer customer : customers) {
                        if (customer.getId().equals(policyHolderId)) {
                            policyHolder = customer;
                            break;
                        }
                    }
                    // If policy holder is found, add this customer as a dependent
                    if (policyHolder != null) {
                        dependents.add(new Customer(id, fullName, false, policyHolder, null, new ArrayList<>(), null));
                    }
                }
                // Add the policy holder or dependent to the list of customers
                customers.add(new Customer(id, fullName, isPolicyHolder, policyHolder, null, dependents, new ArrayList<>()));
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
                String[] parts = line.split(",");
                String id = parts[0];
                Date claimDate = parseDate(parts[1]);
                String insuredPerson = parts[2];
                String cardNumber = parts[3];
                Date examDate = parseDate(parts[4]);
                List<String> documents = new ArrayList<>();
                documents.add(parts[5]);
                double claimAmount = Double.parseDouble(parts[6]);
                String status = parts[7];
                String bank = parts[8];
                String receiverName = parts[9];
                String receiverNumber = parts[10];
                claims.add(new Claim(id, claimDate, insuredPerson, cardNumber, examDate, documents, claimAmount, status, bank, receiverName, receiverNumber));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid claim amount format.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Invalid claim format: " + e.getMessage());
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

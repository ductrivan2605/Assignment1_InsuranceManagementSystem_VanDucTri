/**
 * @author <Van Duc Tri - s3978223>
 */
package entities;
import java.util.Date;
import java.util.List;

public class Claim {
//    A Claim contains id, claim date, insured person, card number, exam date, list of documents, claim amount, status and receiver banking info
    public String id;
    Date claimDate;
    String insuredPerson;
    String cardNumber;
    Date examDate;
    List<String> documents;
    double claimAmount;
    public String status;
    String receiverBank;
    String receiverName;
    String receiverNumber;

    public Claim(String id, Date claimDate, String insuredPerson, String cardNumber, Date examDate, List<String> documents, double claimAmount, String status, String receiverBank, String receiverName, String receiverNumber) {
        this.id = id;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.documents = documents;
        this.claimAmount = claimAmount;
        this.status = status;
        this.receiverBank = receiverBank;
        this.receiverName = receiverName;
        this.receiverNumber = receiverNumber;
    }
//    Getter and Setter for Claims
    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id.matches("F-\\d{10}")) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Invalid claim ID format. Format should be 'F-numbers' with 10 digits.");
        }
    }
    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public String getInsuredPerson() {
        return insuredPerson;
    }

    public void setInsuredPerson(String insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiverBank() {
        return receiverBank;
    }

    public void setReceiverBank(String receiverBank) {
        this.receiverBank = receiverBank;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverNumber() {
        return receiverNumber;
    }

    public void setReceiverNumber(String receiverNumber) {
        this.receiverNumber = receiverNumber;
    }
    @Override
    public String toString() {
        return "ID: " + id + "\n" + "Claim Date: " + claimDate + "\n" + "Insured Person: " + insuredPerson + "\n" + "Card Number: " + cardNumber + "\n" + "Exam Date: " + examDate + "\n" + "Documents: " + documents + "\n" + "Claim Amount: " + claimAmount + "\n" + "Status: " + status + "\n" + "Receiver Bank: " + receiverBank + "\n" + "Receiver Name: " + receiverName + "\n" + "Receiver Number: " + receiverNumber;
    }
}

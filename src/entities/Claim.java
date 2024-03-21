package entities;
import java.util.Date;
import java.util.List;

public class Claim {
    String id;
    Date claimDate;
    String insuredPerson;
    String cardNumber;
    Date examDate;
    List<String> documents;
    double claimAmount;
    String status;
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
}

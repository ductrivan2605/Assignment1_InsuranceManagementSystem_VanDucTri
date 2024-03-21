package entities;
import java.util.List;

public class Customer {
    String id;
    String fullName;
    InsuranceCard insuranceCard;
    List<Claim> claims;

    public Customer(String id, String fullName, InsuranceCard insuranceCard, List<Claim> claims) {
        this.id = id;
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
        this.claims = claims;
    }
}

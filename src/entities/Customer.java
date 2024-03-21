package entities;
import java.util.List;

public class Customer {
// Every Customer has an ID, name, unique insurance card and list of claims
    String id;
    String fullName;
    InsuranceCard insuranceCard;
    List<Claim> claims;
//    Constructor
    public Customer(String id, String fullName, InsuranceCard insuranceCard, List<Claim> claims) {
        this.id = id;
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
        this.claims = claims;
    }
//    Getter for Customer class
    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public InsuranceCard getInsuranceCard() {
        return insuranceCard;
    }

    public List<Claim> getClaims() {
        return claims;
    }
//    Setters for Customer class
}

package entities;
import java.util.List;

public class Customer {
// Every Customer has an ID, name, unique insurance card and list of claims
    private String id;
    private String fullName;
    private InsuranceCard insuranceCard;
    private List<Claim> claims;
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

    public void setId(String id) {
        if (id.matches("C-\\d{7}")) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Invalid customer ID format. Format should be 'c-numbers' with 7 digits.");
        }
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setInsuranceCard(InsuranceCard insuranceCard) {
        this.insuranceCard = insuranceCard;
    }

    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }
    @Override
    public String toString() {
        return "ID: " + id + "\n" + "Full Name: " + fullName + "\n" + "Insurance Card: " + insuranceCard + "\n" + "Claims: " + claims.size();
    }
}

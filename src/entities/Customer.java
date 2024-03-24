/**
 * @author <Van Duc Tri - s3978223>
 */
package entities;
import java.util.List;

public class Customer {
// Every Customer has an ID, name, unique insurance card and list of claims
    private String id;
    private String fullName;
    private boolean isPolicyHolder;
    private Customer policyHolder;
    private String cardNumber;
    private List<Customer> dependents;
    private List<Claim> claims;
//    Constructor
    public Customer(String id, String fullName, boolean isPolicyHolder, Customer policyHolder, String cardNumber, List<Customer> dependents, List<Claim> claims) {
        this.id = id;
        this.fullName = fullName;
        this.cardNumber = InsuranceCard.getCardNumber();
        this.isPolicyHolder = isPolicyHolder;
        this.policyHolder = policyHolder;
        this.dependents = dependents;
        this.claims = claims;
    }
//    Getter for Customer class
    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public List<Customer> getDependents() {
        return dependents;
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

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\n");
        sb.append("Full Name: ").append(fullName).append("\n");
        sb.append("Insurance Card Number: ").append(cardNumber).append("\n");
        if (isPolicyHolder) {
            sb.append("Policy Holder: Yes\n");
        } else {
            sb.append("Policy Holder: No\n");
            sb.append("Policy Holder ID: ").append(policyHolder.getId()).append("\n");
        }
        sb.append("Dependents: ").append(dependents.size()).append("\n");
        sb.append("Claims: ").append(claims.size());
        return sb.toString();
    }
}

/**
 * @author <Van Duc Tri - s3978223>
 */
package entities;
import java.util.Date;
public class InsuranceCard{
    String cardNumber;
    String cardHolder;
    String policyOwner;
    Date expirationDate;
    boolean isAssigned; //This is to track if the card has been owned by a cardHolder

    public InsuranceCard(String cardNumber, String cardHolder, String policyOwner, Date expirationDate, boolean isAssigned){
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.policyOwner = policyOwner;
        this.expirationDate = expirationDate;
        this.isAssigned = true; // Insurance Card is assigned by default
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getPolicyOwner() {
        return policyOwner;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setPolicyOwner(String policyOwner) {
        this.policyOwner = policyOwner;
    }

    public void setCardHolder(String cardHolder) {
        if (!isAssigned) {
            this.cardHolder = cardHolder;
            isAssigned = true; // Mark card as assigned when holder is set
        } else {
            throw new IllegalStateException("This card is already assigned to a holder.");
        }
    }
    public void setCardNumber(String cardNumber) {
        if (cardNumber.matches("\\d{10}")) {
            this.cardNumber = cardNumber;
        } else {
            throw new IllegalArgumentException("Invalid insurance card number format. It should contain exactly 10 digits.");
        }
    }

    @Override
    public String toString(){
        return "Card number: " + cardNumber + "\n" + "Holder: " + cardHolder + "\n" + "Policy Owner: " + policyOwner + "\n" + "Expiration Date: " + expirationDate;
    }
}

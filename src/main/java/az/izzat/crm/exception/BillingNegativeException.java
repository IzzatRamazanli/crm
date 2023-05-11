package az.izzat.crm.exception;

public class BillingNegativeException extends RuntimeException {
    public BillingNegativeException(String message) {
        super(message);
    }
}

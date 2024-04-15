package Exceptions;

/**
 * This class represents a custom exception for transaction-related errors. It
 * extends the RuntimeException class.
 *
 * @author Gonzalo Jiménez, Luis Pastor
 */
public class TransactionException extends RuntimeException {

    private final String originPublicKey;
    private final String destinationPublicKey;
    private final Integer balance;

    /**
     * Constructs a new TransactionException with the specified origin public
     * key, destination public key, and balance.
     *
     * @param originPublicKey the public key of the transaction origin
     * @param destinationPublicKey the public key of the transaction destination
     * @param balance the balance of the transaction
     */
    public TransactionException(String originPublicKey, String destinationPublicKey, Integer balance) {
        this.originPublicKey = originPublicKey;
        this.destinationPublicKey = destinationPublicKey;
        this.balance = balance;
    }

    /**
     * Returns a string representation of the TransactionException object.
     *
     * @return a string representation of the TransactionException object
     */
    @Override
    public String toString() {
        return "Negative transfer attempt: source: " + this.originPublicKey
                + ", receiver: " + this.destinationPublicKey
                + ", amount: " + this.balance;
    }
}

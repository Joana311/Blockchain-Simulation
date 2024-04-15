package Exceptions;

public class TransactionException extends RuntimeException {

    private final String originPublicKey;
    private final String destinationPublicKey;
    private final Integer balance;

    public TransactionException(String originPublicKey, String destinationPublicKey, Integer balance) {
        this.originPublicKey = originPublicKey;
        this.destinationPublicKey = destinationPublicKey;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Negative transfer attempt: source: " + this.originPublicKey
                + ", receiver: " + this.destinationPublicKey
                + ", amount: " + this.balance;
    }
}

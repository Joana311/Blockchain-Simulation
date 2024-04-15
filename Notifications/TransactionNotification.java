package Notifications;

import Transaction.Transaction;

/**
 * This class represents a transaction notification message. It implements the
 * IMessage interface.
 *
 * @author Gonzalo Jiménez, Luis Pastor
 */
public class TransactionNotification implements IMessage {

    private final Transaction transaction;

    /**
     * Constructs a TransactionNotification object with the given transaction.
     *
     * @param transaction the transaction associated with the notification
     */
    public TransactionNotification(Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * Returns the transaction associated with the notification.
     *
     * @return the transaction
     */
    public Transaction getTransaction() {
        return this.transaction;
    }

    /**
     * Returns the message content of the notification.
     *
     * @return the message content
     */
    @Override
    public String getMessage() {
        return "Transaction " + this.transaction.getId()
                + "| from: " + this.transaction.getOriginPublicKey()
                + ", to: " + this.transaction.getDestinationPublicKey()
                + ", quantity: " + this.transaction.getBalance();
    }

}

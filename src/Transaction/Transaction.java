package Transaction;

import Wallet.*;

/**
 * This class represents a transaction between two wallets.
 *
 * @author Gonzalo Jiménez, Luis Pastor
 */
public class Transaction {

    private static Integer count = 0;

    private Integer id;
    private String originPublicKey;
    private String destinationPublicKey;
    private Integer balance;

    private void assignId() {
        this.id = count;
        count++;
    }

    /**
     * Constructs a new Transaction object with the specified origin wallet,
     * destination wallet, and balance.
     *
     * @param origin The origin wallet of the transaction.
     * @param destination The destination wallet of the transaction.
     * @param balance The balance of the transaction.
     */
    public Transaction(Wallet origin, Wallet destination, Integer balance) {
        this.originPublicKey = origin.getPublicKey();
        this.destinationPublicKey = destination.getPublicKey();
        this.balance = balance;

        this.assignId();
    }

    /**
     * Constructs a new Transaction object with the specified origin public key,
     * destination public key, and balance.
     *
     * @param originPublicKey The origin public key of the transaction.
     * @param destinationPublicKey The destination public key of the
     * transaction.
     * @param balance The balance of the transaction.
     */
    public Transaction(String originPublicKey, String destinationPublicKey, Integer balance) {
        this.originPublicKey = originPublicKey;
        this.destinationPublicKey = destinationPublicKey;
        this.balance = balance;

        this.assignId();
    }

    /**
     * Returns the ID of the transaction.
     *
     * @return The ID of the transaction.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Returns the origin public key of the transaction.
     *
     * @return The origin public key of the transaction.
     */
    public String getOriginPublicKey() {
        return this.originPublicKey;
    }

    /**
     * Sets the origin public key of the transaction.
     *
     * @param originPublicKey The origin public key to set.
     */
    public void setOriginPublicKey(String originPublicKey) {
        this.originPublicKey = originPublicKey;
    }

    /**
     * Returns the destination public key of the transaction.
     *
     * @return The destination public key of the transaction.
     */
    public String getDestinationPublicKey() {
        return this.destinationPublicKey;
    }

    /**
     * Sets the destination public key of the transaction.
     *
     * @param destinationPublicKey The destination public key to set.
     */
    public void setDestinationPublicKey(String destinationPublicKey) {
        this.destinationPublicKey = destinationPublicKey;
    }

    /**
     * Returns the balance of the transaction.
     *
     * @return The balance of the transaction.
     */
    public Integer getBalance() {
        return this.balance;
    }

    /**
     * Sets the balance of the transaction.
     *
     * @param balance The balance to set.
     */
    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    /**
     * Returns a string representation of the transaction.
     *
     * @return A string representation of the transaction.
     */
    @Override
    public String toString() {
        return "Transaction " + this.id
                + "| from: " + this.originPublicKey
                + ", to:" + this.destinationPublicKey
                + ", quantity: " + this.balance;
    }
}

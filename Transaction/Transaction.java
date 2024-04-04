package Transaction;

import Wallet.*;

public class Transaction {

    private static Integer count = 0;

    private Integer id;
    private Wallet origin;
    private Wallet destination;
    private Integer balance;

    public Transaction(Wallet origin, Wallet destination, Integer balance) {
        this.origin = origin;
        this.destination = destination;
        this.balance = balance;

        count++;
        this.id = count;
    }

    /*_______________________________________________________________*/
    public Integer getId() {
        return this.id;
    }

    public Wallet getOrigin() {
        return this.origin;
    }

    public void setOrigin(Wallet origin) {
        this.origin = origin;
    }

    public Wallet getDestination() {
        return this.destination;
    }

    public void setDestination(Wallet destination) {
        this.destination = destination;
    }

    public Integer getBalance() {
        return this.balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    /*_______________________________________________________________*/
    @Override
    public String toString() {
        return "Transaction " + this.id
                + "| from: " + this.origin.getPublicKey()
                + ", to:" + this.destination.getPublicKey()
                + "quantity: " + this.balance;
    }
}

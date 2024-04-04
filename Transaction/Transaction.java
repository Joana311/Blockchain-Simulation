package Transaction;

import Wallet.*;

public class Transaction {

    private static Integer count = 0;

    private Integer id;
    private String originPublicKey;
    private String destinationPublicKey;
    private Integer balance;

    private void assignId() {
        count++;
        this.id = count;
    }
    
    public Transaction(Wallet origin, Wallet destination, Integer balance) {
        this.originPublicKey = origin.getPublicKey();
        this.destinationPublicKey = destination.getPublicKey();
        this.balance = balance;
        
        this.assignId();
    }
    
    public Transaction(String originPublicKey, String destinationPublicKey, Integer balance) {
        this.originPublicKey = originPublicKey;
        this.destinationPublicKey = destinationPublicKey;
        this.balance = balance;

        this.assignId();
    }

    /*_______________________________________________________________*/
    public Integer getId() {
        return this.id;
    }

    public String getOriginPublicKey() {
        return this.originPublicKey;
    }

    public void setOriginPublicKey(String originPublicKey) {
        this.originPublicKey = originPublicKey;
    }

    public String getDestinationPublicKey() {
        return this.destinationPublicKey;
    }

    public void setDestinationPublicKey(String destinationPublicKey) {
        this.destinationPublicKey = destinationPublicKey;
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
                + "| from: " + this.originPublicKey
                + ", to:" + this.destinationPublicKey
                + "quantity: " + this.balance;
    }
}

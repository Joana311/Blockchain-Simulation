package Exceptions;

import Wallet.*;


public class TransactionException extends Exception{
    private Wallet origin;
    private Wallet destination;
    private Integer balance;
    
    public TransactionException(Wallet origin, Wallet destination, Integer balance) {
        this.origin = origin;
        this.destination = destination;
        this.balance = balance;
    }
    
    @Override
    public String toString() {
        return "Negative transfer attempt: source" + this.origin.getHash() +
        ", receiver: " + this.origin.getHash() +
        ", amount: " + this.balance;
    }
}

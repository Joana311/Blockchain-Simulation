/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Notifications;

import Transaction.Transaction;

public class TransactionNotification implements IMessage {

    private Transaction transaction;

    public TransactionNotification(Transaction transaction) {
        this.transaction = transaction;
    }

    /*____________________________________________________________________*/
    public Transaction getTransaction() {
        return this.transaction;
    }

    /*____________________________________________________________________*/
    @Override
    public String getMessage() {
        return "Transaction " + this.transaction.getId()
                + "| from: " + this.transaction.getOriginPublicKey()
                + ", to: " + this.transaction.getDestinationPublicKey()
                + ", quantity: " + this.transaction.getBalance();
    }

}

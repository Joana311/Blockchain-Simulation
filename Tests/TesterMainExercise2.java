package Tests;

import Interfaces.*;
import Transaction.*;
import Exceptions.*;

public class TesterMainExercise2 extends TesterMainExercise1 {

    public void buildFaultyNetwork() {
        super.buildNetwork();
        try {
            this.network.connect(this.node); // cannot connect: node already in the network
        } catch (ConnectionException e) {
            System.err.println(e);
        }
        try {
            this.network.connect(this.miningNode2); // cannot connect: miningNode in a subnet
        } catch (DuplicateConnectionException e) {
            System.err.println(e);
        }
    }

    public void createTransactions() {
        try {
            Transaction tr1 = node.createTransaction(wallet2, 10);
            
            /* TODO */
            //network.broadcast(new TransactionNotification(tr1));
            Transaction tr2 = miningNode.createTransaction(wallet1.getPublicKey(), -1);// negative fails
            
            /* TODO */
            //network.broadcast(new TransactionNotification(tr2));
        } catch (TransactionException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        TesterMainExercise2 tme = new TesterMainExercise2();
        tme.buildFaultyNetwork();
        tme.createTransactions();
    }
}

package Node;

import java.util.*;
import Wallet.*;
import Transaction.*;

public class Node {

    public static Integer count = 0;

    private Integer id;
    private Wallet wallet;
    private HashSet<Transaction> transactions = new HashSet<>();

    public Node(Wallet wallet) {
        this.wallet = wallet;
        this.id = count;
        count++;
    }

    /*_______________________________*/
    public Integer getId() {
        return this.id;
    }

    public Wallet getWallet() {
        return this.wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public HashSet<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(HashSet<Transaction> transactions) {
        this.transactions = transactions;
    }

    /*_______________________________*/
    public String fullName() {
        return "Node#" + String.format("%03d", this.id);
    }

    @Override
    public String toString() {
        return this.wallet.toString()
                + " | @" + this.fullName();
    }
}

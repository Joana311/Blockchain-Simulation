package Node;

import java.util.*;
import Wallet.*;
import Transaction.*;
import Exceptions.*;
import Interfaces.*;

public class Node implements IConnectable {

    public static Integer count = 0;

    private final Integer id;
    private Wallet wallet;
    private List<Transaction> transactions = new ArrayList<>();
    private IConnectable parent;

    public Node(Wallet wallet) {
        this.wallet = wallet;
        this.parent = null;
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

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public IConnectable getParent() {
        return this.parent;
    }

    public void setParent(IConnectable parent) {
        this.parent = parent;
    }

    @Override
    public void broadcast(IMessage msg) {
    }

    /*_______________________________*/
    public Transaction createTransaction(Wallet wallet, Integer balance) throws TransactionException {
        if (balance < 0) {
            throw new TransactionException(this.wallet, wallet, balance);
        }

        Transaction transaction = new Transaction(this.wallet, wallet, balance);
        this.transactions.add(transaction);
        return transaction;
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

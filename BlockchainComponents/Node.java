package BlockchainComponents;

import java.util.*;
import Transaction.*;
import Wallet.*;
import Interfaces.*;
import Exceptions.*;

public class Node extends BlockchainComponent {

    private Wallet wallet;
    private List<Transaction> transactions = new ArrayList<>();

    public Node(Wallet wallet) {
        super();
        this.wallet = wallet;
    }

    /*____________________________________________________________________*/
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

    /*____________________________________________________________________*/
    @Override
    public void broadcast(IMessage msg) {
        msg.process(this);
    }

    @Override
    public String fullName(String separator) {
        return "Node" + separator + this.formatId();
    }

    /*____________________________________________________________________*/
    private Transaction createTransactionMethod(String destinationPublicKey, Integer balance) throws TransactionException {
        String originPublicKey = this.wallet.getPublicKey();

        if (balance < 0) {
            throw new TransactionException(originPublicKey, destinationPublicKey, balance);
        }

        Transaction transaction = new Transaction(originPublicKey, destinationPublicKey, balance);
        this.transactions.add(transaction);
        return transaction;
    }

    public Transaction createTransaction(Wallet destinationWallet, Integer balance) throws TransactionException {
        return createTransactionMethod(destinationWallet.getPublicKey(), balance);
    }

    public Transaction createTransaction(String destinationPublicKey, Integer balance) throws TransactionException {
        return createTransactionMethod(destinationPublicKey, balance);
    }

    /*____________________________________________________________________*/
    @Override
    public String toString() {
        return this.wallet.toString()
                + " | @" + this.fullName();
    }
}

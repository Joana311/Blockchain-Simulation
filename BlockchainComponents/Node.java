package BlockchainComponents;

import java.util.*;
import Transaction.*;
import Wallet.*;
import Interfaces.*;
import Exceptions.*;
import ValidateBlockMsg.*;

public class Node extends BlockchainComponent {

    private Wallet wallet;
    
    private List<Transaction> nonValidatedTransactions = new ArrayList<>();
    private List<Transaction> validatedTransactions = new ArrayList<>();

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

    public List<Transaction> getValidatedTransactions() {
        return this.validatedTransactions;
    }
    
    public List<Transaction> getNonValidatedTransactions() {
        return this.nonValidatedTransactions;
    }

    

    /*____________________________________________________________________*/
    @Override
    public void broadcast(IMessage msg) {
        msg.process(this);
        
        if (msg instanceof TransactionNotification transactionNotification) {
            if (this.nonValidatedTransactions.contains(transactionNotification.getTransaction()) == false)
                this.nonValidatedTransactions.add(transactionNotification.getTransaction());
        }
        
        if (msg instanceof ValidateBlockRes validateBlockRes)
        {
            Transaction transaction = validateBlockRes.getBlock().getTransaction();
            System.out.println("[" + this.fullName()+ "] Commiting transaction : " +
                    "Tx-" + transaction.getId() + " in " + this.fullName());
            System.out.println("[" + this.fullName()+ "] -> Tx details:" + transaction);
            this.nonValidatedTransactions.remove(transaction);
            
            /* If the node is not part of the transaction, stop */
            if (transaction.getOriginPublicKey() != this.wallet.getPublicKey() &&
                    transaction.getDestinationPublicKey() != this.wallet.getPublicKey())
                return ;
            
            /* Else, check the transaction (the pdf dont say nothing about an error, so I make a case) */
            if (validateBlockRes.getState() == false) {
                System.out.print("[" + this.fullName() + "] No Applied Transaction: " + transaction);
                return ;
            }
            
            this.validatedTransactions.add(transaction);
            System.out.print("[" + this.fullName() + "] Applied Transaction: " + transaction);
            
            /* Do the changes on the balance */
            Integer count = (transaction.getOriginPublicKey() == this.wallet.getPublicKey() ? count = -transaction.getBalance() : transaction.getBalance());
            this.wallet.setBalance(this.wallet.getBalance() + count);
            
            System.out.println("[" + this.fullName() + "] New wallet value: " + this.wallet);
            
        }
    }

    @Override
    public String fullName() {
        return "Node#" + this.formatId();
    }

    /*____________________________________________________________________*/
    private Transaction createTransactionMethod(String destinationPublicKey, Integer balance) throws TransactionException {
        String originPublicKey = this.wallet.getPublicKey();

        if (balance < 0) {
            throw new TransactionException(originPublicKey, destinationPublicKey, balance);
        }

        Transaction transaction = new Transaction(originPublicKey, destinationPublicKey, balance);
        this.nonValidatedTransactions.add(transaction);
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

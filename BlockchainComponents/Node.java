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
    protected void nodeMine(Transaction transaction) {
    }

    protected void nodeValidateBlock(ValidateBlockRq validateBlockRq) {
    }

    @Override
    public void broadcast(IMessage msg) {
        msg.process(this);
        if (msg instanceof TransactionNotification transactionNotification) {
            Transaction transaction = transactionNotification.getTransaction();

            /* If the transaction is already confirmed, msg and stop */
            if (this.validatedTransactions.contains(transaction)) {
                System.out.println("[" + this.fullName() + "] Transaction already confirmed: Tx" + transaction.getId());
                return;
            }

            /* If the transaction is not confirmed and not no confirmed, save */
            if (this.nonValidatedTransactions.contains(transaction) == false) {
                this.nonValidatedTransactions.add(transaction);
            }

            /* Mine a block to the transaction (if there is posible) */
            this.nodeMine(transaction);
        }

        if (msg instanceof ValidateBlockRq validateBlockRq) {
            /* Validate the block */
            this.nodeValidateBlock(validateBlockRq);
        }

        if (msg instanceof ValidateBlockRes validateBlockRes) {
            /* Check and print the transaction info */
            Transaction transaction = validateBlockRes.getBlock().getTransaction();
            System.out.println("[" + this.fullName() + "] Commiting transaction : "
                    + "Tx-" + transaction.getId() + " in " + this.fullName());
            System.out.println("[" + this.fullName() + "] -> Tx details:" + transaction);

            /* Else, check the transaction (the pdf dont say nothing about an error, so I make a case) */
            if (validateBlockRes.getState() == false) {
                System.out.println("[" + this.fullName() + "] No Applied Transaction: " + transaction);
                return;
            }

            /* Add the valid transaction */
            this.nonValidatedTransactions.remove(transaction);
            this.validatedTransactions.add(transaction);
            System.out.println("[" + this.fullName() + "] Applied Transaction: " + transaction);

            /* If the node is not part of the transaction, stop */
            if (transaction.getOriginPublicKey().equals(this.wallet.getPublicKey()) == false
                    && transaction.getDestinationPublicKey().equals(this.wallet.getPublicKey()) == false) {
                return;
            }

            /* Do the changes on the balance and print it */
            Integer count = (transaction.getOriginPublicKey().equals(this.wallet.getPublicKey()) ? -1 * transaction.getBalance() : transaction.getBalance());
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

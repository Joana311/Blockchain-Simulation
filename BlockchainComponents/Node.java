package BlockchainComponents;

import Notifications.IMessage;
import Notifications.ValidateBlockRq;
import Notifications.ValidateBlockRes;
import Notifications.TransactionNotification;
import java.util.*;
import Transaction.*;
import Wallet.*;
import Exceptions.*;

/**
 * This is the Node class that extends the BlockchainComponent class. It
 * represents a node in a blockchain.
 *
 * @author Gonzalo Jimenez, Luis Pastor
 */
public class Node extends BlockchainComponent {

    private Wallet wallet;

    protected List<Transaction> nonValidatedTransactions = new ArrayList<>();
    protected List<Transaction> validatedTransactions = new ArrayList<>();

    /**
     * Node class constructor. Initializes a new Node with the provided wallet.
     *
     * @param wallet The wallet of the Node.
     */
    public Node(Wallet wallet) {
        super();
        this.wallet = wallet;
    }

    /**
     * Retrieves the wallet of the Node.
     *
     * @return The wallet of the Node.
     */
    public Wallet getWallet() {
        return this.wallet;
    }

    /**
     * Sets the wallet of the Node.
     *
     * @param wallet The wallet to set.
     */
    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    /**
     * Retrieves the list of validated transactions of the Node.
     *
     * @return The list of validated transactions.
     */
    public List<Transaction> getValidatedTransactions() {
        return this.validatedTransactions;
    }

    /**
     * Retrieves the list of non-validated transactions of the Node.
     *
     * @return The list of non-validated transactions.
     */
    public List<Transaction> getNonValidatedTransactions() {
        return this.nonValidatedTransactions;
    }

    protected void nodeMine(Transaction transaction) {
    }

    protected void nodeValidateBlock(ValidateBlockRq validateBlockRq) {
    }

    /**
     * Processes a message. If the message is a TransactionNotification, it
     * mines a block with the transaction. If the message is a ValidateBlockRq,
     * it validates the block. If the message is a ValidateBlockRes, it commits
     * the transaction.
     *
     * @param msg The message to process.
     */
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

    /**
     * Returns a string representation of the Node. The string contains "Node#"
     * and the formatted ID of the Node.
     *
     * @return A string representation of the Node.
     */
    @Override
    public String fullName() {
        return "Node#" + this.formatId();
    }

    /**
     * Creates a new transaction with the specified destination public key and
     * balance. If the balance is less than 0, it throws a TransactionException.
     * Otherwise, it creates a new transaction, adds it to the list of
     * non-validated transactions, and returns it.
     *
     * @param destinationPublicKey The public key of the destination wallet.
     * @param balance The balance of the transaction.
     * @return The created transaction.
     * @throws TransactionException If the balance is less than 0.
     */
    private Transaction createTransactionMethod(String destinationPublicKey, Integer balance) throws TransactionException {
        String originPublicKey = this.wallet.getPublicKey();

        if (balance < 0) {
            throw new TransactionException(originPublicKey, destinationPublicKey, balance);
        }

        Transaction transaction = new Transaction(originPublicKey, destinationPublicKey, balance);
        this.nonValidatedTransactions.add(transaction);
        return transaction;
    }

    /**
     * Creates a new transaction with the specified destination wallet and
     * balance. It calls the createTransactionMethod with the public key of the
     * destination wallet and the balance.
     *
     * @param destinationWallet The destination wallet.
     * @param balance The balance of the transaction.
     * @return The created transaction.
     * @throws TransactionException If the balance is less than 0.
     */
    public Transaction createTransaction(Wallet destinationWallet, Integer balance) throws TransactionException {
        return createTransactionMethod(destinationWallet.getPublicKey(), balance);
    }

    /**
     * Creates a new transaction with the specified destination public key and
     * balance. It calls the createTransactionMethod with the destination public
     * key and the balance.
     *
     * @param destinationPublicKey The public key of the destination wallet.
     * @param balance The balance of the transaction.
     * @return The created transaction.
     * @throws TransactionException If the balance is less than 0.
     */
    public Transaction createTransaction(String destinationPublicKey, Integer balance) throws TransactionException {
        return createTransactionMethod(destinationPublicKey, balance);
    }

    /**
     * Returns a string representation of the Node. The string contains the
     * string representation of the wallet and the full name of the Node.
     *
     * @return A string representation of the Node.
     */
    @Override
    public String toString() {
        return this.wallet.toString()
                + " | @" + this.fullName();
    }
}

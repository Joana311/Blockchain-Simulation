package BlockchainComponents;

import Interfaces.*;
import Wallet.*;
import Block.*;
import java.util.*;
import Transaction.*;

public class MiningNode extends Node {

    Integer balance;
    private List<Transaction> validatedTransactions = new ArrayList<>();
    private List<Block> validatedBlock = new ArrayList<>();
    IMiningMethod miningMethod;
    IValidateMethod validateMethod;

    public MiningNode(Wallet wallet, Integer balance) {
        super(wallet);
        this.balance = balance;
        this.miningMethod = null;
        this.validateMethod = null;
    }

    /*____________________________________________________________________*/
    public Integer getBalance() {
        return this.balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    
    public List<Transaction> getValidatedTransactions() {
        return this.validatedTransactions;
    }
        
    public List<Block> getValidatedBlock() {
        return this.validatedBlock;
    }
    
    public void setMiningMethod(IMiningMethod miningMethod) {
        this.miningMethod = miningMethod;
    }
    
    public void setValidateMethod(IValidateMethod validateMethod) {
        this.validateMethod = validateMethod;
    }

    /*____________________________________________________________________*/
    @Override
    public void broadcast(IMessage msg) {
        Transaction transaction = null;
        
        if (msg instanceof TransactionNotification transactionNotification) {
            transaction = transactionNotification.getTransaction();
        }
        
        /* If the transaction is not stored, is stored and a block is created and validated */
        if (this.validatedTransactions.contains(transaction) == false) {
            this.validatedTransactions.add(transaction);
            this.miningMethod.mineBlock(transaction, this.validatedBlock.getLast(), this.getWallet().getPublicKey());
        }
    }

    @Override
    public String fullName() {
        return "MiningNode#" + this.formatId();
    }

}

package BlockchainComponents;

import Interfaces.*;
import Wallet.*;
import Block.*;
import java.util.*;
import Transaction.*;
import ValidateBlockMsg.*;

public class MiningNode extends Node {

    Integer balance;
    private List<Transaction> validatedTransactions = new ArrayList<>();
    private List<Block> validatedBlock = new ArrayList<>();
    IMiningMethod miningMethod;
    IValidateMethod validationMethod;

    public MiningNode(Wallet wallet, Integer balance) {
        super(wallet);
        this.balance = balance;
        this.miningMethod = null;
        this.validationMethod = null;
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
    
    public void setValidationMethod(IValidateMethod validationMethod) {
        this.validationMethod = validationMethod;
    }

    /*____________________________________________________________________*/
    @Override
    public void broadcast(IMessage msg) {
        
        /*if (msg instanceof TransactionNotification transactionNotification) {
            transaction = transactionNotification.getTransaction();
        }
        
        if (transaction != null && this.validatedTransactions.contains(transaction) == false &&
                this.miningMethod != null && this.validationMethod != null) {
            
            Block lastValidatedBlock = (this.validatedBlock.isEmpty() ? null : this.validatedBlock.getLast());
            
            this.validatedTransactions.add(transaction);
            Block minedBlock = this.miningMethod.mineBlock(transaction, lastValidatedBlock,
                    this.getWallet().getPublicKey());
            
            this.getTopParent().broadcast(new ValidateBlockRq(minedBlock, this));
        }*/
        
        msg.process(this);
        if (msg instanceof TransactionNotification transactionNotification) {
            Transaction transaction = transactionNotification.getTransaction();
            
            /* If there is any problem trying to mine, exit the function */
            if (this.miningMethod == null ||
                    transaction == null || this.validatedTransactions.contains(transaction) == true)
                return ;            
            /* Get the info to mine and mine */
            Block lastValidatedBlock = (this.validatedBlock.isEmpty() ? null : this.validatedBlock.getLast());
            this.validatedTransactions.add(transaction);
            Block minedBlock = this.miningMethod.mineBlock(transaction, lastValidatedBlock,
                    this.getWallet().getPublicKey());
            
            System.out.println("[" + this.fullName() + "] Mined block: " + minedBlock);
            this.getTopParent().broadcast(new ValidateBlockRq(minedBlock, this));
            return ;
        }
        
        if (msg instanceof ValidateBlockRq validateBlockRq) {
            /* If the node is the one that created the block, dont do nothing */
            if (validateBlockRq.getNode() == this) {
                System.out.println("[" + this.fullName() + "] You cannot validate your own block");
                return ;
            }
            
            /* If it is another node, it will validate it (if it has a validate method) */
            if (this.validationMethod == null)
                return ;
            
            Boolean state = this.validationMethod.validate(this.miningMethod,
                                                            validateBlockRq.getBlock());
            
            ValidateBlockRes response = new ValidateBlockRes(validateBlockRq.getBlock(), state, this);
            System.out.println("[" + this.fullName() + "] Emitted Task: " + response.getMessage());
            this.getTopParent().broadcast(response);
        }
        
    }

    @Override
    public String fullName() {
        return "MiningNode#" + this.formatId();
    }

}

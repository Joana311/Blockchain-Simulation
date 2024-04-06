package BlockchainComponents;

import Interfaces.*;
import Wallet.*;
import Block.*;
import java.util.*;
import CommonUtils.*;
import Transaction.*;

public class MiningNode extends Node implements IMiningMethod {

    Integer balance;
    private List<Transaction> validatedTransactions = new ArrayList<>();
    private List<Block> validatedBlock = new ArrayList<>();

    public MiningNode(Wallet wallet, Integer balance) {
        super(wallet);
        this.balance = balance;
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
        }
    }

    @Override
    public String fullName() {
        return "MiningNode#" + this.formatId();
    }

    /*____________________________________________________________________*/
    
    /*
        Version del bloque
        Hash del bloque anterior o GENESIS_BLOCK
        Timestamp
        Dificultad
        Nonce
    */
    @Override
    public String createHash(Block block) {
        String buffer;
        
        buffer = block.getVersion().toString() + 
                (block.getPrevious() != null ? block.getPrevious().getHash() : BlockConfig.GENESIS_BLOCK) + 
                block.getTimestamp() +
                block.getDifficulty() +
                block.getNonce();
        
        return CommonUtils.sha256(buffer);
    }
    
    /* TODO */
    @Override
    public Block mineBlock(Transaction transaction, Block previousConfirmedBlock, String minerKey) {
        return null;
    }
    
    /*____________________________________________________________________*/
}

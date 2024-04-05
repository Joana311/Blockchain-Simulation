package BlockchainComponents;

import Interfaces.*;
import Wallet.*;
import Block.*;
import java.util.*;
import CommonUtils.*;
import Transaction.*;

public class MiningNode extends Node implements IMiningMethod {

    Integer balance;
    private List<Block> validatedBlocks = new ArrayList<>();

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
    
    public List<Block> getValidatedBlocks() {
        return this.validatedBlocks;
    }
    
    public void setValidatedBlocks(List<Block> validatedBlocks) {
        this.validatedBlocks = validatedBlocks;
    }
    
    public void addValidatedBlock(Block block) {
        this.validatedBlocks.add(block);
    }

    /*____________________________________________________________________*/
    @Override
    public void broadcast(IMessage msg) {
        msg.process(this);
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

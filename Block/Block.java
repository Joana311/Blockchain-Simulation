package Block;

import CommonUtils.*;
import java.util.Date;
import Transaction.*;

public class Block {

    private static Integer count = 0;

    private Integer id;
    private Integer version;
    private Integer Nonce;
    private Integer timestamp;
    private Integer difficulty;
    private boolean validated;
    private String hash;
    private Block previous;

    private Transaction transaction;

    public Block(Transaction transaction) {
        /* Set the block id */
        this.id = count;
        count++;

        /* Added the default information */
        this.version = BlockConfig.VERSION;
        this.Nonce = (int) Math.floor(Math.random() * 1000);
        this.timestamp = (int) (new Date().getTime() / 1000);
        this.difficulty = BlockConfig.DIFFICULTY;
        this.validated = false;
        this.hash = null;
        this.previous = null;

        /* Added arguments */
        this.transaction = transaction;
    }
    
    /*____________________________________________________________________*/
    
    public Integer getId() {
        return this.id;
    }
    
    public Integer getVersion() {
        return this.version;
    }
    
    public Integer getNonce() {
        return this.Nonce;
    }
    
    public Integer getTimestamp() {
        return this.timestamp;
    }
    
    public Integer getDifficulty() {
        return this.difficulty;
    }
    
    public boolean getValidated() {
        return this.validated;
    }
    
    public void setValidated(boolean validated) {
        this.validated = validated;
    }
    
    public String getHash() {
        return this.hash;
    }
    
    public void setHash(String hash) {
        this.hash = hash;
    }
    
    public Block getPrevious() {
        return this.previous;
    }
    
    public void setPrevious(Block previous) {
        this.previous = previous;
    }
    
    public Transaction getTransaction() {
        return this.transaction;
    }
    
    /*____________________________________________________________________*/
    
}

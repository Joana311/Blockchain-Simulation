package Block;

import CommonUtils.*;
import java.util.Date;
import Transaction.*;

public class Block {

    private static Integer count = 0;

    private final Integer id;
    private final Integer version;
    private final Integer Nonce;
    private final Integer timestamp;
    private final Integer difficulty;
    private boolean validated;
    private String hash;
    private Block previous;

    private final Transaction transaction;
    private String minerKey;

/**
 * Block class constructor.
 * Initializes a new block with the provided transaction and sets up default values.
 *
 * @param transaction The transaction to be included in this block.
 */
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
        this.minerKey = null;
    }

    
    /**
 * Retrieves the block's ID.
 *
 * @return The block's ID.
 */
    public Integer getId() {
        return this.id;
    }

/**
 * Retrieves the block's version.
 *
 * @return The block's version.
 */
    public Integer getVersion() {
        return this.version;
    }

/**
 * Retrieves the block's nonce.
 * The nonce is a number that is used once in cryptography.
 *
 * @return The block's nonce.
 */
    public Integer getNonce() {
        return this.Nonce;
    }

/**
 * Retrieves the block's timestamp.
 *
 * @return The block's timestamp.
 */
    public Integer getTimestamp() {
        return this.timestamp;
    }

/**
 * Retrieves the block's difficulty.
 * The difficulty is a parameter that determines the amount of work required to mine a block.
 *
 * @return The block's difficulty.
 */
    public Integer getDifficulty() {
        return this.difficulty;
    }

/**
 * Retrieves the block's validation status.
 *
 * @return The block's validation status.
 */
    public boolean getValidated() {
        return this.validated;
    }

/**
 * Sets the block's validation status.
 *
 * @param validated The validation status to set.
 */
    public void setValidated(boolean validated) {
        this.validated = validated;
    }

/**
 * Retrieves the block's hash.
 *
 * @return The block's hash.
 */
    public String getHash() {
        return this.hash;
    }

/**
 * Sets the block's hash.
 *
 * @param hash The hash to set.
 */
    public void setHash(String hash) {
        this.hash = hash;
    }

/**
 * Retrieves the previous block in the blockchain.
 *
 * @return The previous block.
 */
    public Block getPrevious() {
        return this.previous;
    }

/**
 * Sets the previous block in the blockchain.
 *
 * @param previous The block to set as the previous block.
 */
    public void setPrevious(Block previous) {
        this.previous = previous;
    }

/**
 * Retrieves the transaction associated with the block.
 *
 * @return The transaction.
 */
    public Transaction getTransaction() {
        return this.transaction;
    }

/**
 * Retrieves the miner's key.
 *
 * @return The miner's key.
 */
    public String getMinerKey() {
        return this.minerKey;
    }

/**
 * Sets the miner's key.
 *
 * @param minerKey The miner's key to set.
 */
    public void setMinerKey(String minerKey) {
        this.minerKey = minerKey;
    }


    /**
 * Returns a string representation of the block.
 *
 * @return A string representation of the block.
 */
    @Override
    public String toString() {
        return "id:" + this.id + ", "
                + "v:" + this.version + ", "
                + "nonce: " + this.Nonce + ", "
                + "ts: " + this.timestamp + ", "
                + "diff: " + this.difficulty + ", "
                + "hash: " + this.hash;
    }

}

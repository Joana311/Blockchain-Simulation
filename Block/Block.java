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
}

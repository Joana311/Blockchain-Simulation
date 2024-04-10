package ValidateBlockMsg;

import BlockchainComponents.*;
import Block.*;
import Interfaces.*;

public class ValidateBlockRq implements IMessage {
    private final Block block;
    private final Node node;
    
    public ValidateBlockRq(Block block, Node node) {
        this.block = block;
        this.node = node;
    }
    
    /*____________________________________________________________________*/
    
    public Block getBlock() {
        return this.block;
    }
    
    public Node getNode() {
        return this.node;
    }
    
    /*____________________________________________________________________*/
    
    /* TODO */
    @Override
    public String getMessage() {
        return null;
    }
}

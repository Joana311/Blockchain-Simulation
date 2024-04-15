package Notifications;

import BlockchainComponents.*;
import Block.*;

public class ValidateBlockRq extends ValidateBlock {

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
        return "ValidateBlockRq: <b:" + this.block.getId()
                + ", src: " + this.node.formatId() + ">";
    }
}

package Notifications;

import Notifications.ValidateBlock;
import Block.*;
import BlockchainComponents.*;

public class ValidateBlockRes extends ValidateBlock {

    private Block block;
    private boolean state;
    private Node nodeValidated;

    public ValidateBlockRes(Block block, boolean state, Node nodeValidated) {
        this.block = block;
        this.state = state;
        this.nodeValidated = nodeValidated;
    }

    /*____________________________________________________________________*/
    public Block getBlock() {
        return this.block;
    }

    public boolean getState() {
        return this.state;
    }

    public Node getNode() {
        return this.nodeValidated;
    }

    /*____________________________________________________________________*/
    @Override
    public String getMessage() {
        return "ValidateBlockRes: <b:" + this.block.getId() + ", "
                + "res:" + this.state + ", "
                + "src:" + this.nodeValidated.formatId() + ">";
    }
}

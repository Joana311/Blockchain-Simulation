package Notifications;

import Block.*;
import BlockchainComponents.*;

/**
 * Class ValidateBlockRes extends ValidateBlock.
 * 
 * @author Gonzalo Jiménez, Luis Pastor
 */
public class ValidateBlockRes extends ValidateBlock {

    private final Block block;
    private final boolean state;
    private final Node nodeValidated;

    /**
     * Constructor for ValidateBlockRes.
     * 
     * @param block The block to be validated.
     * @param state The validation state.
     * @param nodeValidated The node that validated the block.
     */
    public ValidateBlockRes(Block block, boolean state, Node nodeValidated) {
        this.block = block;
        this.state = state;
        this.nodeValidated = nodeValidated;
    }

    /**
     * Get the block to be validated.
     * 
     * @return The block to be validated.
     */
    public Block getBlock() {
        return this.block;
    }

    /**
     * Get the validation state.
     * 
     * @return The validation state.
     */
    public boolean getState() {
        return this.state;
    }

    /**
     * Get the node that validated the block.
     * 
     * @return The node that validated the block.
     */
    public Node getNode() {
        return this.nodeValidated;
    }

    /**
     * Get the message for ValidateBlockRes.
     * 
     * @return The message for ValidateBlockRes.
     */
    @Override
    public String getMessage() {
        return "ValidateBlockRes: <b:" + this.block.getId() + ", "
                + "res:" + this.state + ", "
                + "src:" + this.nodeValidated.formatId() + ">";
    }
}

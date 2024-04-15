package Notifications;

import Block.*;
import BlockchainComponents.*;

/**
 * Class ValidateBlockRes extends ValidateBlock.
 *
 * @author Gonzalo Jiménez, Luis Pastor
 */
public class ValidateBlockRes extends ValidateBlock {
    private final boolean state;

    /**
     * Constructor for ValidateBlockRes.
     *
     * @param block The block to be validated.
     * @param state The validation state.
     * @param node The node that validated the block.
     */
    public ValidateBlockRes(Block block, boolean state, Node node) {
        super(block, node);
        this.state = state;
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
     * Get the message for ValidateBlockRes.
     *
     * @return The message for ValidateBlockRes.
     */
    @Override
    public String getMessage() {
        return "ValidateBlockRes: <b:" + this.block.getId() + ", "
                + "res:" + this.state + ", "
                + "src:" + this.node.formatId() + ">";
    }
}

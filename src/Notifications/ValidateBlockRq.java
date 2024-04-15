package Notifications;

import BlockchainComponents.*;
import Block.*;

/**
 * This class represents a request to validate a block in the blockchain. It
 * extends the ValidateBlock class.
 *
 * @author Gonzalo Jiménez, Luis Pastor
 */
public class ValidateBlockRq extends ValidateBlock {
    /**
     * Constructs a ValidateBlockRq object with the specified block and node.
     *
     * @param block the block to be validated
     * @param node the node requesting the validation
     */
    public ValidateBlockRq(Block block, Node node) {
        super(block, node);
    }

    /**
     * TODO: Add description here.
     *
     * @return the message for the ValidateBlockRq
     */
    @Override
    public String getMessage() {
        return "ValidateBlockRq: <b:" + this.block.getId()
                + ", src: " + this.node.formatId() + ">";
    }
}

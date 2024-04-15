package Notifications;

import BlockchainComponents.*;
import Block.*;

/**
 * This class represents a request to validate a block in the blockchain.
 * It extends the ValidateBlock class.
 *
 * @author Gonzalo Jiménez, Luis Pastor
 */
public class ValidateBlockRq extends ValidateBlock {

    private final Block block;
    private final Node node;

    /**
     * Constructs a ValidateBlockRq object with the specified block and node.
     *
     * @param block the block to be validated
     * @param node the node requesting the validation
     */
    public ValidateBlockRq(Block block, Node node) {
        this.block = block;
        this.node = node;
    }

    /**
     * Returns the block to be validated.
     *
     * @return the block to be validated
     */
    public Block getBlock() {
        return this.block;
    }

    /**
     * Returns the node requesting the validation.
     *
     * @return the node requesting the validation
     */
    public Node getNode() {
        return this.node;
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

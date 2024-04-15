package Notifications;

import BlockchainComponents.*;
import Block.*;

/**
 * This abstract class represents a block validation message. It implements the
 * IMessage interface.
 *
 * @author Gonzalo Jiménez, Luis Pastor
 */
public abstract class ValidateBlock implements IMessage {

    protected Block block;
    protected Node node;
    
    /**
     * Constructs a new ValidateBlock object.
     * @param block Block to validate
     * @param node Node that sends the request
     */
    public ValidateBlock(Block block, Node node) {
        this.block = block;
        this.node = node;
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
     * Returns the node requesting the validation.
     *
     * @return the node requesting the validation
     */
    public Node getNode() {
        return this.node;
    }
    
    /**
     * Returns the message associated with the ValidateBlock object.
     *
     * @return the message associated with the ValidateBlock object.
     */
    @Override
    public abstract String getMessage();

    /**
     * Processes the ValidateBlock object by printing the received task message.
     *
     * @param n the Node object representing the current node.
     */
    @Override
    public void process(Node n) {
        System.out.println("[" + n.fullName() + "] "
                + "Received Task: " + this.getMessage());
    }

}

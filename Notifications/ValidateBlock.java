package Notifications;

import BlockchainComponents.*;


/**
 * This abstract class represents a block validation message.
 * It implements the IMessage interface.
 * 
 * @author Gonzalo Jiménez, Luis Pastor
 */
public abstract class ValidateBlock implements IMessage {

    /**
     * Constructs a new ValidateBlock object.
     */
    public ValidateBlock() {
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

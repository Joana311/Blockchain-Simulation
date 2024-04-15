package Exceptions;

import BlockchainComponents.*;

/**
 * This exception is thrown when a duplicate connection is detected in the blockchain network.
 * It is a subclass of ConnectionException.
 *
 * @author Gonzalo Jiménez, Luis Pastor
 */
public class DuplicateConnectionException extends ConnectionException {

    /**
     * Constructs a new DuplicateConnectionException with the specified node.
     *
     * @param node the node that caused the exception
     */
    public DuplicateConnectionException(Node node) {
        super(node);
    }

    /**
     * Returns a string representation of the exception.
     *
     * @return the string representation of the exception
     */
    @Override
    public String toString() {
        return "Connection exception: Node " + String.format("%03d", this.getNode().getId())
                + " is connected to a different network";
    }
}

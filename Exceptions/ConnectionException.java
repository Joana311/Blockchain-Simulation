package Exceptions;

import BlockchainComponents.*;

/**
 * This exception is thrown when a connection to a node in the network is
 * already established. It extends the RuntimeException class.
 *
 * @author Gonzalo Jimenez, Luis Pastor
 */
public class ConnectionException extends RuntimeException {

    private final Node node;

    /**
     * Constructs a ConnectionException with the specified node.
     *
     * @param node the node that is already connected to the network
     */
    public ConnectionException(Node node) {
        this.node = node;
    }

    /**
     * Returns the node that is already connected to the network.
     *
     * @return the node that is already connected
     */
    public Node getNode() {
        return this.node;
    }

    /**
     * Returns a string representation of the ConnectionException.
     *
     * @return a string representation of the exception
     */
    @Override
    public String toString() {
        return "Connection exception: Node " + String.format("%03d", this.node.getId())
                + " is already connected to the network";
    }
}

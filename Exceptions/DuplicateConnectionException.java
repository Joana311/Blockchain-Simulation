package Exceptions;

import BlockchainComponents.*;

public class DuplicateConnectionException extends ConnectionException {

    public DuplicateConnectionException(Node node) {
        super(node);
    }

    @Override
    public String toString() {
        return "Connection exception: Node " + String.format("%03d", this.getNode().getId())
                + " is connected to a different network";
    }
}

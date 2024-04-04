package Exceptions;

import Node.*;

public class ConnectionException extends RuntimeException {
    private Node node;
    
    public ConnectionException(Node node) {
        this.node = node;
    }
    
    public Node getNode() {
        return this.node;
    }
    
    @Override
    public String toString() {
        return "Connection exception: Node " + String.format("%03d", this.node.getId()) + 
                " is already connected to the network";
    }
}

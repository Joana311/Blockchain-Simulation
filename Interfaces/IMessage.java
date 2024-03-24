package Interfaces;

import Node.*;

public interface IMessage {

    public String getMessage();

    public default void process(Node n) {
        System.out.println("[" + n.fullName() + "]"
                + " - Received notification - Nex Tx: "
                + this.getMessage());
    }
}

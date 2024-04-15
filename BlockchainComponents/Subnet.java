package BlockchainComponents;

import Notifications.IMessage;
import Notifications.ValidateBlockRq;
import Notifications.ValidateBlockRes;
import Notifications.TransactionNotification;
import java.util.*;

/**
 * Represents a subnet in a blockchain network.
 * 
 * @author Gonzalo Jimenez, Luis Pastor
 */
public class Subnet extends BlockchainComponent {

    private List<Node> nodes = new ArrayList<>();

    /**
     * Constructs a Subnet object with the given nodes.
     *
     * @param nodes the nodes to be added to the subnet
     */
    public Subnet(Node... nodes) {
        super();

        for (Node current : nodes) {
            current.setParent(this);
            this.nodes.add(current);
        }
    }

    /**
     * Returns the list of nodes in the subnet.
     *
     * @return the list of nodes in the subnet
     */
    public List<Node> getNodes() {
        return this.nodes;
    }

    /**
     * Sets the list of nodes in the subnet.
     *
     * @param nodes the list of nodes to be set
     */
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * Broadcasts a message to all the nodes in the subnet.
     *
     * @param msg the message to be broadcasted
     */
    @Override
    public void broadcast(IMessage msg) {
        System.out.print("[" + this.fullName() + "] ");
        if (msg instanceof TransactionNotification) {
            System.out.println(msg.getMessage());
        } else if (msg instanceof ValidateBlockRq) {
            System.out.println("ValidateBlockRq");
        } else if (msg instanceof ValidateBlockRes) {
            System.out.println("ValidateBlockRes");
        }

        System.out.println("Broadcasting to " + this.nodes.size() + " nodes:");
        for (Node node : this.nodes) {
            node.broadcast(msg);
        }
    }

    /**
     * Returns the full name of the subnet.
     *
     * @return the full name of the subnet
     */
    @Override
    public String fullName() {
        return "Subnet#" + this.formatId();
    }

    /**
     * Returns a string representation of the subnet.
     *
     * @return a string representation of the subnet
     */
    @Override
    public String toString() {
        String buffer;
        int i, nodesLength;

        /* Main comment */
        nodesLength = this.nodes.size();
        buffer = "Node network of " + nodesLength + " nodes: ";

        /* Saving the information of each node */
        i = 0;
        for (Node node : this.nodes) {
            buffer += "[" + node.toString() + "]";

            /* Unspecified case that there is more than one node in the subnet, so interpretation */
            i++;
            if (i != nodesLength) {
                buffer += ", ";
            }
        }

        return buffer;
    }
}

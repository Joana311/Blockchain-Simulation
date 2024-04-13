package BlockchainComponents;

import java.util.*;
import Interfaces.*;
import Transaction.*;
import ValidateBlockMsg.*;

public class Subnet extends BlockchainComponent {

    private List<Node> nodes = new ArrayList<>();

    public Subnet(Node... nodes) {
        super();

        for (Node current : nodes) {
            current.setParent(this);
            this.nodes.add(current);
        }
    }

    /*____________________________________________________________________*/
    public List<Node> getNodes() {
        return this.nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    /*____________________________________________________________________*/
    @Override
    public void broadcast(IMessage msg) {
        System.out.print("[" + this.fullName() + "] ");
        if (msg instanceof TransactionNotification)
            System.out.println(msg.getMessage());
        else if (msg instanceof ValidateBlockRq)
            System.out.println("ValidateBlockRq");
        else if (msg instanceof ValidateBlockRes)
            System.out.println("ValidateBlockRes");
        
        System.out.println("Broadcasting to " + this.nodes.size() + " nodes:");
        for (Node node : this.nodes) {
            node.broadcast(msg);
        }
    }

    @Override
    public String fullName() {
        return "Subnet#" + this.formatId();
    }

    /*____________________________________________________________________*/
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

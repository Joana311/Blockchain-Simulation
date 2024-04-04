package BlockchainNetwork;

import java.util.*;
import Node.*;
import Interfaces.*;

public class Subnet implements IConnectable {

    private static Integer count = 0;

    private Integer id;
    private List<Node> nodes = new ArrayList<>();
    private IConnectable parent;

    public Subnet(Node... nodes) {
        /* The id is setted, and the static value is incremented */
        count++;
        this.id = count;
        

        /* The nodes are inserted on the HashSet, and the parent is setted */
        for (Node current : nodes) {
            current.setParent(this);
            this.nodes.add(current);
        }
        this.parent = null;
    }

    /*___________________________________________*/
    public Integer getId() {
        return this.id;
    }

    public List<Node> getNodes() {
        return this.nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public IConnectable getParent() {
        return this.parent;
    }

    public void setParent(IConnectable parent) {
        this.parent = parent;
    }

    @Override
    public void broadcast(IMessage msg) {
        System.out.println( "[" + this.fullName() + "] " + msg.getMessage());
        for (Node node : this.nodes) {
            node.broadcast(msg);
        }
    }

    /*___________________________________________*/
    
    public String fullName() {
        return "Subnet#" + String.format("%03d", this.id);
    }
    
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

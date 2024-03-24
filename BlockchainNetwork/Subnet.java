package BlockchainNetwork;

import java.util.*;
import Node.*;

public class Subnet {

    private static Integer count = 0;

    private Integer id;
    private HashSet<Node> nodes = new HashSet<>();

    public Subnet(Node... nodes) {
        /* The id is setted, and the static value is incremented */
        count++;
        this.id = count;

        /* The nodes are inserted on the HashSet */
        this.nodes.addAll(Arrays.asList(nodes));
    }

    /*___________________________________________*/
    public Integer getId() {
        return this.id;
    }

    public HashSet<Node> getNodes() {
        return this.nodes;
    }

    public void setNodes(HashSet<Node> nodes) {
        this.nodes = nodes;
    }

    /*___________________________________________*/
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

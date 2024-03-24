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
}

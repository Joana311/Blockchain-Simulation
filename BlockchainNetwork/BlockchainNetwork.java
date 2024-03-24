package BlockchainNetwork;

import java.util.*;
import Node.*;

public class BlockchainNetwork {

    private String networkName;
    /*private HashSet<Node> nodes = new HashSet<>();
    private HashSet<Subnet> subnets = new HashSet<>();*/
    private List<Object> elements = new ArrayList<>();

    public BlockchainNetwork(String networkName) {
        this.networkName = networkName;
    }

    /*_____________________________________________*/
    public String getNetworkName() {
        return this.networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }
    
    /*public HashSet<Node> getNodes() {return this.nodes;}
    public void setNodes (HashSet<Node> nodes) {this.nodes = nodes;}
    
    public HashSet<Subnet> getSubmits () {return this.subnets;}
    public void setSubnets (HashSet<Subnet> subnets) {this.subnets = subnets;}*/
    
    public List<Object> getElements () { return this.elements;}
    public void setElements (List<Object> elements) {this.elements = elements;}

    /*_____________________________________________*/
    
    public BlockchainNetwork connect(Object data)
    {
        /* The variable is saved on the correct list */
        if (this.elements.contains(data))
            return this;
        this.elements.add(data);
        
        /* The connection is printed */
        System.out.println(this.networkName + " - new peer connected: " + data);
        return this;
    }
    
    @Override
    public String toString() {
        String buffer;
        
        buffer = this.networkName + " consists of " + this.elements.size() + " elements:\n";
        
        for (Object current : this.elements)
            buffer += "* " + current + "\n";
        
        return buffer;
    }
}

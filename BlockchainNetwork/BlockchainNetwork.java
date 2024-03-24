package BlockchainNetwork;

import java.util.*;
import Node.*;

public class BlockchainNetwork {

    private String networkName;
    private HashSet<Node> nodes = new HashSet<>();
    private HashSet<Subnet> subnets = new HashSet<>();

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
    
    public HashSet<Node> getNodes() {return this.nodes;}
    public void setNodes (HashSet<Node> nodes) {this.nodes = nodes;}
    
    public HashSet<Subnet> getSubmits () {return this.subnets;}
    public void setSubnets (HashSet<Subnet> subnets) {this.subnets = subnets;}

    /*_____________________________________________*/
    
}

package BlockchainComponents;

import Notifications.IMessage;
import java.util.*;
import Exceptions.*;

public class BlockchainNetwork implements IConnectable {

    private String networkName;
    private List<IConnectable> elements = new ArrayList<>();
    private IConnectable parent;

    public BlockchainNetwork(String networkName) {
        this.networkName = networkName;
        this.parent = null;
    }

    /*_____________________________________________*/
    public String getNetworkName() {
        return this.networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public List<IConnectable> getElements() {
        return this.elements;
    }

    public void setElements(List<IConnectable> elements) {
        this.elements = elements;
    }

    /*_____________________________________________*/
    public void setParent(IConnectable parent) {
        this.parent = parent;
    }

    @Override
    public IConnectable getParent() {
        return this.parent;
    }

    @Override
    public void broadcast(IMessage msg) {
        for (IConnectable element : this.elements) {
            element.broadcast(msg);
        }
    }

    /*_____________________________________________*/
    public BlockchainNetwork connect(IConnectable data) throws ConnectionException {
        if (data == null) {
            return this;
        }

        /* The variable is saved on the correct list */
        if ((data instanceof Node || data instanceof MiningNode)
                && data.getParent() != null) {
            if (data.getParent() == this) {
                throw new ConnectionException((Node) data);
            } else {
                throw new DuplicateConnectionException((Node) data);
            }
        }

        /* Added the element to the list */
        this.elements.add(data);

        /* Set the data parent */
        if (data instanceof Node || data instanceof MiningNode) {
            ((Node) data).setParent(this);
        } else if (data instanceof Subnet) {
            ((Subnet) data).setParent(this);
        } else {
            ((BlockchainNetwork) data).setParent(this);
        }

        /* Print connection */
        System.out.println(this.networkName + " - new peer connected: " + data);
        return this;
    }

    @Override
    public String toString() {
        String buffer;

        /* Start of the msg */
        buffer = this.networkName + " consists of " + this.elements.size() + " elements:\n";

        /* Saving the information of each element */
        for (Object current : this.elements) {
            buffer += "* " + current + "\n";
        }

        return buffer;
    }
}

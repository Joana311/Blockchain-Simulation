package BlockchainComponents;

import Notifications.IMessage;
import java.util.*;
import Exceptions.*;

/**
 * This is the BlockchainNetwork class that implements the IConnectable interface.
 * It represents a network in a blockchain.
 *
 * @author Gonzalo Jimenez, Luis Pastor
 */
public class BlockchainNetwork implements IConnectable {

    private String networkName;
    private List<IConnectable> elements = new ArrayList<>();
    private IConnectable parent;

/**
 * BlockchainNetwork class constructor.
 * Initializes a new BlockchainNetwork with the provided network name and sets the parent to null.
 *
 * @param networkName The name of the network.
 */
    public BlockchainNetwork(String networkName) {
        this.networkName = networkName;
        this.parent = null;
    }

    /**
 * Retrieves the network's name.
 *
 * @return The network's name.
 */
    public String getNetworkName() {
        return this.networkName;
    }

/**
 * Sets the network's name.
 *
 * @param networkName The name to set.
 */
    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

/**
 * Retrieves the elements of the network.
 *
 * @return The elements of the network.
 */
    public List<IConnectable> getElements() {
        return this.elements;
    }

/**
 * Sets the elements of the network.
 *
 * @param elements The elements to set.
 */
    public void setElements(List<IConnectable> elements) {
        this.elements = elements;
    }

    /**
 * Sets the parent of the network.
 *
 * @param parent The parent to set.
 */
    public void setParent(IConnectable parent) {
        this.parent = parent;
    }

/**
 * Retrieves the parent of the network.
 *
 * @return The parent of the network.
 */
    @Override
    public IConnectable getParent() {
        return this.parent;
    }

/**
 * Broadcasts a message to all elements in the network.
 *
 * @param msg The message to broadcast.
 */
    @Override
    public void broadcast(IMessage msg) {
        for (IConnectable element : this.elements) {
            element.broadcast(msg);
        }
    }

    /**
 * Connects a new IConnectable data to the network.
 * If the data is a Node or MiningNode and already has a parent, it throws a ConnectionException.
 * If the data is already connected to the network, it throws a DuplicateConnectionException.
 * Otherwise, it adds the data to the network's elements and sets the network as the data's parent.
 *
 * @param data The IConnectable data to connect to the network.
 * @return The network after the data has been connected.
 * @throws ConnectionException If the data is a Node or MiningNode and already has a parent.
 * @throws DuplicateConnectionException If the data is already connected to the network.
 */
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

/**
 * Returns a string representation of the network.
 * The string contains the network's name, the number of elements in the network,
 * and the string representation of each element.
 *
 * @return A string representation of the network.
 */
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

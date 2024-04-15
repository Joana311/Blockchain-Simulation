package BlockchainComponents;

import Notifications.IMessage;

/**
 * This is the abstract BlockchainComponent class that implements the
 * IConnectable interface. It represents a generic component in a blockchain.
 *
 * @author Gonzalo Jimenez, Luis Pastor
 */
public abstract class BlockchainComponent implements IConnectable {

    private static Integer count = 0;

    private final Integer id;
    private IConnectable parent;

    /**
     * BlockchainComponent class constructor. Initializes a new
     * BlockchainComponent with a unique ID and sets the parent to null.
     */
    public BlockchainComponent() {
        /* Set the parent to null */
        this.parent = null;

        /* Add the id and increment the count */
        this.id = count;
        count++;
    }

    /**
     * Retrieves the BlockchainComponent's ID.
     *
     * @return The BlockchainComponent's ID.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Retrieves the parent of the BlockchainComponent.
     *
     * @return The parent of the BlockchainComponent.
     */
    @Override
    public IConnectable getParent() {
        return this.parent;
    }

    /**
     * Sets the parent of the BlockchainComponent.
     *
     * @param parent The parent to set.
     */
    public void setParent(IConnectable parent) {
        this.parent = parent;
    }

    /**
     * @brief Function to recieve the full name of a BlockchainComponent, , with
     * a '#' between the component type and its id
     *
     * @return the full name of the node. Ex.: "<ComponentType>#<ID>"
     */
    public abstract String fullName();

    /**
     * @brief Function to get the id formatted with 3 numbers.
     *
     * @return The id formatted
     */
    public String formatId() {
        return String.format("%03d", this.id);
    }

    /*____________________________________________________________________*/
    @Override
    public abstract void broadcast(IMessage msg);

}

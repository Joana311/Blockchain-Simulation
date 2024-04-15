package BlockchainComponents;

import Notifications.IMessage;

public abstract class BlockchainComponent implements IConnectable {

    private static Integer count = 0;

    private final Integer id;
    private IConnectable parent;

    public BlockchainComponent() {
        /* Set the parent to null */
        this.parent = null;

        /* Add the id and increment the count */
        this.id = count;
        count++;
    }

    /*____________________________________________________________________*/
    public Integer getId() {
        return this.id;
    }

    @Override
    public IConnectable getParent() {
        return this.parent;
    }

    public void setParent(IConnectable parent) {
        this.parent = parent;
    }

    /**
     * @brief Function to recieve the full name of a BlockchainComponent, , with
     * a '#' between the component type and its ide
     * @return the full name of the node. Ex.: "<ComponentType>#<ID>"
     */
    public abstract String fullName();

    /**
     * @brief Function to get the id formatted with 3 numbers.
     * @return The id formatted
     */
    public String formatId() {
        return String.format("%03d", this.id);
    }

    /*____________________________________________________________________*/
    @Override
    public abstract void broadcast(IMessage msg);

}

package BlockchainComponents;

import Interfaces.*;

public abstract class BlockchainComponent implements IConnectable {
    private static Integer count = 0;
    
    private Integer id;
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
    
    public String fullName() {
        return this.fullName("#");
    }
    
    public String formatId() {
        return String.format("%03d", this.id);
    }
    
    /*____________________________________________________________________*/
    
    @Override
    public abstract void broadcast(IMessage msg);
    
    public abstract String fullName(String separator);
}

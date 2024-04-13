package ValidateBlockMsg;

import BlockchainComponents.*;
import Interfaces.*;

public abstract class ValidateBlock implements IMessage{
    
    public ValidateBlock() {
    }
    
    @Override
    public abstract String getMessage();
    
    @Override
    public void process(Node n) {
        System.out.println("[" + n.fullName() + "] " +
                "Received task: " + this.getMessage());
    }
    
}

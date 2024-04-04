package BlockchainComponents;

import Interfaces.IMessage;
import Wallet.*;

public class MiningNode extends Node {
    
    Integer balance;
    
    public MiningNode(Wallet wallet, Integer balance) {
        super(wallet);
        this.balance = balance;
    }
    
    /*____________________________________________________________________*/
    
    public Integer getBalance() {
        return this.balance;
    }
    
    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    
    /*____________________________________________________________________*/
    
    @Override
    public void broadcast(IMessage msg) {
        msg.process(this);
    }
    
    @Override
    public String fullName(String separator) {
        return "MiningNode" + separator + this.formatId();
    }
    
    /*____________________________________________________________________*/
}

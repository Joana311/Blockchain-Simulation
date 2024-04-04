package Node;

import Interfaces.IMessage;
import Wallet.*;
import Transaction.*;

public class MiningNode extends Node {

    private Integer quantity;

    public MiningNode(Wallet wallet, Integer quantity) {
        super(wallet);
        this.quantity = quantity;
    }

    /*________________________________*/
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public void broadcast(IMessage msg) {
        msg.process(this);
    }
    
    /*________________________________*/
    
    @Override
    public String fullName() {
        return "MiningNode#" + String.format("%03d", this.getId());
    }
}

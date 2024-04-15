package BlockchainComponents;

import Interfaces.IMessage;

public interface IConnectable {

    public void broadcast(IMessage msg);

    public IConnectable getParent();

    public default IConnectable getTopParent() {
        IConnectable parent = getParent();
        while (parent != null) {
            if (parent.getParent() == null) {
                return parent;
            }
            parent = parent.getParent();
        }
        return parent;
    }
}

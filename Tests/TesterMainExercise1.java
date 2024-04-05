package Tests;

import BlockchainComponents.BlockchainNetwork;
import CommonUtils.*;

import Wallet.*;
import Transaction.*;
import BlockchainComponents.*;

public class TesterMainExercise1 {

    protected Wallet wallet1, wallet2, wallet3;
    protected MiningNode miningNode, miningNode2;
    protected Node node;
    protected Subnet subnet;
    protected BlockchainNetwork network;

    public void buildNetwork() {
        //Create the wallets
        this.wallet1 = new Wallet("Bob", CommonUtils.sha1("PK-Bob"), 10);
        this.wallet2 = new Wallet("Alice", CommonUtils.sha1("PK-Alice"), 100);
        this.wallet3 = new Wallet("Paul", CommonUtils.sha1("PK-Pauk"), 777);

        //Create the nodes with the wallets
        node = new Node(wallet1);
        miningNode = new MiningNode(wallet2, 10000);

        //Create a subnet inside a network
        miningNode2 = new MiningNode(wallet3, 10000);
        subnet = new Subnet(miningNode2); // we could pass more nodes here

        //Create the network and connect the elements
        this.network = new BlockchainNetwork("ADSOF blockchain");

        network.connect(node)
                .connect(subnet)
                .connect(miningNode);

        //create example transaction, which transfers 10 coins from wallet1 to wallet2
        new Transaction(wallet1, wallet2, 10);
    }

    public static void main(String[] args) {
        TesterMainExercise1 tme = new TesterMainExercise1();
        tme.buildNetwork();
        System.out.println(tme.network);
        System.out.println(tme.node.fullName()); // prints the name of the node
        System.out.println("End of party!");
    }
}

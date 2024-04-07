package MiningMethods;

import Block.Block;
import CommonUtils.BlockConfig;
import CommonUtils.CommonUtils;
import Interfaces.*;
import Transaction.Transaction;

public class SimpleMining implements IMiningMethod {
    public SimpleMining() {
    }
    
    /*____________________________________________________________________*/
    
    /*
        Version del bloque
        Hash del bloque anterior o GENESIS_BLOCK
        Timestamp
        Dificultad
        Nonce
    */
    @Override
    public String createHash(Block block) {
        String buffer;
        
        buffer = block.getVersion().toString() + 
                (block.getPrevious() != null ? block.getPrevious().getHash() : BlockConfig.GENESIS_BLOCK) + 
                block.getTimestamp() +
                block.getDifficulty() +
                block.getNonce();
        
        return CommonUtils.sha256(buffer);
    }
    
    /* TODO */
    @Override
    public Block mineBlock(Transaction transaction, Block previousConfirmedBlock, String minerKey) {
        return null;
    }
    
    /*____________________________________________________________________*/
    
}

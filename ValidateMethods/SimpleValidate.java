package ValidateMethods;

import Interfaces.*;
import Block.*;

public class SimpleValidate implements IValidateMethod {
    public SimpleValidate() {
    }
    
    @Override
    public boolean validate(IMiningMethod miningMethod, Block block) {
        String hash = miningMethod.createHash(block);
        return hash.equals(block.getHash());
    }
}

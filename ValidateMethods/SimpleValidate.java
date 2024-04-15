package ValidateMethods;

import MiningMethods.IMiningMethod;
import Interfaces.*;
import Block.*;

public class SimpleValidate implements IValidateMethod {

    public SimpleValidate() {
    }

    @Override
    public boolean validate(IMiningMethod miningMethod, Block block) {
        String newHash = miningMethod.createHash(block);
        return newHash.equals(block.getHash());
    }
}

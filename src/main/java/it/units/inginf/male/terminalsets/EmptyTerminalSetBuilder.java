package it.units.inginf.male.terminalsets;

import it.units.inginf.male.configuration.Configuration;
import it.units.inginf.male.inputs.Context;

/**
 * This terminal set do nothing. New TerminalSet nodes are not added to the NodeFactory.   
 * @author nvhuy9527
 */
public class EmptyTerminalSetBuilder implements TerminalSetBuilder{

    @Override
    public void setup(Configuration configuration) {
        //Nothing to do
    }

    @Override
    public void setup(Context context) {
        //Nothing to do
    }
    
}

package it.units.inginf.male.terminalsets;

import it.units.inginf.male.configuration.Configuration;
import it.units.inginf.male.inputs.Context;

/**
 * The implementations update the NodeFactory object by adding new nodes.  
 * Initialization is usually based on the dataset examples but this is implementation dependant.
 * Best practice: call the <code>setup</code> method after the datasetContainer <code>setup</code> nut first than the populationBuilder.
 * populationBuilder needs a working NodeFacotry. 
 * @author nvhuy9527
 */
public interface TerminalSetBuilder {   
    void setup(Configuration configuration);
    void setup(Context context);
}

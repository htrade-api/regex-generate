package it.units.inginf.male.generations;

import it.units.inginf.male.configuration.Configuration;
import it.units.inginf.male.inputs.Context;
import it.units.inginf.male.tree.Node;
import java.util.List;

/**
 *
 * @author nvhuy9527
 */
public interface InitialPopulationBuilder {

    /**
     * Returns a copy of the shared population list
     * @return
     */
    public List<Node> init();
    
    
    /**
     * Returns a copy of the shared population list
     * @param context
     * @return
     */
    public List<Node> init(Context context);

    /**
     * Updates the shared population object (into main configuration) 
     * @param configuration
     */
    public void setup(Configuration configuration);
}

package it.units.inginf.male.selections.best;

import it.units.inginf.male.outputs.Results;
import java.util.Map;

/**
 * Its implementations describe the final post processing individual selection. The best individual selection is part of the 
 * solution search algorithm. The implementations may access the results structure and look at the Jobs outcome in order to find out the best solution,
 * --i.e.: performance on validation.
 * @author nvhuy9527
 */
public interface BestSelector {
    public void setup(Map<String,String> parameters);
    public void elaborate(Results results);
}

package it.units.inginf.male.postprocessing;

import it.units.inginf.male.configuration.Configuration;
import it.units.inginf.male.outputs.Results;
import java.util.Map;

/**
 * Classes which implements this interface have to call the BestSelector in order to
 * select the best solution. Populate the Results instance file with requested stats and save
 * the results and/or persist results on file (for example).
 * @author nvhuy9527
 */
public interface Postprocessor {

    public void setup(Map<String,String> parameters);

    public void elaborate(Configuration config, Results results, long timeTaken);
}

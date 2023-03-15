package it.units.inginf.male.postprocessing;

import it.units.inginf.male.configuration.Configuration;
import it.units.inginf.male.outputs.Results;
import java.util.Map;

/**
 * Stub implementation
 * @author nvhuy9527
 */
public class TrivialPostProcessor implements Postprocessor{

    @Override
    public void elaborate(Configuration config, Results results, long timeTaken) {
        System.out.println("Postprocessing...");
    }

    @Override
    public void setup(Map<String, String> parameters) {
        System.out.println(parameters);
    }

}

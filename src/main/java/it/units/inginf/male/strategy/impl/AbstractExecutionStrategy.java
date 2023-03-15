package it.units.inginf.male.strategy.impl;

import it.units.inginf.male.strategy.ExecutionStrategy;
import it.units.inginf.male.strategy.RunStrategy;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author nvhuy9527
 */
public abstract class AbstractExecutionStrategy implements ExecutionStrategy{

    public static final String RUN_STRATEGY_KEY = "runStrategy";
    

    private static final Logger LOG = Logger.getLogger(AbstractExecutionStrategy.class.getName());

    protected Class<? extends RunStrategy> getStrategy(Map<String, String> parameters) {
        String paramValue = parameters.get(RUN_STRATEGY_KEY);
        Class<? extends RunStrategy> strategyClass;
        try{
            strategyClass = Class.forName(paramValue).asSubclass(RunStrategy.class);
        }catch(Exception x){
            LOG.warning("Falling back to default RunStrategy");
            strategyClass = DefaultStrategy.class;
        }
        return strategyClass;
    }

}

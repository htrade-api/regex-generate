package it.units.inginf.male.strategy;

import it.units.inginf.male.configuration.Configuration;
import it.units.inginf.male.evaluators.TreeEvaluationException;
import it.units.inginf.male.inputs.Context;
import java.util.concurrent.Callable;

/**
 *
 * @author nvhuy9527
 */
public interface RunStrategy extends Callable<Void>{

    void setup(Configuration configuration, ExecutionListener executionListener) throws TreeEvaluationException;
    Configuration getConfiguration();
    ExecutionListener getExecutionListener();
    Context getContext();
}

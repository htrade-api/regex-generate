package it.units.inginf.male.strategy;

import it.units.inginf.male.configuration.Configuration;

/**
 *
 * @author nvhuy9527
 */
public interface ExecutionStrategy {
    public void execute(Configuration configuration, ExecutionListenerFactory listenerFactory) throws Exception;

    public void shutdown();
}

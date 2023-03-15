package it.units.inginf.male.strategy;

/**
 *
 * @author nvhuy9527
 */
public interface ExecutionListenerFactory {

    void register(ExecutionStrategy strategy);

    ExecutionListener getNewListener();

}

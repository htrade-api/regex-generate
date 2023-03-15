package it.units.inginf.male.strategy.impl;

import it.units.inginf.male.configuration.Configuration;
import it.units.inginf.male.strategy.ExecutionListenerFactory;
import it.units.inginf.male.strategy.RunStrategy;
import it.units.inginf.male.evaluators.TreeEvaluationException;
import java.util.Map;

/**
 *
 * @author nvhuy9527
 */
public class SinglethreadStrategy extends AbstractExecutionStrategy {

    private Thread workThread;
    private boolean done = false;

    @Override
    public void execute(Configuration configuration, ExecutionListenerFactory listenerFactory) throws Exception {
        listenerFactory.register(this);
        workThread = Thread.currentThread();
        Map<String, String> parameters = configuration.getStrategyParameters();
        Class<? extends RunStrategy> strategyClass = getStrategy(parameters);
        long initialSeed = configuration.getInitialSeed();
        for (int i = 0; i < configuration.getJobs() && !done; i++) {
            RunStrategy job = strategyClass.newInstance();
            Configuration jobConf = new Configuration(configuration);
            jobConf.setJobId(i);
            jobConf.setInitialSeed(initialSeed + i);
            job.setup(jobConf, listenerFactory.getNewListener());
            try {
                job.call();
            } catch (TreeEvaluationException ev) {
                job.getExecutionListener().evolutionFailed(job, ev);
            }
        }
    }

    @Override
    public void shutdown() {
        done=true;
        workThread.interrupt();
    }
}

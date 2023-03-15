package it.units.inginf.male.strategy.impl;

/**
 * This class is the same as DefaultStrategy but changes the defaults for the termination criteria. With CEIndependentStategy the steady-best
 * termination criteria is enabled by default. With termination criteria enabled a Job should terminate first when there are no changes in the best
 * individual for the specified amount of generations. The default number of generations without changes which triggers the job interruption is 200.
 * You can change the default values or configurations by using the configuration strategyParametners in configuration class/file, they are the same
 * as DefaultStrategy, let's refer to DefaultStrategy documentation.
 * @author nvhuy9527
 */
public class CESteadyBestStrategy extends  DefaultStrategy{

    
    public CESteadyBestStrategy() {
        this.terminationCriteria = true;
        this.terminationCriteriaGenerations = 200;
    }
  
}

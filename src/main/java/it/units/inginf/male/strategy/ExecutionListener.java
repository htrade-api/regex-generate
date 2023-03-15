package it.units.inginf.male.strategy;

import it.units.inginf.male.objective.Ranking;
import it.units.inginf.male.tree.Node;
import it.units.inginf.male.evaluators.TreeEvaluationException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author nvhuy9527
 */
public interface ExecutionListener extends Serializable{

    void evolutionStarted(RunStrategy strategy);

    void logGeneration(RunStrategy strategy, int generation, Node best, double[] fitness, List<Ranking> population);

    void evolutionComplete(RunStrategy strategy, int generation, List<Ranking> population);

    void evolutionFailed(RunStrategy strategy, TreeEvaluationException cause);
    
    void evolutionStopped();
}

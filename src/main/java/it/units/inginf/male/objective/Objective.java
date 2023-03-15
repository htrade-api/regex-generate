package it.units.inginf.male.objective;

import it.units.inginf.male.tree.Node;
import it.units.inginf.male.evaluators.TreeEvaluator;
import it.units.inginf.male.inputs.Context;

/**
 *
 * @author nvhuy9527
 */
public interface Objective {

    public void setup(Context context);
    public double[] fitness(Node individual);
    TreeEvaluator getTreeEvaluator();
    Objective cloneObjective();
}

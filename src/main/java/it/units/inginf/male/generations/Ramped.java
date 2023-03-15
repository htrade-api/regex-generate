package it.units.inginf.male.generations;

import it.units.inginf.male.inputs.Context;
import it.units.inginf.male.tree.Node;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nvhuy9527
 */
public class Ramped implements Generation {

    Generation full;
    Generation grow;
    Context context;

    public Ramped(int maxDepth, Context context ) {
        this.full = new Full(maxDepth,context);
        this.grow = new Growth(maxDepth,context);
        this.context=context;
    }

     /**
     * This method returns a new population of the desired size. An half of the
     * population is generated through Growth algorithm, the other half by the
     * Full method
     * @param popSize the desired population size
     * @return a List of Node of size popSize
     */
    @Override
    public List<Node> generate(int popSize) {
        List<Node> population = new ArrayList<>();

        int popSizeGrow = (int)popSize/2;
        int popSizeFull = popSize-popSizeGrow;

        population.addAll(this.full.generate(popSizeGrow));
        population.addAll(this.grow.generate(popSizeFull));

        return population;
    }
}

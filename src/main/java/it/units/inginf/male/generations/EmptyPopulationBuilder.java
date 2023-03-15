package it.units.inginf.male.generations;

import it.units.inginf.male.configuration.Configuration;
import it.units.inginf.male.inputs.Context;
import it.units.inginf.male.tree.Node;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author nvhuy9527
 */
public class EmptyPopulationBuilder implements InitialPopulationBuilder {

    int size;

    @Override
    public List<Node> init() {
        return new ArrayList<>(size);
    }

    @Override
    public void setup(Configuration configuration) {
        size = configuration.getEvolutionParameters().getPopulationSize();
    }

    @Override
    public List<Node> init(Context context) {
        return new LinkedList<>();
    }
}

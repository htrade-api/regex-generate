package it.units.inginf.male.generations;

import it.units.inginf.male.tree.Node;
import java.util.List;

/**
 *
 * @author nvhuy9527
 */
public interface Generation {

    public List<Node> generate(int popSize);
}

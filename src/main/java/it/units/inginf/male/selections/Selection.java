package it.units.inginf.male.selections;

import it.units.inginf.male.objective.Ranking;
import it.units.inginf.male.tree.Node;

import java.util.List;

/**
 *
 * @author nvhuy9527
 */
public interface Selection {

    public Node select(List<Ranking> population);
}

package it.units.inginf.male.selections;

import it.units.inginf.male.inputs.Context;
import it.units.inginf.male.objective.Ranking;
import it.units.inginf.male.tree.Node;
import java.util.List;

/**
 *
 * @author nvhuy9527
 */
public class Tournament implements Selection {

    private final Context context;

    public Tournament(Context context) {
        this.context = context;
    }

    @Override
    public Node select(List<Ranking> population) {

        return tournament(population);

    }

    private Node tournament(List<Ranking> population) {

        int best = population.size();        
        for (int t = 0; t < 7; t++) {
            int index = context.getRandom().nextInt(population.size());
            best = Math.min(best, index);
        }

        return population.get(best).getTree();

    }
}

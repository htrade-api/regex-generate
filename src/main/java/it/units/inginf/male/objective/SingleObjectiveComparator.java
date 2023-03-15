package it.units.inginf.male.objective;

import java.util.Comparator;

/**
 *
 * @author nvhuy9527
 */
public class SingleObjectiveComparator implements Comparator<Ranking> {

    private int objective;

    public SingleObjectiveComparator(int objective) {
        this.objective = objective;
    }        
    
    @Override
    public int compare(Ranking o1, Ranking o2) {
        return Double.compare(o1.getFitness()[objective], o2.getFitness()[objective]);
    }
    
}

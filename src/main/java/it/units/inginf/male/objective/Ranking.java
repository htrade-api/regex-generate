package it.units.inginf.male.objective;

import it.units.inginf.male.tree.Node;
import it.units.inginf.male.utils.Utils;

/**
 *
 * @author nvhuy9527
 */
public class Ranking {

    private Node tree;
    private double[] fitness;

    public Ranking(Node tree, double[] fitness) {
        this.tree = tree;
        this.fitness = fitness;
        
    }

    public double[] getFitness() {
        return fitness;
    }


    public Node getTree() {
        return tree;
    }    
    
    public String getDescription(){
        StringBuilder sb = new StringBuilder();
        this.tree.describe(sb);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ranking other = (Ranking) obj;
        if (this.tree != other.tree && (this.tree == null || !this.tree.equals(other.tree))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.tree != null ? this.tree.hashCode() : 0);
        return hash;
    }       
    
}

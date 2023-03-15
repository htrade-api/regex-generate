package it.units.inginf.male.tree.operator;

import it.units.inginf.male.tree.Anchor;
import it.units.inginf.male.tree.Node;

/**
 *
 * @author nvhuy9527
 */
public abstract class Quantifier extends UnaryOperator{

    @Override
    public boolean isValid(){
        Node child = getChildrens().get(0);
        return child.isValid() && !(child instanceof Quantifier || child instanceof MatchMinMax || child instanceof MatchMinMaxGreedy || child instanceof Anchor || child instanceof Lookaround);
    }

}

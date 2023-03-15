package it.units.inginf.male.tree.operator;

import it.units.inginf.male.tree.Anchor;
import it.units.inginf.male.tree.Node;
import it.units.inginf.male.tree.RegexRange;

/**
 *
 * @author nvhuy9527
 */
public abstract class Lookaround extends UnaryOperator {

    @Override
    public boolean isValid() {
        Node child = getChildrens().get(0);
        return child.isValid() && !(child instanceof RegexRange || child instanceof Anchor || child instanceof Backreference);
    }
    private int numberQuantifier = 0;
    private boolean hasOnlyMinMax = true;

    protected void checkQuantifiers(Node root) {
        if (root instanceof Quantifier) {
            hasOnlyMinMax = false;
            numberQuantifier++;
        } else if (root instanceof MatchMinMax || root instanceof MatchMinMaxGreedy) {
            numberQuantifier++;
        }

        for (Node child : root.getChildrens()) {
            checkQuantifiers(child);
        }

    }

    protected boolean isLookbehindValid() {
        checkQuantifiers(this);
        return hasOnlyMinMax || (numberQuantifier < 1);
    }
}

package it.units.inginf.male.tree.operator;

import it.units.inginf.male.tree.Constant;
import it.units.inginf.male.tree.DescriptionContext;
import it.units.inginf.male.tree.Node;

/**
 *
 * @author nvhuy9527
 */
public class MatchMinMaxGreedy extends TernaryOperator {
    static int MAX_N_GENERATION = 20;
    @Override
    protected TernaryOperator buildCopy() {
        return new MatchMinMaxGreedy();
    }

    @Override
    public void describe(StringBuilder builder, DescriptionContext context, RegexFlavour flavour) {
        getFirst().describe(builder, context, flavour);
        builder.append("{");
        builder.append(Integer.parseInt(getSecond().toString()));
        builder.append(",");
        builder.append(Integer.parseInt(getThird().toString()));
        builder.append("}");
    }

    @Override
    public boolean isValid() {
        Node first = getFirst();
        boolean validFirst = first.isValid() && !(first instanceof Concatenator || first instanceof Quantifier || first instanceof MatchMinMax || first instanceof MatchMinMaxGreedy || first instanceof Lookaround);
        
        Node second = getSecond();
        Node third = getThird();

        if (third instanceof Constant && second instanceof Constant) {
            int leftValue;
            int rightValue;
            try {
                leftValue = Integer.parseInt(second.toString());
                rightValue = Integer.parseInt(third.toString());
            } catch (NumberFormatException ex) {
                return false;
            }
            if (leftValue < 0 || rightValue < 0) {
                return false;
            }
            if(leftValue>=rightValue)
                return false;
            return validFirst;
        }


        return false;
    }
}

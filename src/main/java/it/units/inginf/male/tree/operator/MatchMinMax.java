package it.units.inginf.male.tree.operator;

import it.units.inginf.male.tree.Anchor;
import it.units.inginf.male.tree.Constant;
import it.units.inginf.male.tree.DescriptionContext;
import it.units.inginf.male.tree.Node;
import java.util.Random;

/**
 *
 * @author nvhuy9527
 */
public class MatchMinMax extends TernaryOperator {

    static final int MAX_N_GENERATION = 20;

    @Override
    protected TernaryOperator buildCopy() {
        return new MatchMinMax();
    }

    @Override
    public void describe(StringBuilder builder, DescriptionContext context, RegexFlavour flavour) {
        StringBuilder tmp = new StringBuilder();
        Node child = getFirst();
        // counts the group immediatly
        int index = context.incGroups();
        child.describe(tmp, context, flavour);
        int l = child.isEscaped() ? tmp.length() - 1 : tmp.length();
        boolean group = l > 1 && !child.isCharacterClass() && !(child instanceof Group) && !(child instanceof NonCapturingGroup);

        switch (flavour) {
            case JAVA:
                if (group) {
                    builder.append("(?:");
                    builder.append(tmp);
                    builder.append(")");
                } else {
                    builder.append(tmp);
                }

                builder.append("{");
                builder.append(Integer.parseInt(getSecond().toString()));
                builder.append(",");
                builder.append(Integer.parseInt(getThird().toString()));
                builder.append("}+");
                break;
            default:
                builder.append("(?=(");                
                if (group) {
                    builder.append("(?:");
                    builder.append(tmp);
                    builder.append(")");
                } else {
                    builder.append(tmp);
                }
                builder.append("{");
                builder.append(Integer.parseInt(getSecond().toString()));
                builder.append(",");
                builder.append(Integer.parseInt(getThird().toString()));
                builder.append("}))\\").append(index);
                context.incExpansionGroups();
        }

    }

    @Override
    public boolean isValid() {
        Node first = getFirst();
        boolean validFirst = first.isValid() && !(first instanceof Concatenator || first instanceof Quantifier || first instanceof MatchMinMax || first instanceof MatchMinMaxGreedy || first instanceof Anchor || first instanceof Lookaround);

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
            if (leftValue >= rightValue) {
                return false;
            }
            return validFirst;
        }


        return false;
    }
}

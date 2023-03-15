package it.units.inginf.male.tree.operator;

import it.units.inginf.male.tree.DescriptionContext;

/**
 *
 * @author nvhuy9527
 */
public class Or extends BinaryOperator {

    @Override
    protected BinaryOperator buildCopy() {
        return new Or();
    }

    @Override
    public void describe(StringBuilder builder, DescriptionContext context, RegexFlavour flavour) {
        if (getParent() instanceof Quantifier) {
            builder.append("(?:");
        }
        getLeft().describe(builder, context, flavour);
        builder.append("|");
        getRight().describe(builder, context, flavour);
        if (getParent() instanceof Quantifier) {
            builder.append(")");
        }
    }

    @Override
    public boolean isValid() {
        if (getLeft() instanceof Quantifier || getRight() instanceof Quantifier) {
            return false;
        }
        return getLeft().isValid() && getRight().isValid();
    }
}

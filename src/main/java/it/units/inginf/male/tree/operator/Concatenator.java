package it.units.inginf.male.tree.operator;

import it.units.inginf.male.tree.DescriptionContext;

/**
 *
 * @author nvhuy9527
 */
public class Concatenator extends BinaryOperator {

    @Override
    protected BinaryOperator buildCopy() {
        return new Concatenator();
    }

    @Override
    public void describe(StringBuilder builder, DescriptionContext context, RegexFlavour flavour) {
        getLeft().describe(builder, context, flavour);
        getRight().describe(builder, context, flavour);
    }

    @Override
    public boolean isValid() {
        if(getLeft() instanceof Or || getRight() instanceof Or)
            return false;
        return getLeft().isValid() && getRight().isValid();
    }

}

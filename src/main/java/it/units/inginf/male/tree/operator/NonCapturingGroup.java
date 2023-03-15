package it.units.inginf.male.tree.operator;

import it.units.inginf.male.tree.DescriptionContext;

/**
 *
 * @author nvhuy9527
 */
public class NonCapturingGroup extends UnaryOperator {

    @Override
    protected UnaryOperator buildCopy() {
        return new NonCapturingGroup();
    }

    @Override
    public void describe(StringBuilder builder, DescriptionContext context, RegexFlavour flavour) {
        builder.append("(?:");
        getChildrens().get(0).describe(builder, context, flavour);
        builder.append(")");
    }

    @Override
    public boolean isValid() {
        return getChildrens().get(0).isValid();
    }

}

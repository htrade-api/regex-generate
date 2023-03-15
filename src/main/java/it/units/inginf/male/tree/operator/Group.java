package it.units.inginf.male.tree.operator;

import it.units.inginf.male.tree.DescriptionContext;

/**
 *
 * @author nvhuy9527
 */
public class Group extends UnaryOperator{

    @Override
    protected UnaryOperator buildCopy() {
        return new Group();
    }

    @Override
    public void describe(StringBuilder builder, DescriptionContext context, RegexFlavour flavour) {
        builder.append("(");
        context.incGroups();
        getChildrens().get(0).describe(builder, context, flavour);
        builder.append(")");
    }
    
    @Override
    public boolean isValid() {
        return getChildrens().get(0).isValid();
    }
}

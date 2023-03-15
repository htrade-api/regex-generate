package it.units.inginf.male.tree.operator;

import it.units.inginf.male.tree.DescriptionContext;

/**
 *
 * @author nvhuy9527
 */
public class NegativeLookahead extends Lookaround{

    @Override
    protected UnaryOperator buildCopy() {
        return new NegativeLookahead();
    }

    @Override
    public void describe(StringBuilder builder, DescriptionContext context, RegexFlavour flavour) {
        builder.append("(?!");
        getChildrens().get(0).describe(builder, context, flavour);
        builder.append(")");
    }
    
}

package it.units.inginf.male.tree.operator;

import it.units.inginf.male.tree.DescriptionContext;

/**
 *
 * @author nvhuy9527
 */
public class MatchZeroOrOneGreedy extends Quantifier {

    @Override
    public void describe(StringBuilder builder, DescriptionContext context, RegexFlavour flavour) {
        getChildrens().get(0).describe(builder, context, flavour);
        builder.append("?");
    }

    @Override
    protected UnaryOperator buildCopy() {
        return new MatchZeroOrOneGreedy();
    }
    
}

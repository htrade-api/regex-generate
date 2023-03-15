package it.units.inginf.male.tree.operator;

import it.units.inginf.male.tree.DescriptionContext;

/**
 *
 * @author nvhuy9527
 */
public class PositiveLookbehind extends Lookaround {

    @Override
    protected UnaryOperator buildCopy() {
        return new PositiveLookbehind();
    }

    @Override
    public boolean isValid() {
        boolean valid = super.isValid();
        if (!valid) {
            return valid;
        }                
        
        return isLookbehindValid();
    }

    @Override
    public void describe(StringBuilder builder, DescriptionContext context, RegexFlavour flavour) {
        builder.append("(?<=");
        getChildrens().get(0).describe(builder, context, flavour);
        builder.append(")");
    }
}

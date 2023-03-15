package it.units.inginf.male.tree.operator;

import it.units.inginf.male.tree.Constant;
import it.units.inginf.male.tree.DescriptionContext;
import it.units.inginf.male.tree.Node;
import it.units.inginf.male.tree.RegexRange;

/**
 *
 * @author nvhuy9527
 */
public class ListMatch extends UnaryOperator {

    @Override
    protected UnaryOperator buildCopy() {
        return new ListMatch();
    }

    @Override
    public void describe(StringBuilder builder, DescriptionContext context, RegexFlavour flavour) {
        Node child = getChildrens().get(0);
        builder.append("[");
        child.describe(builder, context, flavour);
        builder.append("]");
    }
    
    @Override
    public boolean isValid() {
        return checkValid(getChildrens().get(0));
    }

    private boolean checkValid(Node root){

        if(!(root instanceof Constant || root instanceof RegexRange || root instanceof Concatenator)){
            return false;
        }

        for(Node child:root.getChildrens()){
            if(!checkValid(child)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isCharacterClass() {
        return true;
    }
     
}

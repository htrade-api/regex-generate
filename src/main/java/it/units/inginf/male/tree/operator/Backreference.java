package it.units.inginf.male.tree.operator;

import it.units.inginf.male.tree.AbstractNode;
import it.units.inginf.male.tree.DescriptionContext;
import it.units.inginf.male.tree.Leaf;
import it.units.inginf.male.tree.Node;
import it.units.inginf.male.tree.operator.UnaryOperator;

/**
 *
 * @author nvhuy9527
 */
public class Backreference extends AbstractNode implements Leaf {

    private Node parent;
    private final int value;

    public Backreference(int value){
        this.value = value;        
    }
    
    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public int getMinChildrenCount() {
        return 0;
    }

    @Override
    public int getMaxChildrenCount() {
        return 0;
    }

    @Override
    public void describe(StringBuilder builder, DescriptionContext context, RegexFlavour flavour) {
        builder.append("\\");
        switch (flavour) {
            case JAVA:
                builder.append(value);
                break;
            default:
                builder.append(value + context.getExpansionGroups());
        }
    }

    @Override
    public boolean isValid() {        
        return true;
    }

    @Override
    public Leaf cloneTree() {
        return new Backreference(value);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.value;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Backreference other = (Backreference) obj;
        if (this.value != other.value) {
            return false;
        }
        return true;
    }
    
}

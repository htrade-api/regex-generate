package it.units.inginf.male.tree.operator;


import it.units.inginf.male.tree.AbstractNode;
import it.units.inginf.male.tree.Node;


/**
 *
 * @author nvhuy9527
 */
public abstract class UnaryOperator extends AbstractNode {

    private Node parent;

    @Override
    public int getMinChildrenCount() {
        return 1;
    }

    @Override
    public int getMaxChildrenCount() {
        return 1;
    }       

    @Override
    public Node cloneTree() {
        UnaryOperator clone = buildCopy();
        if (!getChildrens().isEmpty()) {
            Node child = getChildrens().get(0).cloneTree();
            child.setParent(clone);
            clone.getChildrens().add(child);
        }
        return clone;
    }

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }
   
    protected abstract UnaryOperator buildCopy();  

}

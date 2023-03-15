package it.units.inginf.male.tree.operator;

import it.units.inginf.male.tree.AbstractNode;
import it.units.inginf.male.tree.Node;
import java.util.List;

/**
 *
 * @author nvhuy9527
 */
public abstract class BinaryOperator extends AbstractNode {

    private Node parent;

    @Override
    public int getMinChildrenCount() {
        return 2;
    }

    @Override
    public int getMaxChildrenCount() {
        return 2;
    }

    public Node getLeft() {
        return getChildrens().get(0);
    }

    public Node getRight() {
        return getChildrens().get(1);
    }    

    @Override
    public Node cloneTree() {
        BinaryOperator bop = buildCopy();
        List<Node> bopChilds = bop.getChildrens();
        for(Node child:this.getChildrens()){
            Node newChild = child.cloneTree();
            newChild.setParent(bop);
            bopChilds.add(newChild);
        }
        return bop;
    }

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    protected abstract  BinaryOperator buildCopy();

}

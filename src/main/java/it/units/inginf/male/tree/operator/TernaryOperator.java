package it.units.inginf.male.tree.operator;

import it.units.inginf.male.tree.AbstractNode;
import it.units.inginf.male.tree.Node;
import java.util.List;

/**
 *
 * @author nvhuy9527
 */
public abstract class TernaryOperator extends AbstractNode{
    private Node parent;

    @Override
    public Node cloneTree() {
        TernaryOperator top = buildCopy();
        List<Node> topChilds = top.getChildrens();
        for(Node child:this.getChildrens()){
            Node newChild = child.cloneTree();
            newChild.setParent(top);
            topChilds.add(newChild);
        }
        return top;
    }

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public void setParent(Node parent) {
        this.parent=parent;
    }

    @Override
    public int getMinChildrenCount() {
        return 3;
    }

    @Override
    public int getMaxChildrenCount() {
        return 3;
    }
    
    public Node getFirst() {
        return getChildrens().get(0);
    }

    public Node getSecond() {
        return getChildrens().get(1);
    } 
    
    public Node getThird() {
        return getChildrens().get(2);
    }
    
    protected abstract  TernaryOperator buildCopy();
    
}

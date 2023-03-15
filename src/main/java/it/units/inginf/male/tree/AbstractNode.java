package it.units.inginf.male.tree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nvhuy9527
 */
public abstract class AbstractNode implements Node {

    private List<Node> childrens;
    private long id;

    @Override
    public long getId() {
        return id;
    }

    public AbstractNode() {
        id = IDFactory.getInstance().nextID();
        childrens = new ArrayList<Node>(getMaxChildrenCount());
    }

    @Override
    public List<Node> getChildrens() {
        return childrens;
    }

    @Override
    public void describe(StringBuilder builder) {
        describe(builder, new DescriptionContext(), RegexFlavour.JAVA);
    }
    
    @Override
    public boolean isCharacterClass(){
        return false;
    }
    
    @Override
    public boolean isEscaped(){
        return false;
    }
}

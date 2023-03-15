package it.units.inginf.male.tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author nvhuy9527
 */
public class Anchor extends AbstractNode implements Leaf{
    private Node parent;
    protected String value;
    Set<String> allowedClasses = new HashSet<String>(Arrays.asList("\\w", "\\d", ".", "\\b", "\\s"));

    public Anchor(String value) {
        this.value = value;
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
        builder.append(value);
    }

    @Override
    public Leaf cloneTree() {
        Anchor clone = new Anchor(value);
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

    @Override
    public List<Node> getChildrens() {
        return Collections.<Node>emptyList();
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.value);
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
        final Anchor other = (Anchor) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }
    
    
}

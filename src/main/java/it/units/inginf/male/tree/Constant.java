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
 
public class Constant extends AbstractNode implements Leaf {

    private Node parent;
    protected String value;
    private boolean charClass;
    Set<String> allowedClasses = new HashSet<String>(Arrays.asList("\\w", "\\d", ".", "\\b", "\\s"));
    private boolean escaped;

    public Constant(String value) {
        this.value = value;
        charClass = allowedClasses.contains(value);
        escaped = value.startsWith("\\");
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
        Constant clone = new Constant(value);
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
    public boolean isCharacterClass() {
        return charClass;
    }

    @Override
    public boolean isEscaped() {
        return escaped;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.value);
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
        final Constant other = (Constant) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }
    
    
}

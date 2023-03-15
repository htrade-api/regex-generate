package it.units.inginf.male.tree;

import java.util.List;

/**
 *
 * @author nvhuy9527
 */
public interface Node {
    
    Node cloneTree();

    Node getParent();
    void setParent(Node parent);
    
    int getMinChildrenCount();
    int getMaxChildrenCount();
    List<Node> getChildrens();
    long getId();
    
    void describe(StringBuilder builder);
    void describe(StringBuilder builder, DescriptionContext context, RegexFlavour flavour);
    boolean isValid();

    public enum RegexFlavour {
        JAVA,
        CSHARP,
        JS
    }
    public boolean isCharacterClass();
    public boolean isEscaped();
}

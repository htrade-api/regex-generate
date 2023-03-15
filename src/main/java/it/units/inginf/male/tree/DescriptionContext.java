package it.units.inginf.male.tree;

/**
 *
 * @author nvhuy9527
 */
public class DescriptionContext {
    int groups;
    int expansionGroups = 0;

    public int getGroups() {
        return groups;
    }

    public int incGroups(){
        groups++;
        return groups;
    }

    public int getExpansionGroups() {
        return expansionGroups;
    }

    public void incExpansionGroups() {
        this.expansionGroups++;
    }
    
}

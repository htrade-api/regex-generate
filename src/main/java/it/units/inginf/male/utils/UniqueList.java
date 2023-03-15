package it.units.inginf.male.utils;

import it.units.inginf.male.tree.Node;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author nvhuy9527
 */
public class UniqueList<E> extends ArrayList<Node> {

    private Set<String> hashes = new HashSet<>();

    public UniqueList(int initialCapacity) {
        super(initialCapacity);
    }        

    @Override
    public boolean add(Node e) {
        StringBuilder builder = new StringBuilder();
        e.describe(builder);
        String hash = builder.toString();
        if (hashes.contains(hash)) {
            return false;
        }
        hashes.add(hash);
        return super.add(e);
    }   

    @Override
    public boolean addAll(Collection<? extends Node> c) {
        boolean ret = false;
        for(Node n:c){
            ret = this.add(n) | ret;
        }
        return ret;
    }
    
    
    
}

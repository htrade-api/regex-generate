package it.units.inginf.male.tree;

import it.units.inginf.male.tree.operator.Concatenator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nvhuy9527
 */
public class NodeFactory {

    private List<Node> functionSet;
    private List<Leaf> terminalSet;

    public NodeFactory(NodeFactory factory){
        functionSet = new ArrayList<Node>(factory.getFunctionSet());
        terminalSet = new ArrayList<Leaf>(factory.getTerminalSet());
    }

    public NodeFactory() {
        functionSet = new ArrayList<Node>();
        terminalSet = new ArrayList<Leaf>();

        functionSet.add(new Concatenator());
        
    }

    public List<Node> getFunctionSet() {
        return functionSet;
    }

    public List<Leaf> getTerminalSet() {
        return terminalSet;
    }

    
}

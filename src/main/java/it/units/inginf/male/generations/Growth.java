package it.units.inginf.male.generations;

import it.units.inginf.male.inputs.Context;
import it.units.inginf.male.tree.Leaf;
import it.units.inginf.male.tree.Node;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nvhuy9527
 */
public class Growth implements Generation {

    int maxDepth;
    Context context;

    public Growth(int maxDepth, Context context) {
        this.maxDepth = maxDepth;
        this.context = context;
    }

     /**
     * This method return a new population of the desired size. The population
     * is generated by Growth algorithm which creates individual with a depth
     * from 1 to a specified max depth,
     * @param popSize the desired population size
     * @return a List of Node of size popSize
     */
    @Override
    public List<Node> generate(int popSize) {
        List<Node> population = new ArrayList<>();

        for (int i = 0; i < popSize;) {
            Node candidate = grow(1);
            if (candidate.isValid()) {
                population.add(candidate);
                i++;
            }
        }

        return population;
    }

    private Node grow(int depth) {
        Node tree = randomFunction();
        if (depth >= this.maxDepth - 1) {

            for (int i = tree.getMaxChildrenCount() - tree.getMinChildrenCount(); i < tree.getMaxChildrenCount(); i++) {
                Leaf leaf = randomLeaf();
                leaf.setParent(tree);
                tree.getChildrens().add(leaf);
            }

        } else {
            for (int i = tree.getMaxChildrenCount() - tree.getMinChildrenCount(); i < tree.getMaxChildrenCount(); i++) {
                if (context.getRandom().nextBoolean()) {
                    Node node = grow(depth + 1);
                    node.setParent(tree);
                    tree.getChildrens().add(node);
                } else {
                    Leaf leaf = randomLeaf();
                    leaf.setParent(tree);
                    tree.getChildrens().add(leaf);
                }
            }
        }
        return tree;
    }

    private Node randomFunction() {

        List<Node> functionSet = context.getConfiguration().getNodeFactory().getFunctionSet();
        return functionSet.get(context.getRandom().nextInt(functionSet.size())).cloneTree();
    }

    private Leaf randomLeaf() {
        List<Leaf> terminalSet = context.getConfiguration().getNodeFactory().getTerminalSet();
        return terminalSet.get(context.getRandom().nextInt(terminalSet.size())).cloneTree();
    }
}

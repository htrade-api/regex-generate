package it.units.inginf.male.evaluators;

import it.units.inginf.male.inputs.Context;
import it.units.inginf.male.inputs.DataSet.Bounds;
import it.units.inginf.male.tree.Node;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nvhuy9527
 */
public interface TreeEvaluator {

    public void setup(Map<String,String> parameters);
    List<List<Bounds>> evaluate(Node root, Context context) throws TreeEvaluationException;
    
}

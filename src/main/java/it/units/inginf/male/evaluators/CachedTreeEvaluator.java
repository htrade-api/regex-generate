package it.units.inginf.male.evaluators;

import it.units.inginf.male.inputs.Context;
import it.units.inginf.male.inputs.Context.EvaluationPhases;
import it.units.inginf.male.inputs.DataSet.Bounds;
import it.units.inginf.male.tree.Node;
import it.units.inginf.male.utils.Triplet;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 *
 * @author nvhuy9527
 */
public class CachedTreeEvaluator extends DefaultTreeEvaluator implements CachedEvaluator{

    private final WeakHashMap<Triplet<EvaluationPhases, Boolean, String>, List<List<Bounds>>> cache = new WeakHashMap<>();
    private long hit = 0;
    private long miss = 0;

    @Override
    public List<List<Bounds>> evaluate(Node root, Context context) throws TreeEvaluationException {

        StringBuilder sb = new StringBuilder();
        root.describe(sb);
        List<List<Bounds>> results;
        Triplet<EvaluationPhases, Boolean, String> key = new Triplet<>(context.getPhase(), context.isStripedPhase(), sb.toString());
        synchronized (cache) {
            results = cache.get(key);
        }
        if (results != null) {
            hit++;
            return results;
        }
        
        miss++;
        results = super.evaluate(root, context);
        
        synchronized (cache) {
            cache.put(key, results);
        }
        return results;
    }

    @Override
    public double getRatio(){
        return (double)this.hit/(this.hit+this.miss);
    }
    
    @Override
    public long getCacheSizeBytes(){
        synchronized (cache) {
            long cacheSize = 0;
            for (Map.Entry<Triplet<EvaluationPhases, Boolean, String>, List<List<Bounds>>> entry : cache.entrySet()) {
                List<List<Bounds>> list = entry.getValue();
                for (List<Bounds> exampleResult : list) {
                    cacheSize+=exampleResult.size();
                }            
            }
            cacheSize*=(Integer.SIZE/4);
            return cacheSize;
        }
    }
}

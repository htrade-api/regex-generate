package it.units.inginf.male.evaluators;

/**
 *
 * @author nvhuy9527
 */
public interface CachedEvaluator extends TreeEvaluator{
    public double getRatio();
    public long getCacheSizeBytes();
}

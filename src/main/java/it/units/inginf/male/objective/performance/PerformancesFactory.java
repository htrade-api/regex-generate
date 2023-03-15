package it.units.inginf.male.objective.performance;

import it.units.inginf.male.configuration.Configuration;
import it.units.inginf.male.inputs.Context;
import it.units.inginf.male.objective.Objective;

/**
 * Creates performance objectives with the requested configurations.
 * @author nvhuy9527
 */
public class PerformancesFactory {
    
    public static Objective buildObjective(Context.EvaluationPhases phase, Configuration configuration) {
        return buildObjective(phase, configuration, false);
    }
    
    public static Objective buildObjective(Context.EvaluationPhases phase, Configuration configuration, boolean isStriped){
        Context context = new Context(phase, configuration);
        context.setStripedPhase(isStriped);
        Objective objective = new PerformacesObjective();
        objective.setup(context);
        return objective;
    }
}

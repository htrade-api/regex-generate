package it.units.inginf.male.selections.best;

import it.units.inginf.male.outputs.FinalSolution;
import it.units.inginf.male.outputs.JobEvolutionTrace;
import it.units.inginf.male.outputs.Results;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Picks one individual per job (the best one using the evolution fitness); then it evaluates their performance
 * on the learning set. The individual with the best performance is promoted as the best one.. 
 * 
 * @author nvhuy9527
 */
public class BasicLearningBestSelector implements BestSelector {
    
    private static Logger LOG = Logger.getLogger(BasicLearningBestSelector.class.getName());
   

    @Override
    public void setup(Map<String, String> parameters) {
        
    }

    @Override
    public void elaborate(Results results) {
        this.selectAndPopulateBest(results);
    }
    
    private void selectAndPopulateBest(Results results) {
        double bestIndividualIndex = Double.NEGATIVE_INFINITY;
        int bestLength = Integer.MAX_VALUE;
        FinalSolution best = null;
        for (JobEvolutionTrace jobEvolutionTrace : results.getJobEvolutionTraces()) {
            FinalSolution bestOfJob = jobEvolutionTrace.getFinalGeneration().get(0);
            double precision = bestOfJob.getLearningPerformances().get("match precision");
            double recall = bestOfJob.getLearningPerformances().get("match recall");
            int bestJobLength = bestOfJob.getSolution().length();
            precision = (Double.isNaN(precision))?0:precision;
            recall = (Double.isNaN(recall))?0:recall;
            double individualIndex = (precision+recall)/2;
            if ((individualIndex > bestIndividualIndex) || ((individualIndex == bestIndividualIndex) && (bestLength > bestJobLength))) {
                    bestLength = bestJobLength;
                    best = bestOfJob;
                    bestIndividualIndex = individualIndex;
            }
            
        }
        results.setBestSolution(best);
     
    }
}

package it.units.inginf.male.strategy.impl;

import it.units.inginf.male.outputs.FinalSolution;

/**
 *
 * @author nvhuy9527
 */
public class BasicExecutionStatus {
    
    public boolean isSearchRunning;
    public boolean hasFinalResult;
    public String evolutionEta;
    public FinalSolution best = null;
    
    //public double bestPerformance;
    public int jobTotal = 0;
    public int jobDone = 0;
    public int jobFailed = 0;
    public int overallGenerations = 0;
    public int overallGenerationsDone = 0;
    
    /**
     * Checks the candidate parameter to be better than previous recorded best.
     * When the candidate is better than the best, candidate becames the new best.
     * The best and the candidate need a populated trainingPerformance or the update'll fail
     * 
     * The best solution is the solution with higher training f-measure, when f-measure is the same
     * the best solution is the smaller (string length wise) one.
     * @param candidate
     */
    synchronized public void updateBest(FinalSolution candidate){
        if(this.best == null){
            this.best = candidate;
            return;
        }
        //int index = 0;
        Double bestPerformance = best.getTrainingPerformances().get("match f-measure");
        Double candidatePerformance = candidate.getTrainingPerformances().get("match f-measure");
        if((candidatePerformance > bestPerformance) || 
                ((candidatePerformance.equals(bestPerformance)) && (candidate.getSolution().length()<best.getSolution().length()))){
            this.best = candidate;
        }
        
    
    }
}

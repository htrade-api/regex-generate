package it.units.inginf.male.outputs;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author nvhuy9527
 */
public class JobEvolutionTrace {

    private List<FinalSolution> finalGeneration = new LinkedList<>(); 
    

    private long executionTimeMillis; 

    

    

    public List<FinalSolution> getFinalGeneration() {
        return finalGeneration;
    }

    public void setFinalGeneration(List<FinalSolution> finalGeneration) {
        this.finalGeneration = finalGeneration;
    }

    public long getExecutionTime() {
        return executionTimeMillis;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTimeMillis = executionTime;
    }

}

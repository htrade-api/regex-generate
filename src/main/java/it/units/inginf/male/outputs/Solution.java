package it.units.inginf.male.outputs;

import it.units.inginf.male.objective.Ranking;

/**
 *
 * @author nvhuy9527
 */
public class Solution {

    public Solution() {
    }
    
    public Solution(Ranking individual){
        StringBuilder builder = new StringBuilder();
        individual.getTree().describe(builder);
        this.solution = builder.toString();
        this.fitness = individual.getFitness();
    }

    public Solution(String solution) {
        this.solution = solution;
    }

    public Solution(String solution, double[] fitness) {
        this.solution = solution;
        this.fitness = fitness;
    }
    
    
    private String solution;
    private double[] fitness;
    
    public String getSolution() {
        return solution;
    }

    public void setSolution(String bestSolution) {
        this.solution = bestSolution;
    }

    public double[] getFitness() {
        return fitness;
    }

    public void setFitness(double[] fitness) {
        this.fitness = fitness;
    }
    
    
}

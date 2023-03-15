package it.units.inginf.male.outputs;

import it.units.inginf.male.objective.Ranking;
import it.units.inginf.male.tree.DescriptionContext;
import it.units.inginf.male.tree.Node;
import java.util.HashMap;
import java.util.Map;

/**
 * Extends the Solution class; adds performances on Training, Validation and Learning
 * @author nvhuy9527
 */
public class FinalSolution extends Solution{

    public FinalSolution() {
    }

    public FinalSolution(Ranking individual) {
        super(individual);
        this.solutionJS = getDescriptionJavascript(individual.getTree());
    }
   
    private String getDescriptionJavascript(Node node){
        StringBuilder sb = new StringBuilder();
        node.describe(sb, new DescriptionContext(), Node.RegexFlavour.JS);
        return sb.toString();
    }

    /**
     * Create a FinalSolution for the Java regex (String) solution parameter.
     * Javascript version is not automatically generate, this needs initialization
     * with a Node individual.
     * @param solution
     */
    public FinalSolution(String solution) {
        super(solution);
    }

    public FinalSolution(String solution, double[] fitness) {
        super(solution, fitness);
    }

    private String solutionJS;
    private Map<String,Double> trainingPerformances = new HashMap<>();
    private Map<String,Double> validationPerformances = new HashMap<>();
    private Map<String,Double> learningPerformances = new HashMap<>();

    public Map<String, Double> getTrainingPerformances() {
        return trainingPerformances;
    }

    public void setTrainingPerformances(Map<String, Double> trainingPerformances) {
        this.trainingPerformances = trainingPerformances;
    }

    public Map<String, Double> getValidationPerformances() {
        return validationPerformances;
    }

    public void setValidationPerformances(Map<String, Double> validationPerformances) {
        this.validationPerformances = validationPerformances;
    }

    public Map<String, Double> getLearningPerformances() {
        return learningPerformances;
    }

    public void setLearningPerformances(Map<String, Double> learningPerformances) {
        this.learningPerformances = learningPerformances;
    }

    public String getSolutionJS() {
        return solutionJS;
    }
     
}

package it.units.inginf.male.evaluators;

import it.units.inginf.male.strategy.RunStrategy;
import java.util.regex.PatternSyntaxException;

/**
 *
 * @author nvhuy9527
 */
public class TreeEvaluationException extends Exception {

    private RunStrategy associatedStrategy;

    /**
     * Creates a new instance of <code>TreeEvaluationException</code> without detail message.
     */
    public TreeEvaluationException() {
    }


    /**
     * Constructs an instance of <code>TreeEvaluationException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public TreeEvaluationException(String msg) {
        super(msg);
    }

    public TreeEvaluationException(String message, Throwable cause, RunStrategy associatedStrategy) {
        super(message, cause);
        this.associatedStrategy = associatedStrategy;
    }

    public TreeEvaluationException(String message, RunStrategy associatedStrategy) {
        super(message);
        this.associatedStrategy = associatedStrategy;
    }

    public TreeEvaluationException(PatternSyntaxException ex) {
        super(ex);
    }

    public RunStrategy getAssociatedStrategy() {
        return associatedStrategy;
    }


}

package it.units.inginf.male.evaluators;

import it.units.inginf.male.inputs.Context;
import it.units.inginf.male.inputs.DataSet;
import it.units.inginf.male.inputs.DataSet.Bounds;
import it.units.inginf.male.inputs.DataSet.Example;
import it.units.inginf.male.tree.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 *
 * @author nvhuy9527
 */
public class DefaultTreeEvaluator implements TreeEvaluator {

    @Override
    public List<List<Bounds>> evaluate(Node root, Context context) throws TreeEvaluationException {

        List<List<Bounds>> results = new ArrayList<>(context.getCurrentDataSetLength());

        StringBuilder sb = new StringBuilder();
        root.describe(sb);

        try {
             
            Pattern regex = Pattern.compile(sb.toString());
            Matcher matcher = regex.matcher("");

            int i = 0;
            DataSet dataSet = context.getCurrentDataSet();
            for (Example example : dataSet.getExamples()) {
                try {
                    Matcher m = matcher.reset(example.getString());
                    List<Bounds> b = new LinkedList<>();
                    while (m.find()) {
                        Bounds bounds = new Bounds(matcher.start(0), matcher.end(0));
                        b.add(bounds);

                    }
                    results.add(b);
                } catch (StringIndexOutOfBoundsException ex) {
                    /**
                     * Workaround: ref BUG: 6984178
                     * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6984178
                     * with greedy quantifiers returns exception 
                     * instead than "false".
                     */
                    results.add(Collections.<Bounds>emptyList());
                }

                i++;
            }

        } catch (PatternSyntaxException ex) {
            throw new TreeEvaluationException(ex);
        }
        return results;
    }

    @Override
    public void setup(Map<String, String> parameters) {
    }
}

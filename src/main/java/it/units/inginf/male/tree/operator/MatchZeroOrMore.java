package it.units.inginf.male.tree.operator;

import it.units.inginf.male.tree.DescriptionContext;
import it.units.inginf.male.tree.Node;

/**
 *
 * @author nvhuy9527
 */
public class MatchZeroOrMore extends Quantifier {

    @Override
    public void describe(StringBuilder builder, DescriptionContext context, RegexFlavour flavour) {
        StringBuilder tmp = new StringBuilder();
        Node child = getChildrens().get(0);
        // Counts the group immediatly
        int index = context.incGroups();
        child.describe(tmp, context, flavour);
        int l = child.isEscaped() ? tmp.length() - 1 : tmp.length();
        boolean group = l > 1 && !child.isCharacterClass() && !(child instanceof Group) && !(child instanceof NonCapturingGroup);
        switch (flavour) {
            case JAVA:
                if (group) {
                    builder.append("(?:");
                    builder.append(tmp);
                    builder.append(")");
                } else {
                    builder.append(tmp);
                }
                builder.append("*+");
                break;
            default:
                builder.append("(?=(");
                if (group) {
                    builder.append("(?:");
                    builder.append(tmp);
                    builder.append(")");
                } else {
                    builder.append(tmp);
                }
                builder.append("*))\\").append(index);
                context.incExpansionGroups();
             
        }
    }

    @Override
    protected UnaryOperator buildCopy() {
        return new MatchZeroOrMore();
    }
}

package it.units.inginf.male.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This tokenizer is implemented with the regular expression "\\w+|\\s+|[^\\w\\s]+".
 * This tokenizer generate no overlapping tokens, in fact the elements, of the tokens list, when concatenated re-generate the original string. 
 * @author nvhuy9527
 */
public class BasicTokenizer implements Tokenizer {
    
    private final Pattern pattern = 
            Pattern.compile("\\w+|\\s+|[^\\w\\s]+");

    
    @Override
    public List<String> tokenize(String string){
        List<String> tokensList = new LinkedList<>();
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            tokensList.add(matcher.group());
        }
        return tokensList;
    }
}

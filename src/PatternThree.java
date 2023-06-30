//Erez Chamilevsky 206399867 chamile1

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PatternTwo is the calss that matches the text to pattern "such NP as NP {, NP, ..., {and|or} NP}".
 * The class extends BasicNp.
 */
public class PatternThree extends BasicNP {
    static final int GROUP = 1;
    private String regex;
    private Pattern pat;

    /**
     * Constractor.
     * @param lema
     * @param col
     */
    public PatternThree(String lema, Table col) {
        super(lema, col);
        this.regex = "such " + "<np>(([\\w+]+)+([ .\\-\\w+]+)*)</np>" + " as"
                + " <np>([\\w+]+)+([ .\\-\\w+]+)*</np>"
                + "(( , )<np>([\\w+]+)+([ .\\-\\w+]+)*</np>)*(( ,)*( and| or) <np>([\\w+]+)+([ .\\-\\w+]+)*</np>)?";
        this.pat = Pattern.compile(regex);
    }

    @Override
    public boolean find(String text) {
        Matcher mat = this.pat.matcher(text);
        String temp = "temp";
        String group1 = "1";
        while (mat.find()) {
            temp = mat.group();
            group1 = mat.group(GROUP);
            if (super.find(temp) && !super.isLemma(group1)) {
                this.getCollector().newString(group1);
                // if (temp.contains("a professional academic discipline")) {
                //         System.out.println(temp);
                //     }
            }
        }
        return true;
    }

}
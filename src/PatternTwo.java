
//Erez Chamilevsky 206399867 chamile1
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PatternTwo is the calss that matches the text to pattern "NP {,} which is
 * {{an example|a kind|a class} of} NP".
 * The class extends BasicNp.
 */
public class PatternTwo extends BasicNP {
    static final int GROUP = 3;
    private String regex;
    private Pattern pat;

    /**
     * Constractor.
     *
     * @param lema
     * @param col
     */
    public PatternTwo(String lema, Table col) {
        super(lema, col);
        this.regex = "<np>(([\\w+]+)([ .\\-\\w+]+)*)</np>"
                + "( ,)* which is ((an example|a kind|a class)* of )*<np>(([\\w+]+)([ .\\-\\w+]+)*)</np>";

        this.pat = Pattern.compile(this.regex);
    }

    @Override
    public boolean find(String text) {
        Matcher mat = this.pat.matcher(text);
        String temp = ".";
        while (mat.find()) {
            temp = mat.group();
            if (super.find(temp)) {
                int last = temp.lastIndexOf("<np>");
                String word = temp.substring(last + 4);
                word = word.substring(0, word.lastIndexOf("</np>"));
                if (!super.isLemma(word)) {
                    this.getCollector().newString(word);
                    // if (temp.contains("a professional academic discipline")) {
                    // System.out.println(temp);
                    // }

                }
            }
        }
        return true;
    }

}

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BasicNP is the abstract class of the petterns that should be find in the
 * text.
 */
public abstract class BasicNP {

    private String lema;
    private Table collector;

    /**
     * Constractor.
     *
     * @param lema
     * @param col
     */
    protected BasicNP(String lema, Table col) {
        this.lema = lema;
        this.collector = col;
    }

    /**
     * The find method is a boolean that returns if the match from the child
     * contains the lemma.
     *
     * @param text
     * @return true false
     */
    public boolean find(String text) {
        Pattern a = Pattern.compile("<np>" + this.lema + "</np>");
        Matcher mat1 = a.matcher(text);
        while (mat1.find()) {
            return true;
        }
        return false;
    }

    /**
     * Getting the collector that collect the matches.
     *
     * @return Table
     */
    public Table getCollector() {
        return this.collector;
    }

    /**
     * Checks if the NP is the lemma.
     *
     * @param string
     * @return true false
     */
    public boolean isLemma(String string) {
        return this.lema.equals(string);
    }
}

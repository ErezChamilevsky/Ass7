
//Erez Chamilevsky 206399867 chamile1
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * The program will read all the files in the directory,
 * find all the possible hypernyms of the input lemma that match the Hearst
 * Patterns
 * and print them to the console as follows.
 */
public class DiscoverHypernym {
    private Table collector;

    /**
     * Constractor.
     */
    public DiscoverHypernym() {
        this.collector = new Table();
    }

    /**
     * The manager will runs the program, gets list of files and lemma and updates
     * the collector.
     *
     * @param lemma
     * @param files
     */
    public void manager(String lemma, File[] files) {
        // File directory = new File("C:\\BIU\\מונחה עצמים\\הרצאות\\corpus");
        // File[] files = directory.listFiles();

        PatternOne testA = new PatternOne(lemma, this.collector);
        PatternTwo testB = new PatternTwo(lemma, this.collector);
        PatternThree testC = new PatternThree(lemma, this.collector);
        try {

            for (File a : files) {

                try (BufferedReader is = new BufferedReader(new InputStreamReader(new FileInputStream(a)))) {
                    String line;
                    while ((line = is.readLine()) != null) {
                        if (line.contains(lemma)) {
                            testA.find(line);
                            testB.find(line);
                            testC.find(line);
                        }
                    }
                    is.close();
                } catch (Exception e) {

                }
            }
        } catch (Exception e) {

        }
    }

    /**
     * Getter of the map.
     *
     * @return map
     */
    public Map<String, Integer> getMap() {
        return this.collector.getMap();
    }

    /**
     * Converts the string of args and sends it to the manager to execute.
     *
     * @param args
     */
    public void argsToAction(String[] args) {
        File directory = new File(args[0]);
        File[] files = directory.listFiles();
        this.manager(args[1], files);
    }

    /**
     * Checks if args' size more than 1.
     *
     * @param args
     * @return true/false
     */
    public boolean isValid(String[] args) {
        if (args.length > 1) {
            return true;
        }
        return false;
    }

    /**
     * The main. Sends to the other functions if the input is valid.
     *
     * @param args
     */
    public static void main(String[] args) {
        DiscoverHypernym a = new DiscoverHypernym();
        if (a.isValid(args)) {
            a.argsToAction(args);
            a.collector.print();
        }
    }
}

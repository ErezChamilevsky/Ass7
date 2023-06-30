//Erez Chamilevsky 206399867 chamile1

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The collector of the matches of the patterns.
 */
public class Table {
    private Map<String, Integer> map;
    /**
     * Constractor.
     */
    public Table() {
        this.map = new HashMap<String, Integer>();
    }

    /**
     * Adding new string to the map.
     * @param string
     */
    public void newString(String string) {
        if (this.map.containsKey(string)) {
            this.map.put(string, this.map.get(string) + 1);
            return;
        }
        this.map.put(string, 1);

    }

    /**
     * Getting the map.
     * @return map
     */
    public Map<String, Integer> getMap() {
        return this.map;
    }

    /**
     * Sorting the map in order of decending values and Alpha-Bethical order.
     * @return list
     */
    public List<Map.Entry<String, Integer>> sort() {
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(this.map.entrySet());
        Collections.sort(sortedEntries, Comparator
                .comparing(Map.Entry<String, Integer>::getValue).reversed()
                .thenComparing(Map.Entry<String, Integer>::getKey));
        return sortedEntries;

    }

    /**
     * Printing the list that contains the keys and values.
     */
    public void print() {
        if (this.map.size() == 0) {
            System.out.println("The lemma doesn't appear in the corpus.");
        }
        List<Map.Entry<String, Integer>> a = this.sort();
        for (Map.Entry<String, Integer> entry : a) {
            System.out.println(entry.getKey() + ": (" + entry.getValue() + ")");
        }
    }
}

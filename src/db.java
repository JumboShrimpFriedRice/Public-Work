import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Oliver on 3/11/16.
 */
public class db {
    private static Stack<HashMap<String, Integer>> s = new Stack<>();
    static public boolean checkCommit;

    static public int checkStackSize() {
        return s.size();
    }


    static public HashMap<String, Integer> createMap () {
        HashMap<String, Integer> m = new HashMap<>();
        return m;
    }
    static public void commit () {
        checkCommit = true;
    }

    /**
     * return to the previous HashMap
     * @return
     */
    static public HashMap<String, Integer> rollBack() {
        if (checkCommit) return null;
        if (checkStackSize() == 0) return createMap();
        return s.pop();
    }

    /**
     * Push a HashMap into the stack.
     * Call this function when stack is not empty.
     * @param m
     * @return
     */
    static public HashMap<String, Integer> beginWithAdd(HashMap<String, Integer> m) {
        checkCommit = false;
        s.push(m);
        return createMap();
    }

    /**
     * push a Hashmap into the stack.
     * Call this function when it is the first "begin".
     * @param m
     */
    static public void begin(HashMap<String, Integer> m) {
        checkCommit = false;
        s.push(m);
    }

    /**
     * Takes a key and a value and put it into the hashtmap
     * @param st as string
     * @param v as integer
     */
    static public void set (HashMap<String, Integer> m, String st, int v) {
        m.put(st, v);
    }

    /**
     * Print out the value of the variable name, or NULL if that variable is not set.
     * @param st as string
     * @return value of the name
     */
    static public void get (HashMap<String, Integer> m, String st) {
        if (m.size() == 0) {
            System.out.println("NULL");
            return;
        }
        System.out.println(m.get(st));
    }

    /**
     * Unset the variable name, making it just like that variable was never set.
     * @param m
     * @param st
     */
    static public void unset (HashMap<String, Integer> m, String st) {
        m.put(st, null);
    }

    /**
     * Print out the number of variables that are currently set to value.
     * If no variables equal that value, print 0.
     * @param m
     * @param v
     */
    static public void numEqualTo (HashMap<String, Integer> m, int v) {
        int count = 0;
        for (Map.Entry<String, Integer> entry : m.entrySet()) {
            if (entry.getValue() == null) continue;
            if (entry.getValue() == v) {
                count++;
            }
        }
        System.out.println(count);
    }
}

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private final Map<Character, TrieNode> children; // Map for children nodes
    private boolean isWord; // boolean for marking word nodes; remove the prefix restriction

    public boolean isWord() {
            return isWord;
        }
        public void setWord(boolean b) { isWord = b; }
    // constructor
    public TrieNode() {
        children = new HashMap<>(); // empty HashMap for children
        this.isWord = false; // a node is not storing a word by default
    }
    public Map<Character, TrieNode> getChildren() {
            return children;
        }

    }


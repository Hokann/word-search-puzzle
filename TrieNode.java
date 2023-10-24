import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private Map<Character, TrieNode> children; // Map for children nodes
    private boolean isWord;

    // constructor
    public TrieNode() {
        children = new HashMap<>(); // empty HashMap for children
        this.isWord = false; // a node is not storing a word by default
    }
    public Map<Character, TrieNode> getChildren(){
        return children;
    }
    public void setWord(boolean b){
        isWord = b;
    }

    public boolean isWord() {
        return isWord;
    }
}

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private TrieNode[] children; // Map for children nodes
    private boolean isWord;

    // constructor
    public TrieNode() {
        children = new TrieNode[26]; // empty HashMap for children
        this.isWord = false; // a node is not storing a word by default
    }
    public TrieNode[] getChildren(){
        return children;
    }
    public void setWord(boolean b){
        isWord = b;
    }

    public boolean isWord() {
        return isWord;
    }
}

import java.util.Arrays;

// Trie class for a standard Trie with character nodes
public class Trie {
    private TrieNode root = new TrieNode(); // root of the Trie
    public boolean isEmpty() { return root.getChildren() == null; }
    public void insert( String word ) {
// insert a word in the Trie
        TrieNode node = root; // take the root
        for( char c: word.toCharArray() ) {
            int index = c - 'a';
            // if there is no node with char c in the children, create it
            if( node.getChildren()[index] == null ) node.getChildren()[index] = new TrieNode();
            node = node.getChildren()[index]; // move to it to process the next char
        }
        node.setWord(true); // last node accessed was for the last char of word
    }
    // constructor with initial list of words
    public Trie( String[] words ) {
        for( String word: words ) {
            this.insert( word );
            System.out.println(word);
        }
        System.out.println(Arrays.toString(root.getChildren()));
    }

    public TrieNode getCurrent(){ return root;}
    public void setCurrent(TrieNode root) { this.root = root; }

}

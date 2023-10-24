// Trie class for a standard Trie with character nodes
public class Trie {
    private TrieNode root = new TrieNode(); // root of the Trie
    public boolean isEmpty() { return root.getChildren() == null; }
    public void insert( String word ) {
// insert a word in the Trie
        TrieNode node = root; // take the root
        for( char c: word.toCharArray() ) {
            // if there is no node with char c in the children, create it
            if( node.getChildren().get( c ) == null ) node.getChildren().put( c, new TrieNode() );
            node = node.getChildren().get( c ); // move to it to process the next char
        }
        node.setWord(true); // last node accessed was for the last char of word
    }
    // constructor with initial list of words
    public Trie( String[] words ) {
        for( String word: words ){
            this.insert( word );
            System.out.println(word);
        }
        System.out.println("Number of First Letters "+root.getChildren().size());
    }

    public TrieNode getCurrent(){ return root;}
    public void setCurrent(TrieNode root) { this.root = root; }
    // @Test

    // Check if the trie contains a given word
    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.getChildren().containsKey(ch)) {
                return false;
            }
            node = node.getChildren().get(ch);
        }
        return node.isWord();
    }


}


    // Trie class for a standard Trie with character nodes
    public class Trie {
        private final TrieNode root = new TrieNode(); // root of the Trie
        public TrieNode getRoot() {
            return root;
        }
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
        public Trie(String[] words ) { for( String word: words ) this.insert( word ); }
    }


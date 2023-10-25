
import java.util.HashMap;
import java.util.Map;
    // Trie class for a standard Trie with character nodes
    public class TrieSchool {

        public static class TrieNodeSchool {
            private Map<Character, TrieNodeSchool> children; // Map for children nodes
            private boolean isWord; // boolean for marking word nodes; remove the prefix restriction

            public boolean isWord() {
                return isWord;
            }
            public void setWord(boolean b){
                isWord = b;
            }

            // constructor
            public TrieNodeSchool() {
                children = new HashMap<>(); // empty HashMap for children
                this.isWord = false; // a node is not storing a word by default
            }

            public Map<Character, TrieNodeSchool> getChildren() {
                return children;
            }

            @Override
            public String toString() {
                return "Node< " + this.children.size() + " children, is a word? " + this.isWord + " >";
            }
        }
        private TrieNodeSchool root = new TrieNodeSchool(); // root of the Trie
        public TrieNodeSchool getRoot() {
            return root;
        }
        public void setRoot(TrieNodeSchool root) {
            this.root = root;
        }
        public void insert( String word ) {
            // insert a word in the Trie
            TrieNodeSchool node = root; // take the root
            for( char c: word.toCharArray() ) {
                // if there is no node with char c in the children, create it
                if( node.children.get( c ) == null ) node.children.put( c, new TrieNodeSchool() );
                node = node.children.get( c ); // move to it to process the next char
            }
            node.isWord = true; // last node accessed was for the last char of word
        }

        public boolean search(String word) {
            TrieNodeSchool node = root;
            for (char c : word.toCharArray()) {
                if (node.children.get(c) == null) {
                    return false; // If a character is not found, the word doesn't exist in the trie
                }
                node = node.children.get(c);
            }
            return node.isWord; // Check if the last node represents the end of a word
        }
        // constructor with initial list of words
        public TrieSchool( String[] words ) { for( String word: words ) this.insert( word ); }
    }


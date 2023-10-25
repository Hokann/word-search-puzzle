public class Trie2 {
    public class TrieNode2 {
        protected static TrieNode2[] children;
        boolean isEndOfWord;

        public TrieNode2() {
            children = new TrieNode2[26]; // Assuming only lowercase English letters
            isEndOfWord = false;
        }

        public char getValue() {
            // Assuming the node represents a lowercase English letter
            for (int i = 0; i < 26; i++) {
                if (children[i] != null) {
                    return (char) (i + 'a');
                }
            }
            return '\0'; // Return null character if the node has no value (should not happen in a valid trie)
        }

        public TrieNode2[] getChildren() {
            return children;
        }
    }

    private TrieNode2 root;





    public TrieNode2 getRoot() {
        return root;
    }
    public void setRoot(TrieNode2 newRoot) {
        root = newRoot;
    }

    public Trie2() {
        root = new TrieNode2();
    }

    public Trie2(String[] words){
        root = new TrieNode2();
        for (String word : words) {
            this.insert(word);
        }
    }

    public void insert(String word) {
        TrieNode2 node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode2();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode2 node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode2 node = root;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return true;
    }
}


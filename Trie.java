 /*
    * Classe trie qui permet d'initialiser un arbre de trie standard avec noeuds.
    * Les noeuds sont definis avec la classe TrieNode.
    * La racine root permet d'initialiser un trie.
    * Dans ce project, seules les methodes getRoot() et insert() ont étées implementées.
    * Les slides du cours IFT2015 ont étés utilisés comme références, avec quelques ajustements
    *
    * @author Francois Major
    * @author Hokan Gillot
    * */
    public class Trie {
        private final TrieNode root = new TrieNode(); // racine du trie

        // getter
        public TrieNode getRoot() {
            return root;
        }
        // méthode pour insérer un mot (et tous ses caractères) dans le trie
        public void insert( String word ) {
            TrieNode node = root; // prendre la racine
            for( char c: word.toCharArray() ) {
                // si aucun noeud avec c comme enfant, on le créer
                if( node.getChildren().get( c ) == null ) node.getChildren().put( c, new TrieNode() );
                node = node.getChildren().get( c ); // on passe au prochain noeud
            }
            node.setWord(true); // Notre mot est inséré lorsqu'on arrive à notre dernier noeud, qui sera une feuille
        }
        // constructor avec liste initiale de mots
        public Trie(String[] words ) { for( String word: words ) this.insert( word ); }
    }


import java.util.HashMap;
import java.util.Map;
/*
* Classe qui definie un noeud d'un arbre trie.
* On considère les noeuds enfants, qui sont stockés dans un Hashmap
* Chaque noeuds a la possibilité de prendre une valeur (ici, un char)
* Le boolean isWord permet de verifier si le noeud marque un mot
* Les slides du cours IFT2015 ont été utilisés comme références, avec quelques ajustements faits.
*
* @author Francois Major
* @author Hokan Gillot
* */
public class TrieNode {
    private final Map<Character, TrieNode> children; // Map pour noeuds enfants
    private boolean isWord; // boolean pour marquer si le noeud constitue un mot (sa sequence de noeuds jusqu'à son dernier noeud).
    // getter pour boolean isWord et pour les enfants
    public boolean isWord() { return isWord; }
    public Map<Character, TrieNode> getChildren() {
        return children;
    }
    // setter
    public void setWord(boolean b) { isWord = b; }
    // constructor
    public TrieNode() {
        children = new HashMap<>(); // Hashmap vide pour les enfants
        this.isWord = false; // au debut, un noeud ne stock pas un mot
    }
}


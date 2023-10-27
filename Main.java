import java.io.*;
import java.util.*;
import java.awt.Point;
/*
* Classe Main qui initialise le jeu.
* Permet de trouver les mots d'une grille de caractères. La taille de la grille, la grille elle-même,
* et la liste de mots à trouver est donnée via un fichier texte qu'on passe à la ligne de commande en arguments
*
* Ce programme traverse chaque caractère de la grille et vérifie si un caractère correspond à un caractère d'un mot.
* Si oui, on cherche autour du caractère (les 8 adjacents + lui-même) pour le prochain caractère du mot. Et ainsi de
* suite par récursion. Si on trouve le mot, on l'ajoute lui et son chemin et on l'imprime à la console.
*
* Ce jeu est similaire au jeu de Boggle, sauf qu'on est autorisé à revisiter les caractères et le chemin pour chaque mot
* est pris en compte (chemins multiples pour un mot possible)
*
* @author Hokan Gillot (20242295)
*
* */
public class Main {
    public static void main(String[] args){

        int queryNum = 0; // variable pour compter le nombre de queries du notre fichier
        // try-catch pour s'assurer de trouver un fichier qu'on peut lire
        try {
            File file = new File(args[0]);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();

                // vrai seulement c'est la ligne qui définie la taille de la grille
                // Permet de différencier les queries
                if (data.toUpperCase().equals(data)) {
                    queryNum++;
                    // String -> Integer ; taille de la grille : M lignes et N colonnes
                    int M = Integer.parseInt(data.substring(0,data.indexOf(' ')));
                    int N = Integer.parseInt(data.substring(data.indexOf(' ')+1));

                    // création de la grille de caractères
                    char[][] grid = new char[M][N];
                    for (int i = 0; i < M; i++) {
                        String line = reader.nextLine();
                        String[] characters = line.split(" ");
                        for (int j = 0; j < N; j++) {
                            grid[i][j] = characters[j].charAt(0);
                        }
                    }

                    // structure de donnée pour gérer les mots à trouver. On choisit un trie.
                    String[] words = reader.nextLine().split(" ");
                    Trie trie = new Trie(words);

                    // trouver les mots
                    List<WordPath> foundWords = new ArrayList<>(); // init d'une array pour stocker les mots et chemins
                    // traverser la grille
                    for (int i = 0; i < M; i++) {
                        for (int j = 0; j < N; j++) {
                            // mot initial : string vide // chemin initial : ArrayList vide
                            findWords(i, j, grid, "", trie.getRoot(), new ArrayList<>() , foundWords);
                        }
                    }

                    // On doit trier de façon alphabétique les strings de mots et chemins.
                    // On creer une nouvelle ArrayList de strings pour cela
                    ArrayList<String> wordPathStr = new ArrayList<>();
                    for (WordPath wordPath : foundWords) {
                        String result = wordPath.toString();
                        wordPathStr.add(result);
                    }
                    // on utilise le tri de java qui tri alphabétiquement les strings
                    Collections.sort(wordPathStr);

                    // OUTPUT - Pour chaque query on imprime les mots+chemins, ligne par ligne.
                    System.out.println("Query "+queryNum+":");
                    for (String str : wordPathStr) {
                        System.out.println(str);
                    }
                }
            }
            reader.close(); // fin lecture fichier
        } catch (FileNotFoundException e) {
            System.out.println("Erreur : fichier non trouvé");
            e.printStackTrace();
        }
    }

    static void findWords(int x, int y, char[][] grid, String currWord, TrieNode node, List<Point> currPath, List<WordPath> foundWords) {
        // verifier que nos coordonnées soient justes
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) { return; }

        char chr = grid[x][y]; // init du caractère

        // si il appartient aux enfants du noeud, on l'ajoute au mot courrant, et on note son chemin
        if (node.getChildren().containsKey(chr)) {
            currWord += chr;
            currPath.add(new Point(x, y)); // chemin géré par la classe java Point du package awt
            node = node.getChildren().get(chr); // on passe au prochain noeud

            // si on aboutie à une feuille (c-a-d un mot), alors on ajoute ce mot et son chemin à l'array
            if (node.isWord()) {
                foundWords.add(new WordPath(currWord, new ArrayList<>(currPath)));
                currPath.clear(); // on vide le chemin actuel (pour aider le garbage collector de java)
            }

            // on traverse les 8 caractères adjacents + le caractère actuel
            for (int xx = -1; xx <= 1; xx++) {
                for (int yy = -1; yy <= 1; yy++){
                    // appel récursif
                    findWords(x + xx, y + yy, grid, currWord, node, new ArrayList<>(currPath), foundWords);
                }
            }
        }
    }
}

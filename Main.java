import java.io.*;
import java.util.*;
import java.awt.Point;

class WordWithPath {
    String word;
    String path;

    public WordWithPath(String word, String path) {
        this.word = word;
        this.path = path;
    }
}

public class Main {
    public static void main(String[] args){
        try {
            File fichier = new File(args[0]);
            Scanner lecteur = new Scanner(fichier);
            while (lecteur.hasNextLine()) {
                String data = lecteur.nextLine();
                if (data.toUpperCase().equals(data)) { // vrai seulement c'est la ligne qui definine la taille de la grille. Signal qui differencies les queries
                    int M = Integer.parseInt(data.substring(0,data.indexOf(' ')));
                    int N = Integer.parseInt(data.substring(data.indexOf(' ')+1));

                    // creation grille de caracteres
                    char[][] grille = new char[M][N];
                    System.out.println(grille.length+"x"+grille[0].length);
                    for (int i = 0; i < M; i++) {
                        String line = lecteur.nextLine();
                        String[] characters = line.split(" ");
                        for (int j = 0; j < N; j++) {
                            grille[i][j] = characters[j].charAt(0);
                        }
                    }
                    // Print the 2D character array (optional)
                    for (int i = 0; i < M; i++) {
                        for (int j = 0; j < N; j++) {
                            System.out.print(grille[i][j] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println(Arrays.deepToString(grille));

  // ------------------------------------------------------------------------------------------------------------------------

                    // ajout des mots a trouver dans un trie.
                    // There is little sense in trying to search the word TLRSOU because there are no words in English alphabet that start with TLR. So a better idea is to store words in a trie and terminate fast the branches that do not makes sense
                    String[] words = lecteur.nextLine().split(" ");
                    TrieSchool trie = new TrieSchool(words);

                    // trouver les mots
                    List<WordWithPath> foundWords = new ArrayList<>();

                    for (int i = 0; i < M; i++) {
                        for (int j = 0; j < N; j++) {
                            findWords(i, j, "", grille, trie.getRoot(), new ArrayList<>(), foundWords);
                        }
                    }

                    // sort and print
                    Collections.sort(foundWords, Comparator.comparing(o -> o.word));
                    System.out.println("-----------------------");
                    for (WordWithPath wordWithPath : foundWords) {
                        System.out.println(wordWithPath.word + " " + wordWithPath.path);
                    }
                    System.out.println("-----------------------");
                }
            }
            lecteur.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur");
            e.printStackTrace();
        }
    }

    static void findWords(int x, int y, String currentWord, char[][] grid, TrieSchool.TrieNodeSchool node, List<Point> currentPath, List<WordWithPath> foundWords) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) { return; }

        char ch = grid[x][y];
        if (node.getChildren().containsKey(ch)) {
            currentWord += ch;
            currentPath.add(new Point(x, y));
            node = node.getChildren().get(ch);

            if (node.isWord()) {
                foundWords.add(new WordWithPath(currentWord, formatPath(currentPath)));
                currentPath.clear();
            }

            for (int xx = -1; xx <= 1; xx++) {
                for (int yy = -1; yy <= 1; yy++){
                    findWords(x + xx, y + yy, currentWord, grid, node, new ArrayList<>(currentPath), foundWords);

                }
            }
        }
    }

    static String formatPath(List<Point> path) {
        StringBuilder formattedPath = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            Point point = path.get(i);
            formattedPath.append("(").append(point.x).append(",").append(point.y).append(")");
            if (i < path.size() - 1) {
                formattedPath.append("->");
            }
        }
        return formattedPath.toString();
    }

}

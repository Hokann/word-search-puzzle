import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1, 0};


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
                        if (characters.length != N) {
                            System.out.println("Error: Invalid number of columns in row " + (i + 1));
                            return;
                        }
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
                    System.out.println(trie.search("sea"));     // Output: true
                    System.out.println(trie.search("son"));  // Output: true
                    System.out.println(trie.search("share"));   // Output: true
                    System.out.println(trie.search("willow"));    // Output: true

                    // trouver les mots
                    Set<String> foundWords = new HashSet<>();
                    for (int i = 0; i < M; i++) {
                        for (int j = 0; j < N; j++) {
                            findWordsInGrid(i, j, "", grille, trie.getRoot(), foundWords);
                        }
                    }
                    System.out.println("Found Words: " + foundWords);
                }
            }
            lecteur.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur");
            e.printStackTrace();
        }

    }


    static void findWordsInGrid(int x, int y, String currentWord, char[][] grid, TrieSchool.TrieNodeSchool node, Set<String> foundWords) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return;
        }

        char ch = grid[x][y];
        if (node.getChildren().containsKey(ch)) {
            currentWord += ch;
            node = node.getChildren().get(ch);

            if (node.isWord()) {
                foundWords.add(currentWord);
                // Handle the found word, such as storing it in a set or any other processing.
            }

            // Explore all 8 neighboring cells using DFS
            for (int i = 0; i < 8; i++) {
                findWordsInGrid(x + dx[i], y + dy[i], currentWord, grid, node, foundWords);
            }


        }
    }
}

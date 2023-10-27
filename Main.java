//TODO
// commentaires
// cleanup code, transform code,
// .jar file & making sure it works on other devices

import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {
    public static void main(String[] args){
        int query = 0;
        try {
            File file = new File(args[0]);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (data.toUpperCase().equals(data)) { // vrai seulement c'est la ligne qui definine la taille de la grille. Signal qui differencies les queries
                    query++;
                    int M = Integer.parseInt(data.substring(0,data.indexOf(' ')));
                    int N = Integer.parseInt(data.substring(data.indexOf(' ')+1));

                    // creation grille de caracteres
                    char[][] grid = new char[M][N];
                    for (int i = 0; i < M; i++) {
                        String line = reader.nextLine();
                        String[] characters = line.split(" ");
                        for (int j = 0; j < N; j++) {
                            grid[i][j] = characters[j].charAt(0);
                        }
                    }

                    // ajout des mots a trouver dans un trie.
                    // There is little sense in trying to search the word TLRSOU because there are no words in English alphabet that start with TLR. So a better idea is to store words in a trie and terminate fast the branches that do not makes sense
                    String[] words = reader.nextLine().split(" ");
                    Trie trie = new Trie(words);

                    // trouver les mots
                    List<WordPath> foundWords = new ArrayList<>();
                    List<Point> currentPath = new ArrayList<>();
                    for (int i = 0; i < M; i++) {
                        for (int j = 0; j < N; j++) {
                            findWords(i, j, "", grid, trie.getRoot(), new ArrayList<>() , foundWords);
                        }
                    }


                    // sort and print
                    ArrayList<String> wordPathStr = new ArrayList<>();

                    for (WordPath wordPath : foundWords) {
                        String result = wordPath.toString();
                        wordPathStr.add(result);
                    }
                    Collections.sort(wordPathStr);

                    // print out results
                    System.out.println("Query "+query+":");
                    for (String str : wordPathStr) {
                        System.out.println(str);
                    }
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur : fichier non trouvé");
            e.printStackTrace();
        }
    }

    static void findWords(int x, int y, String currWord, char[][] grid, TrieNode node, List<Point> currPath, List<WordPath> foundWords) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) { return; }

        char chr = grid[x][y];
        if (node.getChildren().containsKey(chr)) {
            currWord += chr;
            currPath.add(new Point(x, y));
            node = node.getChildren().get(chr);

            if (node.isWord()) {
                foundWords.add(new WordPath(currWord, new ArrayList<>(currPath)));
                currPath.clear();
            }

            for (int xx = -1; xx <= 1; xx++) {
                for (int yy = -1; yy <= 1; yy++){
                    findWords(x + xx, y + yy, currWord, grid, node, new ArrayList<>(currPath), foundWords);

                }
            }
        }
    }
}

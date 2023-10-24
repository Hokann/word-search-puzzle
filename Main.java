import java.io.*;
import java.util.*;

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

                    // ajout des mots a trouver dans un trie.
                    // There is little sense in trying to search the word TLRSOU because there are no words in English alphabet that start with TLR. So a better idea is to store words in a trie and terminate fast the branches that do not makes sense
                    String[] words = lecteur.nextLine().split(" ");
                    Trie trie = new Trie(words);

                    // trouver les mots
                    wordSearch(grille, trie);
                }
            }
            lecteur.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur");
            e.printStackTrace();
        }

    }
    public static List<String> wordSearch(char[][] board, Trie trie) {
        if (trie.isEmpty()){ return null; }

        List<String> result = new ArrayList<>();

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                char letter = board[i][j];
                if (trie.getCurrent().getChildren().containsKey(letter)){
                    // if we get a letter that could be a word
                    // store position in queue. (position class + queue class)
                    //

                    //trie.setCurrent(trie.getCurrent().getChildren().get(letter));
                    //System.out.println("Number of 2nd letters for "+letter+" is "+trie.getCurrent().getChildren().size());
                }
            }
        }
        return result;
    }

}

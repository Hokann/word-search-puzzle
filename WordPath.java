import java.awt.*;
import java.util.List;
/*
* Classe qui stock une paire d'objets : un mot (String) et un chemin (List<Point>)
* Chaque mot qu'on trouve dans la grille a un chemin associé, cette classe permet de lier les 2 en un objet
* Surtout, cette classe facilite l'output en definisssant une méthode toString() qui créer un string mot+chemin
* */
public class WordPath {
    private final String word;
    private final List<Point> path;

    public WordPath(String word, List<Point> path) {
        this.word = word;
        this.path = path;
    }

    // On utilise un StringBuilder (meilleure performance que la concaténation, surtout pour des longs strings)
    // On a le format suivant: "mot (x1,y1)->(x2,y2)->...->(xn,yn)"
    public String toString() {
        StringBuilder finalString = new StringBuilder();
        for (int i = 0; i < path.size(); i++) { // on traverse le chemin char par char
            Point point = path.get(i);
            finalString.append("(").append(point.x).append(",").append(point.y).append(")"); // format (x,y)
            if (i < path.size() - 1) { // on rajoute une flèche apres chaque (x,y) SAUF pour le dernier
                finalString.append("->");
            }
        }
        return word+" "+finalString; // on rajoute le mot devant
    }
}


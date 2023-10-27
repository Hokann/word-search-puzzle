import java.awt.*;
import java.util.List;

public class WordPath {
    private final String word;
    private final List<Point> path;

    public WordPath(String word, List<Point> path) {
        this.word = word;
        this.path = path;
    }

    public String toString() {
        StringBuilder finalString = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            Point point = path.get(i);
            finalString.append("(").append(point.x).append(",").append(point.y).append(")");
            if (i < path.size() - 1) {
                finalString.append("->");
            }
        }
        return word+" "+finalString;
    }
}


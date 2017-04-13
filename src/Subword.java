import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Nishant
 */
public class Subword {
    private ArrayList<Node> subWord;
    private int D;
    private double actualProbability, tempProbability;

    public Subword(Node node) {
        this.subWord.add(node);
//        actualProbability=

    }

    public double getActualProbability() {
        return actualProbability;
    }

    public double getTempProbability() {
        return tempProbability;
    }

    public void addNode(Node node) {
        subWord.add(node);
    }
}
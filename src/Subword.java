import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Nish on 06/04/2017.
 */
public class Subword {
    private ArrayList<Node> subWord;
    private int D;
    private double actualProbability, tempProbability;

    public Subword(ArrayList<Node> subWord) {
        this.subWord = subWord;
    }

    public double getActualProbability() {
        return actualProbability;
    }

    public void setActualProbability(double actualProbability) {
        this.actualProbability = actualProbability;
    }

    public double getTempProbability() {
        return tempProbability;
    }

    public void setTempProbability(double tempProbability) {
        this.tempProbability = tempProbability;
    }
}
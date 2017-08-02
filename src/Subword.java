import java.util.ArrayList;

public class Subword extends TrieNode {
    private double actualProbability, extendedProbability;
    private int D;
    private Subword root;
    private ArrayList<TrieNode> leaves;
    private Subword[] array;

    public Subword() {
        root = new Subword();
        root.setActualProbability(1);
        root.setExtendedProbability(1);
        leaves = new ArrayList<>();
        array = new Subword[26];
    }
    //Getter for extendedProbability
    public double getExtendedProbability() {
        return extendedProbability;
    }

    //Setter for extendedProbability
    public void setExtendedProbability(double extendedProbability) {
        this.extendedProbability = extendedProbability;
    }

    //Getter for actualProbability
    public double getActualProbability() {
        return actualProbability;
    }

    //Setter for actualProbability
    public void setActualProbability(double actualProbability) {
        this.actualProbability = actualProbability;
    }

    //Getter for int D
    public int getD() {
        return D;
    }

    //Setter for int D
    public void setD(int d) {
        D = d;
    }

    //Getter for root
    public Subword getRoot() {
        return root;
    }

    //Getter for children of the root
    @Override
    public Subword[] getArray() {
        return array;
    }

    // Sets up a child node for a node
    public void setArrayIndex(int index, Subword node) {
        array[index] = node;
        array[index].setEnd(true);
    }
}
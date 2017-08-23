/**
 * Created by Nishant
 * This class aids to build the weighted suffix tree by the Algorithm class
 */

public class Subword extends TrieNode{
    private double actualProbability, extendedProbability;
    private int D;
    private int nextIndex;

    private int subwordIndex;
    private Subword[] array;
    private String prefix;
    boolean isEnd;

    public Subword() {
        this.setActualProbability(1);
        this.setExtendedProbability(1);
        array = new Subword[26];
        isEnd = true;
        prefix = "";
        nextIndex = 0;
        subwordIndex = -1;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int nextIndex) {
        this.nextIndex = nextIndex;
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
        return this;
    }

    //Getter for children of the root
    public Subword[] getArray() {
        return array;
    }

    // Sets up a child node for a node
    public void setArrayIndex(int index, Subword node) {
        array[index] = node;
        isEnd = false;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setEnd(Boolean value) {
        isEnd = value;
    }

    public Boolean isEnd() {
        return isEnd;
    }

    public int getSubwordIndex() {
        return subwordIndex;
    }

    public void setSubwordIndex(int subwordIndex) {
        this.subwordIndex = subwordIndex;
    }
}
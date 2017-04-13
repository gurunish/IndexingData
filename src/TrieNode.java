/**
 * Created by Nishant
 */
class TrieNode {
    private TrieNode[] array;
    private int D;
    private double actualProbability, extendedProbability;
    //Boolean value to check whether the node is a leaf or internal node
    private boolean isEnd;

    //Constructor for each trie node that allows storing alphabets from a-z
    public TrieNode() {
        this.array = new TrieNode[26];
    }

    //Getter for the TrieNode[] array
    public TrieNode[] getArray() {
        return array;
    }

    //Getter for isEnd
    public boolean isEnd() {
        return isEnd;
    }

    //Setter for isEnd
    public void setEnd(boolean end) {
        isEnd = end;
    }

    //Setter for an index of the array
    public void setArrayIndex(int index, TrieNode node) {
        array[index] = node;
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
}
/**
 * Created by Nishant
 * This class represents each node of the Trie data structure
 */
class TrieNode {
    private TrieNode[] array;
    private int D;
    private double a,c,g,t,actualProbability, extendedProbability;
    //Boolean value to check whether the node is a leaf or internal node
    private boolean isEnd;

    //Alternative constructor
    public TrieNode(){
        array = new TrieNode[26];
    }

    //Constructor for each trie node that allows storing alphabets from a-z
    public TrieNode(Double a, double c, double g, double t) {
        this.a = a;
        this.c = c;
        this.g = g;
        this.t = t;
        array = new TrieNode[26];
    }

    @Override
    public String toString(){
        String allChildren = "";
        for(TrieNode t: array){
            allChildren += getA() + " " + getC() +  " " + getG() + " " + getT() + "\n";
        }

        return allChildren;
    }

    //Getter for a
    public double getA() {
        return a;
    }

    //Getter for c
    public double getC() {
        return c;
    }

    //Getter for g
    public double getG() {
        return g;
    }

    //Getter for t
    public double getT() {
        return t;
    }

    //Getter for the TrieNode[] array
    public TrieNode[] getArray() {
        return array;
    }

    //Getter for isEnd
    public boolean isEnd() {
        return isEnd;
    }

    //Setter for an index of the array
    public void setArrayIndex(int index, TrieNode node) {
        array[index] = node;
        isEnd = false;
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

    //Get probability of a character of this node
    public double getCharProbability(char c) {
        switch (c) {
            case 'a':
                return getA();
            case 'c':
                return getC();
            case 'g':
                return getG();
            case 't':
                return getG();
            default:
                return 0;
        }
    }
}
/**
 * Created by Nishant
 * This class represents each node of the Trie data structure
 * Based on the tutorial from the following source
 * ***************************************************************************************
 * Title: LeetCode – Implement Trie (Prefix Tree) (Java)
 * Author: ProgramCreek
 * Date: May 2015
 * Code version: 1.0
 * Availability: http://www.programcreek.com/2014/05/leetcode-implement-trie-prefix-tree-java/
 */

class TrieNode {
    private TrieNode[] array;
    private double a, c, g, t;
    //Boolean value to check whether the node is a leaf or internal node
    private boolean isEnd;

    //Alternative constructor
    public TrieNode() {
        array = new TrieNode[26];
    }

    //Constructor for each trie node that allows storing alphabets from a-z
    public TrieNode(double a, double c, double g, double t) {
        this.a = a;
        this.c = c;
        this.g = g;
        this.t = t;
        array = new TrieNode[26];
        isEnd = true;
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
        array[index].setEnd(true);
    }

    public void setEnd(boolean b) {
        isEnd = b;
    }
}
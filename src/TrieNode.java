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
    private double a, c, g, t;

    //Constructor for each trie node that stores values of char a,c,g,t
    public TrieNode(double a, double c, double g, double t) {
        this.a = a;
        this.c = c;
        this.g = g;
        this.t = t;
    }

    TrieNode() {
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

    public String toString(){
        return ""+ getA() + " " + getC() + " " + getG() + " " + getT();
    }
}
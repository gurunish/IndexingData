import java.util.ArrayList;

/**
 * Created by Nishant
 * This class sets up the Trie structure, and calls upon TrieNode
 * Based on the tutorial from the following source
 * ***************************************************************************************
 * Title: LeetCode – Implement Trie (Prefix Tree) (Java)
 * Author: ProgramCreek
 * Date: May 2015
 * Code version: 1.0
 * Availability: http://www.programcreek.com/2014/05/leetcode-implement-trie-prefix-tree-java/
 */

public class Trie {
    private Subword root;
    private ArrayList<Subword> leaves;

    public Trie() {
        root = new Subword();
        leaves = new ArrayList<>();
    }

    // Inserts a word into the trie
    public void insertWord(String word) {
        if (!word.isEmpty()) {
            Subword currentNode = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';  // 'a' has value of 97 so 'a' will be stored in [0], 't' will be stored at [19]
                if (currentNode.getArray()[index] == null) {
                    //New node is added
                    Subword temp = new Subword();
                    currentNode.setArrayIndex(index, temp);
                    currentNode = temp; //next iteration(if necessary) continues from the new node
                } else {
                    currentNode = currentNode.getArray()[index];
                }
            }
            leaves.add(currentNode);
        }
    }

    // Searches for an entire word
    public boolean searchWord(String word) {
        TrieNode currentNode = searchNode(word);
        if (currentNode == null) {
            return false;
        } else {
            if (currentNode.isEnd()) {
                return true;
            }
        }
        return false;
    }

    //Algorithm for searching function
    public TrieNode searchNode(String s) {
        TrieNode currentNode = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'a';
            if (currentNode.getArray()[index] != null) {
                currentNode = currentNode.getArray()[index];
            } else {
                return null;
            }
        }
        if (currentNode == root) {
            return null;
        }
        return currentNode;
    }

    //Getter for root
    public TrieNode getRoot() {
        return root;
    }

    //Return leaves of Trie
    public ArrayList<Subword> getLeaves() {
        return leaves;
    }
}
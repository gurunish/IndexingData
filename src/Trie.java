/**
 * Created by Nishant
 * This class sets up the Trie structure, and calls upon TrieNode
 */

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie
    public void insertWord(String word) {
        if (!word.isEmpty()) {
            TrieNode currentNode = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';  // 'a' has value of 97 so 'a' will be stored in [0], 't' will be stored at [19]
                if (currentNode.getArray()[index] == null) {
                    //New node is added
                    TrieNode temp = new TrieNode();
                    currentNode.setArrayIndex(index, temp);
                    currentNode = temp; //next iteration(if necessary) continues from the new node
                } else {
                    currentNode = currentNode.getArray()[index];
                }
            }
            currentNode.setEnd(true);
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

    // Searches if there is any word in the trie that starts with the prefix
    public boolean startsWith(String prefix) {
        TrieNode currentNode = searchNode(prefix);
        if (currentNode == null) {
            return false;
        } else {
            return true;
        }
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
    public TrieNode getRoot(){
        return root;
    }
}
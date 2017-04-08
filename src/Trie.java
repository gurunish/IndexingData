/**
 * Created by Nish on 08/04/2017.
 */

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie
    public void insertWord(String word) {
        if(!word.isEmpty()) {
            TrieNode n = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';  // 'a' has value of 97 so 'a' will be stored in [0], 't' will be stored at [19]
                if (n.getArray()[index] == null) {
                    //New node is added
                    TrieNode temp = new TrieNode();
                    n.getArray()[index] = temp;
                    n = temp; //next iteration(if necessary) continues from the new node
                } else {
                    n = n.getArray()[index];
                }
            }
            n.setEnd(true);
        }
    }

    // Searches for a whole word block
    public boolean searchWord(String word) {
        TrieNode n = searchNode(word);
        if(n==null){
            return false;
        }else{
            if(n.isEnd()) {
                return true;
            }
        }
        return false;
    }

    // Searches if there is any word in the trie that starts with the prefix
    public boolean startsWith(String prefix) {
        TrieNode n = searchNode(prefix);
        if(n==null){
            return false;
        }else{
            return true;
        }
    }

    //Algorithm for word search
    public TrieNode searchNode(String s){
        TrieNode n = root;
        for(int i=0; i<s.length(); i++){
            char c= s.charAt(i);
            int index = c-'a';
            if(n.getArray()[index]!=null){
                n = n.getArray()[index];
            }else{
                return null;
            }
        }
        if(n==root) {
            return null;
        }
        return n;
    }
}
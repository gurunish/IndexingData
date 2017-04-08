/**
 * Created by Nish on 08/04/2017.
 */


public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode p = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            int index = c-'a';
            if(p.getArray()[index]==null){
                TrieNode temp = new TrieNode();
                p.getArray()[index]=temp;
                p = temp;
            }else{
                p=p.getArray()[index];
            }
        }
        p.setEnd(true);
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode p = searchNode(word);
        if(p==null){
            return false;
        }else{
            if(p.isEnd()) {
                return true;
            }
        }
        return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode p = searchNode(prefix);
        if(p==null){
            return false;
        }else{
            return true;
        }
    }

    public TrieNode searchNode(String s){
        TrieNode p = root;
        for(int i=0; i<s.length(); i++){
            char c= s.charAt(i);
            int index = c-'a';
            if(p.getArray()[index]!=null){
                p = p.getArray()[index];
            }else{
                return null;
            }
        }

        if(p==root)
            return null;

        return p;
    }
}
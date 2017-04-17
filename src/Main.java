import java.util.ArrayList;

/**
 * Created by Nishant
 * This main class invokes the GUI
 */
public class Main {
    public static void main(String[] args) {
        Trie t = new Trie();
        TrieNode a = new TrieNode(1,0,0,0);
        TrieNode b = new TrieNode(0,1,1,0);
        TrieNode x = new TrieNode(0.1,0.1,0.1,0.1);
        TrieNode y = new TrieNode(0.25,0.25,0.25,0.25);
        TrieNode x1= new TrieNode(0.25,0.25,0.25,0.25);
        TrieNode x2 = new TrieNode(0.25,0.25,0.25,0.25);
        ArrayList<TrieNode> what = new ArrayList<>();
        what.add(a);
        what.add(b);
        what.add(x);
        what.add(x1);
        what.add(x2);
        what.add(y);
        Algorithm c = new Algorithm(what, 9, "what");

//        Gui g = new Gui();
    }
}
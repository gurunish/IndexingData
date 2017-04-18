import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Created by Nishant on 18/04/2017.
 */
class TrieTest {
    Trie t = new Trie();
    private ArrayList<TrieNode> leaves = new ArrayList<>();

    @Test
    void getLeaves() {
        Assert.assertEquals(t.getLeaves(), leaves);
    }

    @Test
    void insertWord() {
        t.insertWord("abc");
        Assert.assertEquals(true, t.searchWord("abc"));
        Assert.assertEquals(false, t.searchWord("ab"));

    }

    @Test
    void searchWord() {
        Assert.assertEquals(false, t.searchWord("xyz"));
        t.insertWord("xyz");
        Assert.assertEquals(true, t.searchWord("xyz"));
    }

    @Test
    void searchNode() {
        t.insertWord("g");
        int index = 'g' - 'a';
        Assert.assertEquals(t.getRoot().getArray()[index], t.searchNode("g"));
    }
}
import org.junit.Assert;

/**
 * Created by Nishant on 17/04/2017.
 */
class TrieNodeTest {
    TrieNode testNode = new TrieNode(0.1, 0.2, 0.3, 0.4);
    TrieNode[] emptyArray = new TrieNode[26];

    @org.junit.jupiter.api.Test
    void getA() {
        Assert.assertEquals(0.1, testNode.getA(), 0);
        Assert.assertNotEquals(0, testNode.getA());
    }

    @org.junit.jupiter.api.Test
    void getC() {
        Assert.assertEquals(0.2, testNode.getC(), 0);
        Assert.assertNotEquals(0, testNode.getC());
    }

    @org.junit.jupiter.api.Test
    void getG() {
        Assert.assertEquals(0.3, testNode.getG(), 0);
        Assert.assertNotEquals(0, testNode.getG());
    }

    @org.junit.jupiter.api.Test
    void getT() {
        Assert.assertEquals(0.4, testNode.getT(), 0);
        Assert.assertNotEquals(0, testNode.getT());
    }

    @org.junit.jupiter.api.Test
    void getArray() {
        Assert.assertArrayEquals(emptyArray, testNode.getArray());
    }

    @org.junit.jupiter.api.Test
    void isEnd() {
        Assert.assertEquals(true, testNode.isEnd());
    }

    @org.junit.jupiter.api.Test
    void setArrayIndex() {
        TrieNode testNode2 = new TrieNode(0, 0, 0, 1);
        testNode.setArrayIndex(0, testNode2);
        Assert.assertEquals(false, testNode.isEnd());
        Assert.assertEquals(testNode2, testNode.getArray()[0]);
    }

    @org.junit.jupiter.api.Test
    void getExtendedProbability() {
        Assert.assertEquals(1, testNode.getExtendedProbability(), 0);
    }

    @org.junit.jupiter.api.Test
    void setExtendedProbability() {
        testNode.setExtendedProbability(10);
        Assert.assertEquals(10, testNode.getExtendedProbability(), 0);
    }

    @org.junit.jupiter.api.Test
    void getActualProbability() {
        Assert.assertEquals(1, testNode.getActualProbability(), 0);
    }

    @org.junit.jupiter.api.Test
    void setActualProbability() {
        Assert.assertEquals(1, testNode.getActualProbability(), 0);
    }

    @org.junit.jupiter.api.Test
    void getD() {
        Assert.assertEquals(0, testNode.getD(), 0);
    }

    @org.junit.jupiter.api.Test
    void setD() {
        testNode.setD(20);
        Assert.assertEquals(20, testNode.getD(), 0);
    }

    @org.junit.jupiter.api.Test
    void setEnd() {
        testNode.setEnd(false);
        Assert.assertEquals(false, testNode.isEnd());
    }
}
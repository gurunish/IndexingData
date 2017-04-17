import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Nishant on 17/04/2017.
 */
class TrieNodeTest {
    TrieNode testNode = new TrieNode(0.1,0.2,0.3,0.4);
    TrieNode[] emptyArray = new TrieNode[26];

    @org.junit.jupiter.api.Test
    void getA() {
        assertEquals(0.1,testNode.getA());
        assertNotEquals(0,testNode.getA());
    }

    @org.junit.jupiter.api.Test
    void getC() {
        assertEquals(0.2,testNode.getC());
        assertNotEquals(0,testNode.getC());
    }

    @org.junit.jupiter.api.Test
    void getG() {
        assertEquals(0.3,testNode.getG());
        assertNotEquals(0,testNode.getG());
    }

    @org.junit.jupiter.api.Test
    void getT() {
        assertEquals(0.4,testNode.getT());
        assertNotEquals(0,testNode.getT());
    }

    @org.junit.jupiter.api.Test
    void getArray() {
        assertArrayEquals(emptyArray,testNode.getArray());
    }

    @org.junit.jupiter.api.Test
    void isEnd() {
        assertEquals(false,testNode.isEnd());
    }

    @org.junit.jupiter.api.Test
    void setArrayIndex() {
        TrieNode testNode2 = new TrieNode(0,0,0,1);
        testNode.setArrayIndex(0,testNode2);
        assertEquals(false,testNode.isEnd());
        assertEquals(testNode2,testNode.getArray()[0]);
    }

    @org.junit.jupiter.api.Test
    void getExtendedProbability() {
        assertEquals(1,testNode.getExtendedProbability());
    }

    @org.junit.jupiter.api.Test
    void setExtendedProbability() {
        testNode.setExtendedProbability(10);
        assertEquals(10,testNode.getExtendedProbability());
    }

    @org.junit.jupiter.api.Test
    void getActualProbability() {
    }

    @org.junit.jupiter.api.Test
    void setActualProbability() {
    }

    @org.junit.jupiter.api.Test
    void getD() {
    }

    @org.junit.jupiter.api.Test
    void setD() {
    }

    @org.junit.jupiter.api.Test
    void setEnd() {
    }

}
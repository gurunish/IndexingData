/**
 * Created by Nish on 08/04/2017.
 */
class TrieNode {
    private TrieNode[] array;
    private boolean isEnd;

    public TrieNode() {
        this.array = new TrieNode[26];
    }

    public TrieNode[] getArray() {
        return array;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public void setArrayIndex(int index, TrieNode node){
        array[index] = node;
    }
}
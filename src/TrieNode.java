/**
 * Created by Nish on 08/04/2017.
 */
class TrieNode {
    private TrieNode[] array;
    private double actualProbability, extendedProbability;
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

    public void setArrayIndex(int index, TrieNode node) {
        array[index] = node;
    }

    public double getExtendedProbability() {
        return extendedProbability;
    }

    public void setExtendedProbability(double extendedProbability) {
        this.extendedProbability = extendedProbability;
    }

    public double getActualProbability() {
        return actualProbability;
    }

    public void setActualProbability(double actualProbability) {
        this.actualProbability = actualProbability;
    }
}
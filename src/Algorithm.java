import java.util.ArrayList;

/**
 * Created by Nishant
 * The algorithm for all phases are derived from "The Weighted Suffix Tree: An Efficient Data Structure for Handling
 * Molecular Weighted Sequences and its Applications" by C. S. Iliopoulous,  C. Makris, Y. Panagis, K. Perdikuri,
 * E. Theodoridos and A. Tsakalidis (2006)
 */
public class Algorithm {
    private ArrayList<TrieNode> weightedSequence;
    private double k;
    //stores the indices of black positions
    private ArrayList<Integer> blackIndices;
    //0 is white, 1 is gray, 2 is black
    private int[] colorIndices;
    private String searchWord;
    private String word;
    private Trie t;
    private ArrayList<Trie> LT;

    public Algorithm(ArrayList<TrieNode> sequence, int constant, String word) {
        weightedSequence = sequence;
        blackIndices = new ArrayList<>();
        colorIndices = new int[sequence.size()];
        searchWord = word;
        t = new Trie();
        LT = new ArrayList<>();
        k = constant;
        coloringPhase();
        generationPhase();
        constructionPhase();
    }

    //Phase 1- coloring phase which has O(n) complexity
    private void coloringPhase() {
        for (int i = 0; i < weightedSequence.size(); i++) {
            TrieNode temp = weightedSequence.get(i);
            if (temp.getA() == 0 || temp.getC() == 0 || temp.getG() == 0 || temp.getT() == 0) {
                //white node
                colorIndices[i] = 0;
            } else if (temp.getA() > 1 - 1 / k || temp.getC() > 1 - 1 / k ||
                    temp.getG() > 1 - 1 / k || temp.getT() > 1 - 1 / k) {
                //gray node
                colorIndices[i] = 1;
            } else {
                //black node
                colorIndices[i] = 2;
                blackIndices.add(i);
            }
        }
    }

    //Phase 2- generation phase
    private void generationPhase() {
        for (int i : blackIndices) {
            word = "";
            int counter = 0;
            int current = i;
            while (current + 1 < colorIndices.length) {
                for (int j = i; j < weightedSequence.size() - 1; j++) {
                    if (weightedSequence.get(i).getA() >= 1 / k) {
                        extend(t.getRoot(), 'a', weightedSequence.get(current).getA(), colorIndices[i]);
                        word += 'a';
                        counter++;
                    }
                    if (weightedSequence.get(i).getC() >= 1 / k) {
                        extend(t.getRoot(), 'c', weightedSequence.get(current).getC(), colorIndices[i]);
                        word += 'c';
                        counter++;
                    }
                    if (weightedSequence.get(i).getG() >= 1 / k) {
                        extend(t.getRoot(), 'g', weightedSequence.get(current).getG(), colorIndices[i]);
                        word += 'g';
                        counter++;
                    }
                    if (weightedSequence.get(i).getT() >= 1 / k) {
                        extend(t.getRoot(), 't', weightedSequence.get(current).getT(), colorIndices[i]);
                        word += 't';
                        counter++;
                    }
                }
                current++;
            }
            TrieNode j = weightedSequence.get(i);
            while (counter > 0) {
                j = weightedSequence.get(i + 1);
                counter = 0;
                ArrayList<TrieNode> leaves = t.getLeaves();
                for (TrieNode leaf : leaves) {
                    if (leaf.getA() >= 1 / k) {
                        extend(leaf, 'a', leaf.getA(), colorIndices[i + 1]);
                        word += 'a';
                        counter++;
                    }
                    if (leaf.getC() >= 1 / k) {
                        extend(leaf, 'c', leaf.getA(), colorIndices[i + 1]);
                        word += 'c';
                        counter++;
                    }
                    if (leaf.getG() >= 1 / k) {
                        extend(leaf, 'g', leaf.getA(), colorIndices[i + 1]);
                        word += 'g';
                        counter++;
                    }
                    if (leaf.getT() >= 1 / k) {
                        extend(leaf, 't', leaf.getA(), colorIndices[i + 1]);
                        word += 't';
                        counter++;
                    }
                }
            }
            t.insertWord(word);
            LT.add(t);
        }
    }

    //Phase 3- construction phase
    private void constructionPhase() {
        // declare weighted suffix tree
        // for(leaves in LT) do {
        // }
    }

    //Extend algorithm for phase 2
    private void extend(TrieNode node, char c, double probabilityOfC, int color) {
        if (node.getExtendedProbability() * probabilityOfC >= 1 / k) {
            TrieNode temp = new TrieNode();
            int index = c - 'a';
            node.setArrayIndex(index, temp);
            temp.setActualProbability(node.getActualProbability() * probabilityOfC);
            if (color == 0 || color == 1) {
                temp.setExtendedProbability(node.getActualProbability() * 1);
                if (node.getD() > 0) {
                    temp.setD(node.getD() + 1);
                } else {
                    temp.setD(0);
                }
            } else {
                temp.setExtendedProbability(node.getExtendedProbability() * probabilityOfC);
                temp.setD(0);
            }
            System.out.println("Extended");
        }
    }

    public String getResult() {
        String results = "";
        for (Trie t : LT) {
            results += t.searchNode(searchWord);
        }
        return results;
    }
}
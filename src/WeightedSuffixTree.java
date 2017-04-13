import java.util.ArrayList;

/**
 * Created by Nishant
 *
 * The algorithm for all phases are derived from "The Weighted Suffix Tree: An Efficient Data Structure for Handling
 * Molecular Weighted Sequences and its Applications" by C. S. Iliopoulous,  C. Makris, Y. Panagis, K. Perdikuri,
 * E. Theodoridos and A. Tsakalidis (2006)
 */
public class WeightedSuffixTree {
    private ArrayList<Node> weightedSequence;
    private double k;
    //stores the indices of black positions
    private ArrayList<Integer> blackIndices;
    //0 is white, 1 is gray, 2 is black
    private int[] colorIndices;
    private Trie t;
    private ArrayList<Trie> LT;

    public WeightedSuffixTree(ArrayList<Node> sequence, int constant) {
        weightedSequence = sequence;
        blackIndices = new ArrayList<Integer>();
        colorIndices = new int[weightedSequence.size()];
        t = new Trie();
        LT = new ArrayList<>();
        k = constant;
        coloringPhase();
        generationPhase();
        constructionPhase();
    }

    //Phase 1- coloring phase which has O(n) complexity
    public void coloringPhase() {
        for (int i = 0; i < weightedSequence.size() - 1; i++) {
            if (weightedSequence.get(i).getA() == 0 || weightedSequence.get(i).getC() == 0 ||
                    weightedSequence.get(i).getG() == 0 || weightedSequence.get(i).getT() == 0) {
                //white node
                colorIndices[i] = 0;
            } else if (weightedSequence.get(i).getA() > 1 - 1 / k || weightedSequence.get(i).getC() > 1 - 1 / k ||
                    weightedSequence.get(i).getG() > 1 - 1 / k || weightedSequence.get(i).getT() > 1 - 1 / k) {
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
    public void generationPhase() {
        for (int i : blackIndices) {
            int counter = 0;

            int current = i;
            Subword temp = new Subword(weightedSequence.get(i));
            while (current + 1 < colorIndices.length) {
                for (Node tempNode : weightedSequence) {
                    if (tempNode.getA() > 1 - 1 / k || tempNode.getC() > 1 - 1 / k ||
                            tempNode.getG() > 1 - 1 / k || tempNode.getT() > 1 - 1 / k) {

                        //extend(t, t.getRoot(), character c, probability(c), colorIndices[i]);
                        counter++;
                    }
                }
            }

            Node j = weightedSequence.get(i);
            while (counter > 0) {
                j = weightedSequence.get(i + 1);
                counter = 0;
                //for all leaves of t do

                //extend(t, t.getRoot(), character c, probability(c) , color of j);

            }
            LT.add(t);
        }
    }

    //Construction phase
    private void constructionPhase() {
        // declare weighted suffix tree
        // for(leaves in LT) do {

        // }
    }

    //Extend algorithm for phase 2
    private void extend(Trie trie, TrieNode node, char c, double probabilityOfC, int color) {
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
        }
    }
}
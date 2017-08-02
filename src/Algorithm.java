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
    private Trie t;
    private Subword subwords;
    private ArrayList<Trie> LT;

    public Algorithm(ArrayList<TrieNode> sequence, int constant, String word) {
        weightedSequence = sequence;
        blackIndices = new ArrayList<>();
        colorIndices = new int[sequence.size()];
        searchWord = word;
        t = new Trie();
        LT = new ArrayList<>();
        subwords = new Subword();
        k = constant;
        coloringPhase();
        generationPhase();
        constructionPhase();
    }

    //Phase 1- coloring phase which has O(n) complexity
    private void coloringPhase() {
        for (int i = 0; i < weightedSequence.size(); i++) {
            TrieNode temp = weightedSequence.get(i);
            if (temp.getA() == 1 || temp.getC() == 1 || temp.getG() == 1 || temp.getT() == 1) {
                //white node; only one possible character
                colorIndices[i] = 0;
            } else if (temp.getA() > 1 - 1 / k || temp.getC() > 1 - 1 / k ||
                    temp.getG() > 1 - 1 / k || temp.getT() > 1 - 1 / k) {
                //gray node; 0 possible characters
                colorIndices[i] = 1;
            } else {
                //black node; multiple possible characters
                colorIndices[i] = 2;
                blackIndices.add(i);
            }
        }
    }

    /*
        n = 5
        1 - 1/5 = 0.8

        One node a,c,g,t : 0.1, 0.2, 0.3, 0.4
        black because no p(char) > 0.8

        Second node a,c,b,t : 1, 0 ,0 ,0
        white because p(a) = 1

        Third node a,c,g,t : 0, 0.9, 0.1, 0
        grey because p(g) > 0.8
     */

    //Phase 2 - generation phase
    private void generationPhase() {
        for (int i : blackIndices) {
            int counter = 0;
            int currentIndex = i;
            while (currentIndex + 1 < colorIndices.length) {
                for (int j = i; j < weightedSequence.size() - 1; j++) {
                    if (weightedSequence.get(i).getA() >= 1 / k) {
                        extend(subwords.getRoot(), 'a', weightedSequence.get(currentIndex).getA(), colorIndices[i]);
                        counter++;
                    }
                    if (weightedSequence.get(i).getC() >= 1 / k) {
                        extend(subwords.getRoot(), 'c', weightedSequence.get(currentIndex).getC(), colorIndices[i]);
                        counter++;
                    }
                    if (weightedSequence.get(i).getG() >= 1 / k) {
                        extend(subwords.getRoot(), 'g', weightedSequence.get(currentIndex).getG(), colorIndices[i]);
                        counter++;
                    }
                    if (weightedSequence.get(i).getT() >= 1 / k) {
                        extend(subwords.getRoot(), 't', weightedSequence.get(currentIndex).getT(), colorIndices[i]);
                        counter++;
                    }
                }
                currentIndex++;
            }

            int nextIndex = i;
            while (counter > 0) {
                /*
                    counter > 0 implies a subword extension
                 */
                nextIndex += 1;
                counter = 0;
                ArrayList<Subword> leaves = t.getLeaves();
                for (Subword leaf : leaves) {
                    if (leaf.getA() >= 1 / k) {
                        extend(leaf, 'a', leaf.getA(), colorIndices[nextIndex]);
                        counter++;
                    }
                    if (leaf.getC() >= 1 / k) {
                        extend(leaf, 'c', leaf.getC(), colorIndices[nextIndex]);
                        counter++;
                    }
                    if (leaf.getG() >= 1 / k) {
                        extend(leaf, 'g', leaf.getG(), colorIndices[nextIndex]);
                        counter++;
                    }
                    if (leaf.getT() >= 1 / k) {
                        extend(leaf, 't', leaf.getT(), colorIndices[nextIndex]);
                        counter++;
                    }
                }
            }
            //LT.add(subwords);
        }
    }

    //Phase 3- construction phase
    private void constructionPhase() {
        /* declare weighted suffix tree
           for(leaves in LT) do {
                Z' = path label of each leaf
                compute redundancies of Dj
                insert Z' to weighted suffix tree
                retore actual subwords of Z' and  its suffixes in the WST
        */
    }

    //Extend algorithm for phase 2
    private void extend(Subword node, char c, double probabilityOfC, int color) {
        if (node.getExtendedProbability() * probabilityOfC >= 1 / k) {
            Subword temp = new Subword();
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

    //Method that returns results of pattern  matching
    public String getResult() {
        String results = "";
        for (Trie t : LT) {
            results += t.searchNode(searchWord);
        }
        return results;
    }
}
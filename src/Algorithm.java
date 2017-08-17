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
    private ArrayList<Trie> LT;

    public Algorithm(ArrayList<TrieNode> sequence, int constant, String word) {
        weightedSequence = sequence;
        blackIndices = new ArrayList<>();
        colorIndices = new int[sequence.size()];
        searchWord = word;
        k = constant;
        LT = new ArrayList<>();
        coloringPhase();
        generationPhase();
        //constructionPhase();
    }

    //Phase 1- coloring phase which has O(n) complexity
    private void coloringPhase() {
        for (int i = 0; i < weightedSequence.size(); i++) {
            TrieNode temp = weightedSequence.get(i);
            if (temp.getA() == 1 || temp.getC() == 1 || temp.getG() == 1 || temp.getT() == 1) {
                //white node
                colorIndices[i] = 0;
            } else if (temp.getA() > 1 - 1 / k || temp.getC() > 1 - 1 / k || temp.getG() > 1 - 1 / k || temp.getT() > 1 - 1 / k) {
                /*gray node*/
                colorIndices[i] = 1;
            } else {
                //black node
                colorIndices[i] = 2;
                blackIndices.add(i);
            }
        }

        System.out.println("indices colors are ");
        for (int x : colorIndices) {
            System.out.print(" " + x);
        }
        System.out.println("");
    }

    //Phase 2- generation phase
    private void generationPhase() {
        for (int i : blackIndices) {
            t = new Trie();
            if (i + 1 <= weightedSequence.size()) {
                System.out.println("Extending black index " + i);
                int counter = 0;
                int current = i;
                if (weightedSequence.get(i).getA() >= 1 / k) {
                    extend(t.getRoot(), 'a', weightedSequence.get(current).getA(), colorIndices[i]);
                    counter++;
                }
                if (weightedSequence.get(i).getC() >= 1 / k) {
                    extend(t.getRoot(), 'c', weightedSequence.get(current).getC(), colorIndices[i]);
                    counter++;
                }
                if (weightedSequence.get(i).getG() >= 1 / k) {
                    extend(t.getRoot(), 'g', weightedSequence.get(current).getG(), colorIndices[i]);
                    counter++;
                }
                if (weightedSequence.get(i).getT() >= 1 / k) {
                    extend(t.getRoot(), 't', weightedSequence.get(current).getT(), colorIndices[i]);
                    counter++;
                }
                current++;

                int extensionNumber = 1;
                System.out.println("Leaf extension begin");
                while (counter > 0) {
                    counter = 0;
                    ArrayList<Subword> leaves = t.getLeaves();
                    for (int y = 0; y<leaves.size(); y++){
                        Subword leaf = leaves.get(y);
                        TrieNode nextNode = weightedSequence.get(i + extensionNumber);

                        if (nextNode.getA() >= 1 / k) {
                            extend(leaf, 'a', nextNode.getA(), colorIndices[i + extensionNumber]);
                            counter++;
                        }
                        if (nextNode.getC() >= 1 / k) {
                            extend(leaf, 'c', nextNode.getC(), colorIndices[i + extensionNumber]);
                            counter++;
                        }
                        if (nextNode.getG() >= 1 / k) {
                            extend(leaf, 'g', nextNode.getG(), colorIndices[i + extensionNumber]);
                            counter++;
                        }
                        if (nextNode.getT() >= 1 / k) {
                            extend(leaf, 't', nextNode.getT(), colorIndices[i + extensionNumber]);
                            counter++;
                        }
                        extensionNumber++;
                        if (i + extensionNumber == leaves.size()) {
                            counter = 0;
                        }
                    }
                }
                System.out.println("Leaf extension ends");

                //for (String x: word){
                //   System.out.println("Words at index " + i + " " +x);
                //}
                LT.add(t);
            }
        }
    }

    //Phase 3- construction phase
    private void constructionPhase() {
        System.out.println("Construction phase began");
        /* declare weighted suffix tree
           for(leaves in LT) do {
                Z' = path label of each leaf
                compute redundancies of Dj
                insert Z' to weighted suffix tree
                retore actual subwords of Z' and  its suffixes in the WST
        */
        System.out.println("Construction phase ended");
    }

    //Extend algorithm for phase 2
    private void extend(Subword node, char c, double probabilityOfC, int color) {
        if (node.getExtendedProbability() * probabilityOfC >= 1 / k) {
            Subword temp = new Subword();
            int index = c - 'a';
            temp.setActualProbability(node.getActualProbability() * probabilityOfC);
            if (color == 0 || color == 1) {
                temp.setExtendedProbability(node.getActualProbability());
                if (node.getD() > 0) {
                    temp.setD(node.getD() + 1);
                } else {
                    temp.setD(0);
                }
            } else {
                temp.setExtendedProbability(node.getExtendedProbability() * probabilityOfC);
                temp.setD(0);
            }
            temp.setPrefix(node.getPrefix() + c);
            t.insertWord(temp.getPrefix(), temp);
            node.setArrayIndex(index, temp);
            System.out.println("INSIDE EXTEND:" + temp.getPrefix() + " was added");
        }
    }

    //Method that returns results of pattern  matching
    public String getResult() {
        String results = "false";
        for (Trie x : LT) {
            if (x.searchWord(searchWord)) {
                results = "true";
                break;
            }
        }
        return results;
    }
}
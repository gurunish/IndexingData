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
    private ArrayList<String> word;
    private Trie t;
    private ArrayList<Trie> LT;

    public Algorithm(ArrayList<TrieNode> sequence, int constant, String word) {
        System.out.println("Building suffix tree");
        weightedSequence = sequence;
        blackIndices = new ArrayList<>();
        colorIndices = new int[sequence.size()];
        searchWord = word;
        t = new Trie();
        LT = new ArrayList<>();
        k = constant;
        coloringPhase();
        generationPhase();
        System.out.println("Building complete");
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

        /*for(TrieNode x : weightedSequence){
            System.out.println(x);
        }
        */

        System.out.println("indices colors are ");
        for(int x : colorIndices){
            System.out.print(" " +x);
        }
        System.out.println("");
    }

    //Phase 2- generation phase
    private void generationPhase() {
        //System.out.println("Generation phase began");
        for (int i : blackIndices) {
            if(i+1 <= weightedSequence.size()){
                //System.out.println("Extending black index "+ i);
                word = new ArrayList<>();
                int counter = 0;
                int current = i;
                if (weightedSequence.get(i).getA() >= 1 / k) {
                    extend(t.getRoot(), 'a', weightedSequence.get(current).getA(), colorIndices[i]);
                    word.add("a");
                    t.insertWord("a");
                    counter++;
                }
                if (weightedSequence.get(i).getC() >= 1 / k) {
                    extend(t.getRoot(), 'c', weightedSequence.get(current).getC(), colorIndices[i]);
                    word.add("c");
                    t.insertWord("c");
                    counter++;
                }
                if (weightedSequence.get(i).getG() >= 1 / k) {
                    extend(t.getRoot(), 'g', weightedSequence.get(current).getG(), colorIndices[i]);
                    word.add("g");
                    t.insertWord("g");
                    counter++;
                }
                if (weightedSequence.get(i).getT() >= 1 / k) {
                    extend(t.getRoot(), 't', weightedSequence.get(current).getT(), colorIndices[i]);
                    word.add("t");
                    t.insertWord("t");
                    counter++;
                }
                current++;

                int extensionNumber = 1;
                while (counter > 0 && i+extensionNumber <= weightedSequence.size()) {
                    counter = 0;
                    System.out.println("Black extended by " + counter);
                    ArrayList<Subword> leaves = t.getLeaves();
                    for (Subword leaf : leaves) {
                        if (leaf.getA() >= 1 / k) {
                            extend(leaf, 'a', leaf.getA(), colorIndices[i+extensionNumber]);
                            word.add("a");
                            counter++;
                        }
                        if (leaf.getC() >= 1 / k) {
                            extend(leaf, 'c', leaf.getA(), colorIndices[i+extensionNumber]);
                            word.add("c");
                            counter++;
                        }
                        if (leaf.getG() >= 1 / k) {
                            extend(leaf, 'g', leaf.getA(), colorIndices[i+extensionNumber]);
                            word.add("g");
                            counter++;
                        }
                        if (leaf.getT() >= 1 / k) {
                            extend(leaf, 't', leaf.getA(), colorIndices[i+extensionNumber]);
                            word.add("t");
                            counter++;
                        }
                    }
                }
                for (String x: word){
                    System.out.println("Words at index " + i + " " +x);
                }
                LT.add(t);
            }
        }
        System.out.println("Generation phase ended");
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
            node.setArrayIndex(index, temp);
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
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
    private Trie t;
    private ArrayList<Trie> LT;

    public Algorithm(ArrayList<TrieNode> sequence, int constant) {
        weightedSequence = sequence;
        blackIndices = new ArrayList<>();
        colorIndices = new int[sequence.size()];
        k = constant;
        LT = new ArrayList<>();
        coloringPhase();
        generationPhase();
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
    }

    private void generationPhase() {
        createSubwords(0);
        for (int i : blackIndices) {
            createSubwords(i);
        }
    }

    //Phase 2- generation phase
    private void createSubwords(int i) {
        t = new Trie();
        System.out.println("Extending black index " + i);

        int counter = 0;
        if (weightedSequence.get(i).getA() >= 1 / k) {
            extend(t.getRoot(), 'a', weightedSequence.get(i).getA(),i, Boolean.TRUE);
            counter++;
        }
        if (weightedSequence.get(i).getC() >= 1 / k) {
            extend(t.getRoot(), 'c', weightedSequence.get(i).getC(), i, Boolean.TRUE);
            counter++;
        }
        if (weightedSequence.get(i).getG() >= 1 / k) {
            extend(t.getRoot(), 'g', weightedSequence.get(i).getG(), i, Boolean.TRUE);
            counter++;
        }
        if (weightedSequence.get(i).getT() >= 1 / k) {
            extend(t.getRoot(), 't', weightedSequence.get(i).getT(), i, Boolean.TRUE);
            counter++;
        }

        int leafIndex = 0;
        ArrayList<Subword> leaves = t.getLeaves();
        //System.out.println("Leaf extension begin");
        while (counter > 0) {
            counter =0;
            for (int y = 0; y < leaves.size(); y++) {
                Subword leaf = leaves.get(leafIndex);
                System.out.println("Leaf is " + leaf.getPrefix() + ", subindex:" + leaf.getSubwordIndex() + " nextIndex: " + leaf.getNextIndex());
                if(leaf.getNextIndex() < weightedSequence.size()){
                    TrieNode nextNode = weightedSequence.get(leaf.getNextIndex());
                    System.out.println("Extend attempt leaf " + leaf.getPrefix());
                    System.out.println("Next node is :" + nextNode);
                    counter =0;

                    if (nextNode.getA() >= 1 / k) {
                        extend(leaf, 'a', nextNode.getA(), leaf.getNextIndex(), Boolean.FALSE);
                        counter++;
                    }
                    if (nextNode.getC() >= 1 / k) {
                        extend(leaf, 'c', nextNode.getC(), leaf.getNextIndex(), new Boolean(false));
                        counter++;
                    }
                    if (nextNode.getG() >= 1 / k) {
                        extend(leaf, 'g', nextNode.getG(), leaf.getNextIndex(), new Boolean(false));
                        counter++;
                    }
                    if (nextNode.getT() >= 1 / k) {
                        extend(leaf, 't', nextNode.getT(), leaf.getNextIndex(), new Boolean(false));
                        counter++;
                    }
                    if(counter>0){
                        //System.out.println(leaf.getPrefix() + " was removed");
                        t.removeLeaf(leaf.getPrefix());
                    }
                } else {
                    break;
                }
            }
        }
        //System.out.println("Leaf extension ends");
        LT.add(t);
    }

    //Extend algorithm for phase 2
    private void extend(Subword node, char c, double probabilityOfC, int nodeIndex, Boolean isNode) {
        if (node.getExtendedProbability() * probabilityOfC >= 1 / k) {
            Subword temp = new Subword();
            int index = c - 'a';
            temp.setActualProbability(node.getActualProbability() * probabilityOfC);
            if (colorIndices[nodeIndex] == 0 || colorIndices[nodeIndex] == 1) {
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

            if(isNode){
                temp.setSubwordIndex(nodeIndex);
                temp.setNextIndex(++nodeIndex);
            } else {
                temp.setSubwordIndex(node.getSubwordIndex());
                temp.setNextIndex(node.getNextIndex()+1);
            }

            temp.setPrefix(node.getPrefix() + c);
            t.insertWord(temp.getPrefix(), temp);
            node.setArrayIndex(index, temp);
            System.out.println("Extend:Added " + temp.getPrefix());
        }
    }

    //Method that returns results of pattern  matching
    public String getResult(String searchWord) {
        String results = "The substring is found on indices ";
        for (Trie x : LT) {
            String temp = x.searchIndices(searchWord);
            if (!temp.isEmpty()){
                results+= temp + ",";
            }
        }

        if(results.equals("The substring is found on indices ")){
            return "The substring was not found within the provided parameters";
        } else {
            return results.substring(0, results.length()-1);
        }
    }
}
import java.util.ArrayList;

/**
 * Created by Nishant on 05/04/2017.
 * The algorithm for all phases is derived from The Weighted Suffix Tree: An Efficient Data Structure for Handling
 * Molecular Weighted Sequences and its Applications by C. S. Iliopoulous,  C. Makris, Y. Panagis, K. Perdikuri,
 * E. Theodoridos and A. Tsakalidis (2006)
 */
public class WeightedSuffixTree {
    private ArrayList<Node> weightedSequence;
    private double k;
    //stores the indices of black positions
    private ArrayList<Integer> blackIndices;
    //0 is white, 1 is gray, 2 is black
    private int[] colorIndices;

    public WeightedSuffixTree(ArrayList<Node> sequence, int constant) {
        weightedSequence = sequence;
        blackIndices = new ArrayList<Integer>();
        colorIndices = new int[weightedSequence.size()];
        k = constant;
        coloringPhase();
        generationPhase();
        constructionPhase();
    }

    //Coloring phase algorithm
    // O(n) complexity
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

    public void generationPhase() {

        for(int i: blackIndices){
            int current = i;
            int counter = 0;

            Subword temp = new Subword(weightedSequence.get(i));
            while(current+1<colorIndices.length){
                //Generate a trie of subwords
            }
        }

        for(Node temp: weightedSequence){
            if(temp.getA() > 1 - 1 / k || temp.getC() > 1 - 1 / k ||
                    temp.getG() > 1 - 1 / k || temp.getT() > 1 - 1 / k ){
//                extend();
            }
        }

        // TODO: 06/04/2017  
    }

    private void constructionPhase() {
        // TODO: 06/04/2017  
    }

    private void extend(Subword subword, Node node, char c, double probability, int color){

    }

}
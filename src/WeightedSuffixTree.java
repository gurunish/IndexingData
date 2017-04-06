import java.util.ArrayList;

/**
 * Created by Nishant on 05/04/2017.
 * The algorithm for all phases is derived from The Weighted Suffix Tree: An Efficient Data Structure for Handling
    Molecular Weighted Sequences and its Applications by C. S. Iliopoulous,  C. Makris, Y. Panagis, K. Perdikuri,
    E. Theodoridos and A. Tsakalidis (2006)
 */
public class WeightedSuffixTree {
    private ArrayList<SeqIndex> weightedSequence;
    private double k;
    //stores black indices as 1, 0 for non-black
    private int[] blackIndices;
    //0 is white, 1 is gray, 2 is black
    private int[] colorIndices;

    public WeightedSuffixTree(ArrayList<SeqIndex> sequence, int constant) {
        weightedSequence = sequence;
        blackIndices = new int[weightedSequence.size()];
        colorIndices = new int[weightedSequence.size()];
        k = constant;
        coloringPhase();
        generationPhase();
        constructionPhase();
    }

 

    //Coloring phase algorithm
    public void coloringPhase(){
        int blackIndiceCounter = 0;
        for (int i=0; i<weightedSequence.size()-1; i++) {
            if(weightedSequence.get(i).getA() == 0 || weightedSequence.get(i).getC() == 0||
                    weightedSequence.get(i).getG() == 0 || weightedSequence.get(i).getT() == 0){
                colorIndices[i] = 0;
            } else if(weightedSequence.get(i).getA() > 1-1/k || weightedSequence.get(i).getC() > 1-1/k||
                    weightedSequence.get(i).getG() > 1-1/k || weightedSequence.get(i).getT() > 1-1/k){
                colorIndices[i] = 1;
            } else {
                colorIndices[i] = 2;
                blackIndices[blackIndiceCounter] = i;
                blackIndiceCounter++;
            }
        }
    }

    public void generationPhase(){
        // TODO: 06/04/2017  
    }

    private void constructionPhase() {
        // TODO: 06/04/2017  
    }
}
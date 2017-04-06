import java.util.ArrayList;

/**
 * Created by Nishant on 05/04/2017.
 */
public class WeightedSuffixTree {
    private ArrayList<SeqIndex> weightedSequence;
    private int K;
    //stores black indices as 1, 0 for non-black
    private int[] blackIndices;
    //0 is white, 1 is gray, 2 is black
    private int[] colorIndices;

    public WeightedSuffixTree(ArrayList<SeqIndex> sequence, int constant) {
        weightedSequence = sequence;
        blackIndices = new int[weightedSequence.size()];
        colorIndices = new int[weightedSequence.size()];
        K = constant;
        coloringPhase();
    }

    public void coloringPhase(){
        for (int i=0; i<weightedSequence.size()-1; i++) {
            if(weightedSequence.get(i).getA() == 0 || weightedSequence.get(i).getC() == 0||
                    weightedSequence.get(i).getG() == 0 || weightedSequence.get(i).getT() == 0){
                colorIndices[i] =0;
            } else {

            }
        }
    }

    public void generationPhase(){

    }
}
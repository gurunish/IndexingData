/**
 * Created by Nish on 05/04/2017.
 */
public class SeqIndex {
    private Double a,c,g,t;
    public SeqIndex(Double first, Double second, Double third, Double fourth){
        a = first;
        c = second;
        g = third;
        t = fourth;
    }

    @Override
    public String toString(){
        return ""+ a + " " + c + " " + g + " " + t;
    }

    public Double getT() {
        return t;
    }

    public Double getG() {

        return g;
    }

    public Double getC() {

        return c;
    }

    public Double getA() {

        return a;
    }
}

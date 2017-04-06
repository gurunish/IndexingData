/**
 * Created by Nish on 05/04/2017.
 */
public class Node {
    private double a, c, g, t, actualProbability, tempProbability;
    private int D;


    public Node(double first, double second, double third, double fourth) {
        a = first;
        c = second;
        g = third;
        t = fourth;
    }

    @Override
    public String toString() {
        return "" + a + " " + c + " " + g + " " + t;
    }

    public double getT() {
        return t;
    }

    public double getG() {
        return g;
    }

    public double getC() {
        return c;
    }

    public double getA() {
        return a;
    }


}
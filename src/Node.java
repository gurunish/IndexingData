/**
 * Created by Nishant
 * This classs represents each node of the weighted sequence
 */
public class Node {
    private double a, c, g, t;

    public Node(double first, double second, double third, double fourth) {
        a = first;
        c = second;
        g = third;
        t = fourth;
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
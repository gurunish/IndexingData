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

    public double getActualProbability(char c) {
        switch (c) {
            case 'a':
                return getA();
            case 'c':
                return getC();
            case 'g':
                return getG();
            case 't':
                return getG();
            default:
                return 0;
        }
    }
}
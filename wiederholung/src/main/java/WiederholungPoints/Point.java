package WiederholungPoints;

public class Point {
    private int quadrant;
    private double xKoordinate;
    private double yKoordinate;

    public Point(int quadrant, double xKoordinate, double yKoordinate) {
        this.quadrant = quadrant;
        this.xKoordinate = xKoordinate;
        this.yKoordinate = yKoordinate;
    }

    public int getQuadrant() {
        return quadrant;
    }

    public double getxKoordinate() {
        return xKoordinate;
    }

    public double getyKoordinate() {
        return yKoordinate;
    }


    @Override
    public String toString() {
        return "Point{" +
                "quadrant=" + quadrant +
                ", xKoordinate=" + xKoordinate +
                ", yKoordinate=" + yKoordinate +
                '}';
    }
}

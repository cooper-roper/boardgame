package cs345.deadwood.model;

public class Area implements IArea {
    private final int x;
    private final int y;
    private final int h;
    private final int w;
    private int number;

    public Area(int x, int y, int h, int w) {
        this(1, x, y, h, w);
    }

    public Area(int number, int x, int y, int h, int w) {
        this.number = number;
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    @Override
    public String toString() {
        return "Area{" +
                "x=" + x +
                ", y=" + y +
                ", h=" + h +
                ", w=" + w +
                ", number=" + number +
                '}';
    }

    @Override
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getH() {
        return h;
    }

    @Override
    public int getW() {
        return w;
    }
}

package com.fx.w2p.pojo;

/**
 *
 * @author pscha
 */
public class UvVector {

    private double x;
    private double y;

    public UvVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "<" + x + "," + y + ">";
    }
}

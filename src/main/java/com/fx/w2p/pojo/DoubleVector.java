package com.fx.w2p.pojo;

/**
 *
 * @author pscha
 */
public class DoubleVector {

    private double x;
    private double y;
    private double z;

    public DoubleVector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "<" + x + "," + y + "," + z + ">";
    }
}

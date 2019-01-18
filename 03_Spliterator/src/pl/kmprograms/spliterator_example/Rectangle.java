package pl.kmprograms.spliterator_example;

import java.text.MessageFormat;
import java.util.Objects;

public class Rectangle {
    private double x;
    private double y;

    public Rectangle() {
    }

    public Rectangle(double x, double y) {
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

    public double area() {
        return x * y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.x, x) == 0 &&
                Double.compare(rectangle.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return MessageFormat.format("RECTANGLE: [ {0}, {1} ]", x, y);
    }
}

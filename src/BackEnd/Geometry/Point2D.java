package BackEnd.Geometry;

import java.util.Objects;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class Point2D {
    private int x;
    private int y;

    public Point2D() {
        this.x = -1;
        this.y = -1;
    }

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point2D(Point2D point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getXPixels() {
        return x * TILE_SIZE;
    }

    public int getYPixels() {
        return y * TILE_SIZE;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x) + Objects.hash(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Point2D that = (Point2D) o;

        return that.getX() == this.x && that.getY() == this.y;
    }
}

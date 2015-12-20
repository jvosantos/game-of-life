package com.jvosantos.games.gameoflife;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate() {
        this(0, 0);
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate add(Coordinate coordinate) {
        return new Coordinate(x + coordinate.x, y + coordinate.y);
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Coordinate that = (Coordinate) o;

        if (y != that.y)
            return false;
        return x == that.x;

    }

    @Override public int hashCode() {
        int result = y;
        result = 31 * result + x;
        return result;
    }

    @Override public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

package com.jvosantos.games.gameoflife;

public class Cell {
    private CellState state;

    public Cell() {
        this(CellState.DEAD);
    }

    public Cell(CellState state) {
        this.state = state;
    }

    public CellState getState() {
        return state;
    }

    public void reapSoul() {
        this.state = CellState.DEAD;
    }

    public void giveLife() {
        this.state = CellState.ALIVE;
    }

    public boolean isAlive() { return state == CellState.ALIVE; }

    public boolean isDeath() { return state == CellState.DEAD; }

    @Override
    public String toString() {
        switch (state) {
            case ALIVE:
                return "X";
            case DEAD:
                return " ";
        }

        return "";
    }
}

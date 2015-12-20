package com.jvosantos.games.gameoflife;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class GameOfLifeEndless implements GameOfLife {

    private static final int UNDER_POPULATION = 2;
    private static final int OVER_POPULAITON = 3;
    private static final int REPRODUCTION = 3;

    private static final List<Coordinate> neighbourCoordinatesMask;

    static {
        neighbourCoordinatesMask = new ArrayList<>();
        neighbourCoordinatesMask.add(new Coordinate(-1, -1));
        neighbourCoordinatesMask.add(new Coordinate(-1, 0));
        neighbourCoordinatesMask.add(new Coordinate(-1, 1));
        neighbourCoordinatesMask.add(new Coordinate(0, -1));
        neighbourCoordinatesMask.add(new Coordinate(0, 1));
        neighbourCoordinatesMask.add(new Coordinate(1, -1));
        neighbourCoordinatesMask.add(new Coordinate(1, 0));
        neighbourCoordinatesMask.add(new Coordinate(1, 1));
    }


    private Map<Coordinate, CellState> world;
    private PatternStrategy patternStrategy;

    private Size size;

    public GameOfLifeEndless() {
        clearWorld();
        patternStrategy = PatternStrategy.KEEP_INITIAL_PATTERN;
    }

    public void setOutputStrategy(PatternStrategy patternStrategy) {
        this.patternStrategy = patternStrategy;
    }

    @Override public void seed(int[][] pattern) {
        // Empty the current world.
        clearWorld();

        // if output strategy is to keep initial pattern size, get dimensions of initial pattern
        if (patternStrategy == PatternStrategy.KEEP_INITIAL_PATTERN) {
            size = new Size(Utils.getMaxColumns(pattern), pattern.length);
        }

        // Add all living cells to the world.
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[i].length; j++) {
                if (pattern[i][j] == CellState.ALIVE.ordinal()) {
                    world.put(new Coordinate(i, j), CellState.values()[pattern[i][j]]);
                }
            }
        }
    }

    @Override public int[][] next() {
        // advance the world to the next generation
        nextGeneration();

        int[][] worldArray = new int[size.getHeight()][size.getWidth()];

        Predicate<Coordinate> insideWorldArray =
            (coordinate) -> coordinate.getX() >= 0 && coordinate.getX() < size.getWidth()
                && coordinate.getY() >= 0 && coordinate.getY() < size.getHeight();

        world.keySet().parallelStream().forEach(coordinate -> {
            if (insideWorldArray.test(coordinate)) {
                worldArray[coordinate.getX()][coordinate.getY()] = world.get(coordinate).ordinal();
            }
        });

        return worldArray;
    }

    public void nextGeneration() {
        Map<Coordinate, CellState> nextGeneration = new HashMap<>();

        // find the dead neighbours of every cell
        Map<Coordinate, CellState> deadCells = new HashMap<>();

        // for every live cell, add the neighbours as dead cells only if a living cell in that
        // coordinate doesn't already exit
        world.keySet().parallelStream().forEach(
            liveCellCoordinate -> neighbourCoordinatesMask.parallelStream()
                .forEach(maskCoordinate -> {
                    Coordinate deadCellCoordinate = liveCellCoordinate.add(maskCoordinate);

                    if (!world.containsKey(deadCellCoordinate)) {
                        deadCells.put(deadCellCoordinate, CellState.DEAD);
                    }
                }));

        // For every live cell, find out the cell fate on the next generation.
        // If Death (a.k.a. JVM) decides to reap the cell's life, don't add the cell to the next
        // generation world.
        world.keySet().parallelStream().forEach(liveCellCoordinate -> {
            // count the live neighbours
            int neighbours = neighbourCoordinatesMask.parallelStream()
                .map(maskCoordinate -> maskCoordinate.add(liveCellCoordinate)).mapToInt(
                    neighbourCoordinate -> world.getOrDefault(neighbourCoordinate, CellState.DEAD)
                        .ordinal()).sum();

            // Add current cell only if it will stay alive in the next generation
            if (neighbours >= UNDER_POPULATION && neighbours <= OVER_POPULAITON) {
                nextGeneration.put(liveCellCoordinate, CellState.ALIVE);
            }
        });

        // For every dead cell, find out if there are enough neighbours to reproduce. If enough
        // neighbours are around, add it to the next generation world.
        deadCells.keySet().parallelStream().forEach(deadCellCoordinate -> {
            // count the live neighbours
            int neighbours = neighbourCoordinatesMask.parallelStream()
                .map(maskCoordinate -> maskCoordinate.add(deadCellCoordinate)).mapToInt(
                    neighbourCoordinate -> world.getOrDefault(neighbourCoordinate, CellState.DEAD)
                        .ordinal()).sum();

            if (neighbours == REPRODUCTION) {
                nextGeneration.put(deadCellCoordinate, CellState.ALIVE);
            }
        });

        // advance the world to the next generation
        world = nextGeneration;
    }

    private void clearWorld() {
        world = new HashMap<>();
    }

    @Override public String toString() {
        return "";
    }
}

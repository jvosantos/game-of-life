package com.jvosantos.games.gameoflife;

import org.junit.Assert;
import org.junit.Test;

public abstract class GameOfLifeTest {

    protected abstract GameOfLife createGame();

    @Test public void testBorderBlinker() throws Exception {
        int[][] borderHorizontalBlinker = {{1, 1, 1}, {0, 0, 0}, {0, 0, 0}};
        int[][] borderVerticalBlinker = {{0, 1, 0}, {0, 1, 0}, {0, 0, 0}};

        GameOfLife gameOfLife = createGame();

        gameOfLife.seed(borderHorizontalBlinker);

        Assert.assertArrayEquals(borderVerticalBlinker, gameOfLife.next());
        Assert.assertArrayEquals(borderHorizontalBlinker, gameOfLife.next());
        Assert.assertArrayEquals(borderVerticalBlinker, gameOfLife.next());
        Assert.assertArrayEquals(borderHorizontalBlinker, gameOfLife.next());
    }

    @Test public void testBlinker() throws Exception {
        int[][] blinkerVertical = {{0, 1, 0}, {0, 1, 0}, {0, 1, 0}};
        int[][] blinkerHorizontal = {{0, 0, 0}, {1, 1, 1}, {0, 0, 0}};

        GameOfLife gameOfLife = createGame();

        gameOfLife.seed(blinkerVertical);

        Assert.assertArrayEquals(blinkerHorizontal, gameOfLife.next());

        Assert.assertArrayEquals(blinkerVertical, gameOfLife.next());

        Assert.assertArrayEquals(blinkerHorizontal, gameOfLife.next());

        Assert.assertArrayEquals(blinkerVertical, gameOfLife.next());
    }

    @Test public void testPreBlock() throws Exception {
        int[][] preBlockTopLeft = {{0, 0, 0, 0}, {0, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        int[][] preBlockTopRight = {{0, 0, 0, 0}, {0, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        int[][] preBlockBottomLeft = {{0, 0, 0, 0}, {0, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}};
        int[][] preBlockBottomRight = {{0, 0, 0, 0}, {0, 1, 1, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}};
        int[][] block = {{0, 0, 0, 0}, {0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};

        GameOfLife gameOfLife = createGame();

        gameOfLife.seed(preBlockTopLeft);
        Assert.assertArrayEquals(block, gameOfLife.next());
        Assert.assertArrayEquals(block, gameOfLife.next());

        gameOfLife.seed(preBlockTopRight);
        Assert.assertArrayEquals(block, gameOfLife.next());
        Assert.assertArrayEquals(block, gameOfLife.next());

        gameOfLife.seed(preBlockBottomLeft);
        Assert.assertArrayEquals(block, gameOfLife.next());
        Assert.assertArrayEquals(block, gameOfLife.next());

        gameOfLife.seed(preBlockBottomRight);
        Assert.assertArrayEquals(block, gameOfLife.next());
        Assert.assertArrayEquals(block, gameOfLife.next());
    }

    @Test public void testStillLife() throws Exception {
        int[][] boat =
            {{0, 0, 0, 0, 0}, {0, 1, 1, 0, 0}, {0, 1, 0, 1, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}};

        GameOfLife gameOfLife = createGame();

        gameOfLife.seed(boat);
        Assert.assertArrayEquals(boat, gameOfLife.next());
        Assert.assertArrayEquals(boat, gameOfLife.next());
    }

    @Test public void testDoomedLife() throws Exception {
        int[][] fiveCells =
            {{1, 0, 0, 0, 0}, {0, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};
        int[][] threeCells =
            {{0, 0, 0, 0, 0}, {0, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 0}};
        int[][] oneCell =
            {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        int[][] death =
            {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};

        GameOfLife gameOfLife = createGame();

        gameOfLife.seed(fiveCells);
        Assert.assertArrayEquals(threeCells, gameOfLife.next());
        Assert.assertArrayEquals(oneCell, gameOfLife.next());
        Assert.assertArrayEquals(death, gameOfLife.next());
        Assert.assertArrayEquals(death, gameOfLife.next());
    }

    @Test public void testNousOscillator() {
        int[][] nousOscillator1 = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

        int[][] nousOscillator2 = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

        GameOfLife gameOfLife = createGame();

        gameOfLife.seed(nousOscillator1);

        Assert.assertArrayEquals(nousOscillator2, gameOfLife.next());
        Assert.assertArrayEquals(nousOscillator1, gameOfLife.next());
    }

}

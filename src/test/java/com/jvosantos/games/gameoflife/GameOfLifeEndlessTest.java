package com.jvosantos.games.gameoflife;

public class GameOfLifeEndlessTest extends GameOfLifeTest {
    @Override
    protected GameOfLife createGame() {
        return new GameOfLifeEndless();
    }
}

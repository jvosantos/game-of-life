package com.jvosantos.games.gameoflife;

public class GameOfLifeConstrainedTest extends GameOfLifeTest {

    public GameOfLife createGame() {
        return new GameOfLifeConstrained();
    }

}

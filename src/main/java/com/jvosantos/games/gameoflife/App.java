package com.jvosantos.games.gameoflife;

import java.util.Arrays;

public class App {
    private static final String ALIVE = " ";
    private static final String DEAD = "#";

    public static void main( String[] args ) throws InterruptedException {
        int[][] board = LifePatterns.BLINKER;
        GameOfLife gameOfLife = new GameOfLifeConstrained();

        gameOfLife.seed(board);

        int i = 0;
        while(true) {
            board = gameOfLife.next();
            System.out.println("============  Game of life  ============");
            System.out.println(boardToString(board));
            Thread.sleep(200);
            if(i++ == 1) break;
        }
    }

    public static String boardToString(int[][] board) {
        final StringBuilder sb = new StringBuilder();
        Arrays.stream(board).forEach(
            ints -> {
                Arrays.stream(ints).forEach(i -> sb.append(i==0?DEAD:ALIVE));
                sb.append('\n');}
        );
        return sb.toString();
    }
}

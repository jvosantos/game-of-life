package com.jvosantos.games.gameoflife;

public class Utils {
    public static boolean isRectangular(int[][] a) {
        for (int i = 0; i < a.length-1; i++) {
            if(a[i].length != a[i+1].length) {
                return false;
            }
        }

        return true;
    }

    public static int getMaxColumns(int[][] a) {
        int max = 0;

        for(int i = 0; i < a.length; i++) {
            if(a[i].length > max) {
                max = a[i].length;
            }
        }

        return max;
    }
}

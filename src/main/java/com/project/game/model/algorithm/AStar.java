package com.project.game.model.algorithm;

public class AStar {
    private static AStar aStar;

    private AStar() {}

    public static AStar getInstance() {
        if(aStar == null) {
            aStar = new AStar();
        }
        return aStar;
    }
}

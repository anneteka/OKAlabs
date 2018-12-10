package com.epam.mentoring.location;

import com.epam.mentoring.utils.PropertiesOfTheWorld.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, описывающий ячейку лабиринта
 */
public class NodeOfLabirynth {

    private int y;
    private int x;
    /**
     * Список возможных для данного узла направлений движения
     */
    private List<Direction> possibleDirections;
    private boolean isStart;
    private boolean isExit;
    private boolean isWalkable;

    public NodeOfLabirynth(int y, int x, boolean isStart, boolean isExit, boolean isWalkable) {
        this.y = y;
        this.x = x;
        this.isStart = isStart;
        this.isExit = isExit;
        this.isWalkable = isWalkable;
        possibleDirections = new ArrayList<Direction>();
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public List<Direction> getPossibleDirections() {
        return possibleDirections;
    }

    public void setPossibleDirections(List<Direction> possibleDirections) {
        this.possibleDirections = possibleDirections;
    }

    public boolean isStart() {
        return isStart;
    }

    public boolean isExit() {
        return isExit;
    }

    public boolean isWalkable() {
        return isWalkable;
    }
}
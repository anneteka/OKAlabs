package com.epam.mentoring.utils;

import com.epam.mentoring.location.InfoAbouLabyrinth;
import com.epam.mentoring.location.NodeOfLabirynth;
import com.epam.mentoring.utils.PropertiesOfTheWorld.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Предоставляет методы для взаимодействия с лабиринтом
 */
public class HelpLocation {

    /**
     * Соответствие направлений текстовым командам движения
     */
    public static final Map<Direction, String> DIRECTION_COMMANDS = new HashMap<Direction, String>() {{
        put(Direction.Down, "s");
        put(Direction.Left, "a");
        put(Direction.Right, "d");
        put(Direction.Up, "w");
    }};

    /**
     * Соответствие текстовых команд направлениям движения
     */
    public static final Map<String, Direction> COMMAND_DIRECTIONS = new HashMap<String, Direction>() {{
        put("s", Direction.Down);
        put("a", Direction.Left);
        put("d", Direction.Right);
        put("w", Direction.Up);
    }};

    private InfoAbouLabyrinth infoAbouLabyrinth;
    /**
     * Список переходов с данного узла, которые уже были выполнены ранее
     */
    private Map<String, List<Direction>> exploredDirections;

    public HelpLocation(InfoAbouLabyrinth infoAbouLabyrinth) {
        this.infoAbouLabyrinth = infoAbouLabyrinth;
        this.exploredDirections = new HashMap<String, List<Direction>>();
    }

    public InfoAbouLabyrinth getLabyrinth() {
        return infoAbouLabyrinth;
    }

    public List<Direction> getExploredDirections(String nodeCoordinates) {
        List<Direction> exploredDirections = this.exploredDirections.get(nodeCoordinates);
        if (exploredDirections == null) {
            exploredDirections = new ArrayList<Direction>();
        }
        return exploredDirections;
    }

    public void addExploredDirection(String nodeCoordinates, Direction exploredDirection) {
        List<Direction> exploredDirections = this.exploredDirections.get(nodeCoordinates);
        if (exploredDirections == null) {
            exploredDirections = new ArrayList<Direction>();
        }
        exploredDirections.add(exploredDirection);
        this.exploredDirections.put(nodeCoordinates, exploredDirections);
    }

    public void clearExploredDirections() {
        exploredDirections.clear();
    }

    public boolean isDirectionMovable(NodeOfLabirynth currentNode, Direction direction) {
        return currentNode.getPossibleDirections().contains(direction);
    }

    public NodeOfLabirynth changeLocation(NodeOfLabirynth currentNode, Direction direction) {
        if (isDirectionMovable(currentNode, direction)) {
            return infoAbouLabyrinth.changeLocation(currentNode, direction);
        }
        return currentNode;
    }

    /**
     * Изменяем местоположение в лабиринте, последовательно выполнив ряд перемещений по нему
     *
     * @param currentNode текущая клетка лабиринта
     * @param path        путь в лабиринте, записанный в виде последовательности шагов
     * @return
     */
    public NodeOfLabirynth solvePath(NodeOfLabirynth currentNode, StringBuilder path) {
        char[] stepSeq = path.toString().toCharArray();
        for (char step : stepSeq) {
            currentNode = changeLocation(currentNode, COMMAND_DIRECTIONS.get(String.valueOf(step)));
        }
        return currentNode;
    }

    public String getLocationInfo(NodeOfLabirynth currentNode) {
        return "X=" + currentNode.getX() + ", Y=" + currentNode.getY();
    }

}
package com.epam.mentoring.location;

import com.epam.mentoring.utils.PropertiesOfTheWorld;
import com.epam.mentoring.utils.PropertiesOfTheWorld.Direction;

import java.util.List;

/**
 * Хранит информацию о лабиринте
 */
public class InfoAbouLabyrinth {

    NodeOfLabirynth[][] labyrinthNodes;
    NodeOfLabirynth start;
    NodeOfLabirynth exit;

    public InfoAbouLabyrinth(NodeOfLabirynth[][] labyrinthNodes) {
        this.labyrinthNodes = findPossibleDirections(labyrinthNodes);
        this.start = findStart(labyrinthNodes);
        this.exit = findExit(labyrinthNodes);
    }

    public NodeOfLabirynth[][] getLabyrinthNodes() {
        return labyrinthNodes;
    }

    public NodeOfLabirynth getStart() {
        return start;
    }

    public NodeOfLabirynth getExit() {
        return exit;
    }

    /**
     * Делаем переход на соседнюю клетку лабиринта и возвращаем её в качестве результата работы метода
     *
     * @param currentNode
     * @param direction
     * @return
     */
    public NodeOfLabirynth changeLocation(NodeOfLabirynth currentNode, Direction direction) {
        NodeOfLabirynth newNode = currentNode;
        switch (direction) {
            case Down:
                newNode = labyrinthNodes[currentNode.getY() + 1][currentNode.getX()];
                break;
            case Left:
                newNode = labyrinthNodes[currentNode.getY()][currentNode.getX() - 1];
                break;
            case Right:
                newNode = labyrinthNodes[currentNode.getY()][currentNode.getX() + 1];
                break;
            case Up:
                newNode = labyrinthNodes[currentNode.getY() - 1][currentNode.getX()];
                break;
        }
        return newNode;
    }

    /**
     * Для каждой клетки лабиринта ищем возможные направления движения
     *
     * @param labyrinth
     * @return лабиринт, клетки которого содержат список допустимых направлений перемещения
     */
    private static NodeOfLabirynth[][] findPossibleDirections(NodeOfLabirynth[][] labyrinth) {

        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth[i].length; j++) {
                NodeOfLabirynth nodeOfLabirynth = labyrinth[i][j];
                if (nodeOfLabirynth.isWalkable()) {
                    if (i > 0) {
                        nodeOfLabirynth.setPossibleDirections(checkDirection(labyrinth[i - 1][j], PropertiesOfTheWorld.Direction.Up, nodeOfLabirynth.getPossibleDirections()));
                    }
                    if (i < labyrinth.length - 1) {
                        nodeOfLabirynth.setPossibleDirections(checkDirection(labyrinth[i + 1][j], PropertiesOfTheWorld.Direction.Down, nodeOfLabirynth.getPossibleDirections()));
                    }
                    if (j > 0) {
                        nodeOfLabirynth.setPossibleDirections(checkDirection(labyrinth[i][j - 1], PropertiesOfTheWorld.Direction.Left, nodeOfLabirynth.getPossibleDirections()));
                    }
                    if (j < labyrinth[i].length - 1) {
                        nodeOfLabirynth.setPossibleDirections(checkDirection(labyrinth[i][j + 1], PropertiesOfTheWorld.Direction.Right, nodeOfLabirynth.getPossibleDirections()));
                    }
                }
            }
        }

        return labyrinth;
    }

    /**
     * Проверяем, можно ли перейти из узла X в узел Y, и если можно -
     * добавляем соответствующее направление в список возможных для узла X
     *
     * @param nodeOfLabirynth               - узел Y
     * @param direction          - направление, в котором надо двигаться из узла X, чтобы попасть в узел Y
     * @param possibleDirections - список возможных направлений движения из узла X
     * @return
     */
    private static List<Direction> checkDirection(NodeOfLabirynth nodeOfLabirynth, PropertiesOfTheWorld.Direction direction, List<PropertiesOfTheWorld.Direction> possibleDirections) {
        if (nodeOfLabirynth.isWalkable()) {
            possibleDirections.add(direction);
        }
        return possibleDirections;
    }

    /**
     * Ищем точку входа в лабиринт
     *
     * @param labyrinth
     * @return
     */
    private static NodeOfLabirynth findStart(NodeOfLabirynth[][] labyrinth) {
        for (NodeOfLabirynth[] nodes : labyrinth) {
            for (NodeOfLabirynth nodeOfLabirynth : nodes) {
                if (nodeOfLabirynth.isStart()) return nodeOfLabirynth;
            }
        }
        return null;
    }

    /**
     * Ищем точку выхода из лабиринта
     *
     * @param labyrinth
     * @return
     */
    private static NodeOfLabirynth findExit(NodeOfLabirynth[][] labyrinth) {
        for (NodeOfLabirynth[] nodes : labyrinth) {
            for (NodeOfLabirynth nodeOfLabirynth : nodes) {
                if (nodeOfLabirynth.isExit()) return nodeOfLabirynth;
            }
        }
        return null;
    }

}

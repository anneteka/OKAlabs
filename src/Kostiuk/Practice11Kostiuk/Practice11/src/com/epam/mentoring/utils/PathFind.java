package com.epam.mentoring.utils;

import com.epam.mentoring.location.InfoAbouLabyrinth;
import com.epam.mentoring.location.NodeOfLabirynth;
import com.epam.mentoring.utils.PropertiesOfTheWorld.Direction;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PathFind {

    /**
     * Ищем путь от входа в лабиринт до выхода из него
     * Результат работы метода - последовательность текстовых команд движения
     *
     * @param helpLocation
     * @return последовательность текстовых команд, которая позволит пройти путь от входа в лабиринт до выхода из него
     */
    public static String findPath(HelpLocation helpLocation) {

        InfoAbouLabyrinth infoAbouLabyrinth = helpLocation.getLabyrinth();
        Direction[] preferableDirections = determinePreferableDirections(infoAbouLabyrinth.getStart(), infoAbouLabyrinth.getExit());

        // Используем LinkedHashMap для того, чтобы элементы хранились в порядке добавления
        Map<String, Boolean> paths = new LinkedHashMap<String, Boolean>();
        StringBuilder previousPath;
        StringBuilder currentPath = new StringBuilder();
        NodeOfLabirynth previousNode = infoAbouLabyrinth.getStart();
        NodeOfLabirynth currentNode = infoAbouLabyrinth.getStart();

        boolean pathFound = false;
        while (!pathFound) {
            previousPath = new StringBuilder(currentPath.toString());
            currentPath = choosePath(preferableDirections, helpLocation, currentNode, paths, currentPath);

            if (!currentPath.equals(previousPath)) {
                currentNode = makeStep(helpLocation, currentNode, HelpLocation.COMMAND_DIRECTIONS.get(currentPath.substring(currentPath.length() - 1)));
                if (!pathIsDeadlock(currentNode, previousNode, paths)) {
                    /**
                     * Если шаг был успешно произведён - производим переоценку приоритетов направлений.
                     * Так мы сохраним вектор движения ориентированным на выход из лабиринта
                     */
                    preferableDirections = determinePreferableDirections(currentNode, infoAbouLabyrinth.getExit());
                }
                previousNode = currentNode;
            }

            currentPath = changePathIfDeadlock(paths, currentPath);
            currentNode = helpLocation.solvePath(infoAbouLabyrinth.getStart(), currentPath);

            if (currentNode.isExit()) {
                pathFound = true;
            }
        }

        /**
         * "Заметаем следы" нашего прохода по лабиринту
         */
        helpLocation.clearExploredDirections();
        return currentPath.toString();
    }

    /**
     * Выбираем неисследованное направление движения (если таковое есть)
     * и добавляем соответствующую команду в последовательность команд перемещения
     *
     * @param preferableDirections
     * @param helpLocation
     * @param currentNode
     * @param paths
     * @param path
     * @return
     */
    private static StringBuilder choosePath(Direction[] preferableDirections, HelpLocation helpLocation, NodeOfLabirynth currentNode, Map<String, Boolean> paths, StringBuilder path) {
        List<Direction> possibleDirections = currentNode.getPossibleDirections();
        for (Direction direction : preferableDirections) {
            if (possibleDirections.contains(direction)) {

                if (helpLocation.getExploredDirections("" + currentNode.getY() + currentNode.getX()).contains(direction)) {
                    // Если такой шаг уже делался ранее, пропускаем направление
                    continue;
                } else if (!currentNode.isStart()) {
                    // Если движение в данном направлении приведёт к шагу назад, пропускаем направление
                    if (path.lastIndexOf((HelpLocation.DIRECTION_COMMANDS.get(getOppositeDirection(direction)))) == path.length() - 1) {
                        continue;
                    }
                }

                path.append(HelpLocation.DIRECTION_COMMANDS.get(direction));
                paths.put(path.toString(), true);
                break;
            }
        }
        return path;
    }

    /**
     * Делаем очередной шаг
     *
     * @param helpLocation хранит информацию о лабиринте и предоставляет методы для взаимодействия с ним
     * @param currentNode    текущая клетка лабиринта
     * @param direction      текущая последовательность команд перемещения
     * @return
     */
    private static NodeOfLabirynth makeStep(HelpLocation helpLocation, NodeOfLabirynth currentNode, Direction direction) {
        helpLocation.addExploredDirection("" + currentNode.getY() + currentNode.getX(), direction);
        return helpLocation.changeLocation(currentNode, direction);
    }

    /**
     * Если путь оказался тупиковым (с текущей клетки после анализа ситуации не было сделано шага),
     * помечаем его как ошибочный
     *
     * @param currentNode  текущая клетка лабиринта
     * @param previousNode предыдущая клетка лабиринта
     * @param paths        список проверенных путей
     * @return
     */
    private static boolean pathIsDeadlock(NodeOfLabirynth currentNode, NodeOfLabirynth previousNode, Map<String, Boolean> paths) {
        if (currentNode.getY() == previousNode.getY() &&
                currentNode.getX() == previousNode.getX()) {
            Object[] pathSet = paths.keySet().toArray();
            for (int i = pathSet.length - 1; i >= 0; i--) {
                if (paths.get(pathSet[i]) == true) {
                    paths.put(pathSet[i].toString(), false);
                    break;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Если текущий путь помечен как ошибочный, меняем его на последний неошибочный
     *
     * @param paths список проверенных путей
     * @param path  текущий путь
     * @return
     */
    private static StringBuilder changePathIfDeadlock(Map<String, Boolean> paths, StringBuilder path) {
        String stepSeq = path.toString();
        if (path.length() > 0 && paths.get(path.toString()) == false) {
            Object[] pathSet = paths.keySet().toArray();
            boolean pathExists = false;
            for (int i = pathSet.length - 1; i >= 0; i--) {
                if (paths.get(pathSet[i]) == true) {
                    pathExists = true;
                    stepSeq = pathSet[i].toString();
                    break;
                }
            }
            if (!pathExists) {
                stepSeq = "";
            }
        }
        return new StringBuilder(stepSeq);
    }

    /**
     * Определяем направление, в котором нужно двигаться, чтобы достичь точки назначения
     *
     * @param node1 исходная точка
     * @param node2 точка назначения
     * @return упорядоченный по приоритету список направлений движения
     */
    private static Direction[] determinePreferableDirections(NodeOfLabirynth node1, NodeOfLabirynth node2) {

        int xDelta = node2.getX() - node1.getX();
        int yDelta = -(node2.getY() - node1.getY());
        boolean dXGreaterThandY = Math.abs(xDelta) > Math.abs(yDelta);
        Direction[] preferableDirections = new Direction[4];

        if (dXGreaterThandY) {
            if (xDelta > 0) {
                preferableDirections[0] = Direction.Right;
                preferableDirections[3] = Direction.Left;
            } else {
                preferableDirections[0] = Direction.Left;
                preferableDirections[3] = Direction.Right;
            }
            if (yDelta > 0) {
                preferableDirections[1] = Direction.Up;
                preferableDirections[2] = Direction.Down;
            } else {
                preferableDirections[1] = Direction.Down;
                preferableDirections[2] = Direction.Up;
            }
        } else {
            if (yDelta > 0) {
                preferableDirections[0] = Direction.Up;
                preferableDirections[3] = Direction.Down;
            } else {
                preferableDirections[0] = Direction.Down;
                preferableDirections[3] = Direction.Up;
            }
            if (xDelta > 0) {
                preferableDirections[1] = Direction.Right;
                preferableDirections[2] = Direction.Left;
            } else {
                preferableDirections[1] = Direction.Left;
                preferableDirections[2] = Direction.Right;
            }
        }

        return preferableDirections;
    }

    /**
     * Определяет направление движения, противоположное указанному
     *
     * @param direction указанное направление
     * @return противоположное направление
     */
    private static Direction getOppositeDirection(Direction direction) {
        Direction oppositeDirection = null;
        switch (direction) {
            case Down:
                oppositeDirection = Direction.Up;
                break;
            case Left:
                oppositeDirection = Direction.Right;
                break;
            case Right:
                oppositeDirection = Direction.Left;
                break;
            case Up:
                oppositeDirection = Direction.Down;
                break;
        }
        return oppositeDirection;
    }

}

package com.epam.mentoring.utils;

import com.epam.mentoring.location.InfoAbouLabyrinth;
import com.epam.mentoring.location.NodeOfLabirynth;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilsLabyrinth {

    private static Logger logger = Logger.getLogger(UtilsLabyrinth.class.getName());

    /**
     * Функция, формирующая лабиринт из переданного на вход списка строк
     *
     * @param labyrinthRows горизонтальные линии, образующие карту лабиринта
     * @return
     */
    public static InfoAbouLabyrinth createLabyrinth(List<String> labyrinthRows) {

        int height = labyrinthRows.size();
        int width = labyrinthRows.get(0).length();
        NodeOfLabirynth[][] labyrinthNodes = new NodeOfLabirynth[height][width];

        for (int i = 0; i < height; i++) {
            String currentLine = labyrinthRows.get(i);
            for (int j = 0; j < width; j++) {

                boolean isStart = false;
                boolean isExit = false;
                boolean isWalkable = false;

                switch (currentLine.charAt(j)) {
                    case '0':
                        isWalkable = true;
                        break;
                    case 'I':
                        isStart = true;
                        isWalkable = true;
                        break;
                    case 'X':
                        isExit = true;
                        isWalkable = true;
                        break;
                }

                labyrinthNodes[i][j] = new NodeOfLabirynth(i, j, isStart, isExit, isWalkable);
            }
        }

        return new InfoAbouLabyrinth(labyrinthNodes);
    }

    /**
     * Метод нужен для визуализации местоположения утки в лабиринте
     *
     * @param helpLocation
     * @param currentNode
     */
    public static void printLabyrinth(HelpLocation helpLocation, NodeOfLabirynth currentNode) {
        InfoAbouLabyrinth infoAbouLabyrinth = helpLocation.getLabyrinth();
        NodeOfLabirynth[][] labyrinthNodes = infoAbouLabyrinth.getLabyrinthNodes();
        StringBuilder builder = new StringBuilder("\n------------------------------\n");
        for (int i = 0; i < labyrinthNodes.length; i++) {
            for (int j = 0; j < labyrinthNodes[i].length; j++) {
                if (currentNode.getX() == j && currentNode.getY() == i) {
                    builder.append("D   ");
                } else if (labyrinthNodes[i][j].isStart()) {
                    builder.append("I   ");
                } else if (labyrinthNodes[i][j].isExit()) {
                    builder.append("X   ");
                } else if (labyrinthNodes[i][j].isWalkable()) {
                    builder.append("0   ");
                } else {
                    builder.append("1   ");
                }
            }
            builder.append("\n");
        }
        builder.append("------------------------------");

        logger.log(Level.INFO, builder.toString());
    }

}

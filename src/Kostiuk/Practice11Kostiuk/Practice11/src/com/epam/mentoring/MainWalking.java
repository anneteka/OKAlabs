package com.epam.mentoring;

import com.epam.mentoring.creatures.duck.DuckAbstract;
import com.epam.mentoring.creatures.duck.DuckLive;
import com.epam.mentoring.location.InfoAbouLabyrinth;
import com.epam.mentoring.utils.UtilsLabyrinth;
import com.epam.mentoring.utils.HelpLocation;
import com.epam.mentoring.utils.PathFind;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainWalking {

    public static void main(String[] args) {

        System.out.println("Введіть шлях до файлу лабіринту:");
        Scanner scanner = new Scanner(System.in);
        File labyrinthMap = new File(scanner.nextLine());

        List<String> lines = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(labyrinthMap));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        InfoAbouLabyrinth infoAbouLabyrinth = UtilsLabyrinth.createLabyrinth(lines);
        HelpLocation helpLocation = new HelpLocation(infoAbouLabyrinth);
        DuckAbstract duck = new DuckLive(infoAbouLabyrinth.getStart());

        System.out.println(PathFind.findPath(helpLocation));

        while (true) {
            for (char step : scanner.nextLine().toCharArray()) {
                duck.walk(helpLocation, HelpLocation.COMMAND_DIRECTIONS.get(String.valueOf(step)));
                UtilsLabyrinth.printLabyrinth(helpLocation, duck.getCurrentNode());
            }
        }

    }

}

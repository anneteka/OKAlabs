package com.epam.mentoring;

import com.epam.mentoring.location.Labyrinth;
import com.epam.mentoring.utils.LabyrinthUtils;
import com.epam.mentoring.utils.LocationHelper;
import com.epam.mentoring.utils.PathFinder;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PathFinderTest {

    private Map<Labyrinth, String> labyrinthPaths = new LinkedHashMap<Labyrinth, String>();

    @Before
    public void initObject() {
        List<String> paths = new ArrayList<String>();
        paths.add("ssssddwwddsssdsddwwawwddsdsddwwawwaaa");
        paths.add("ssdssddwwwwwddddsddsssasssaaa");
        paths.add("saaassdssassddddssaaaawwwwdwwawwdddwwdddddddsdsddssassasdssaaaa");
        paths.add("ssdsssssssddwwwdddwwdddwwddsssdddsawaawwwaawwdddwasawddwwddsssssa");
        paths.add("sssasdwddddwwdsasdwwasdsdsddddsdsddwwdwwwwwaaawwawaaasdwwaaaaasaaawaassdsd");

        for (int i = 0; i < 5; i++) {
            File labyrinthMap = new File("D:\\Mentoring\\Part 2\\OO Design Principles\\Duck\\resources\\lab" + i + ".txt");
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
            labyrinthPaths.put(LabyrinthUtils.createLabyrinth(lines), paths.get(i));
        }
    }

    @Test
    public void testPathFinder() {
        for (Map.Entry<Labyrinth, String> entry : labyrinthPaths.entrySet()) {
            assertEquals(entry.getValue(), PathFinder.findPath(new LocationHelper(entry.getKey())));
        }
    }

}

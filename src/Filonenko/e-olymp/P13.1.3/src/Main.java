import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Solver solver = new Solver(scanner);
        System.out.println(solver.sum);
    }
}

class Solver {
    Town[] towns;
    double sum = 0;

    public Solver(Scanner scanner) {
        initTowns(scanner);
        double currentDistance = 0;
        towns[1].minDistance = 0;
        Town minDistanceTown = null;
        for (int i = 0; i < towns.length; i++) {
            minDistanceTown = countMinDistanceTown();
            minDistanceTown.visited = true;
            if(minDistanceTown.nearestTown != null) {
                currentDistance += minDistanceTown.distanceTo(minDistanceTown.nearestTown);
            }
            assignMinDistance(minDistanceTown);;
        }
        this.sum = currentDistance;
    }

    void initTowns(Scanner scanner) {
        int numberOfTowns = scanner.nextInt();
        towns = new Town[numberOfTowns];
        for (int i = 0; i < numberOfTowns; i++) {
            towns[i] = new Town(scanner.nextInt(), scanner.nextInt());
        }
    }

    Town countMinDistanceTown() {
        Town minDistanceTown = null;
        for(Town town: towns) {
            if(!town.visited) {
                if(minDistanceTown == null || town.minDistance < minDistanceTown.minDistance) {
                    minDistanceTown = town;
                }
            }
        }
        return minDistanceTown;
    }

    void assignMinDistance(Town minDistanceTown) {
        for (int i = 0; i < towns.length; i++) {
            double distance = minDistanceTown.distanceTo(towns[i]);
            if (!towns[i].visited && distance < towns[i].minDistance) {
                towns[i].minDistance = distance;
                towns[i].nearestTown = minDistanceTown;
            }
        }
    }
}

class Town {
    static int lastID = 0;
    int id;
    int x;
    int y;
    double minDistance = 100000; // ?? distance to the nearest town? why 0x3F??
    Town nearestTown = null; // ?? what is end distance ?? the distance will be used in calculations ??
    boolean visited = false;
    public Town(int x, int y) {
        this.x = x;
        this.y = y;
        this.id = lastID++;
    }
    public double distanceTo(Town that) {
        double x = this.x - that.x;
        double y = this.y - that.y;
        return Math.sqrt(x*x + y*y);
    }
}

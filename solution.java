//https://stackoverflow.com/questions/19648240/the-best-way-to-print-a-java-2d-array
import java.util.Arrays;

public class solution {
    // The point must be the closest to the goal (move towards it) & The safest (lowest risk value)


    public static int[][] mappedGrid = inputGenerator.getTestInput();

    public static void main(String[] args) {
        // int[][] mappedGrid = inputGenerator.createInput();
        System.out.println(Arrays.deepToString(mappedGrid).replace("], ","]\n"));
        int[] coordinatePt = new int[6]; // A list of the calculated risk vals around a point
        int[] riskVals = new int[6]; // The coordinates that corespond with each risk val. Easier to handle if I just do 2 seperate lists.
        int currentCoordinateIdx = 0; // Where you are in the lists
        int firstCoord = 1; // X val
        int secondCoord = 1; // Y val

        while (firstCoord != 10 & secondCoord != 10) { // While the "elf" is not at the "factory" (1,1 to 10,10)
            for (int i = -1; i < 2; i ++) { // Check surrounding points
                for (int j = -1; j < 2; j ++) {
                    if (i != 0 | j != 0 && mappedGrid[firstCoord + i][secondCoord + j] != 0) { // Make sure it's not an edge case ("0") or the current point (i.e [1,1])
                        riskVals[currentCoordinateIdx] = checkRisk(firstCoord + i, secondCoord + j);
                        coordinatePt[currentCoordinateIdx] = mappedGrid[firstCoord + i][secondCoord + j];
                        currentCoordinateIdx++;
                    }
                }
            } 
        }
    }
            

    public static int checkRisk(int i, int j) { // Calculates the risk value of the point and the surrounding area. Helps prevents a 'Greedy' search.
        int totalRisk = mappedGrid[i][j];

        for (int idx1 = -1; idx1 < 2; idx1 ++) { // Check surrounding points
            for (int idx2 = -1; idx2 < 2; idx2 ++) {
                if ((idx1 != 0 | idx2 != 0) && mappedGrid[i][j] != 0) {
                    totalRisk += mappedGrid[i + idx1][j + idx2];
                }
            }
        } 

        return totalRisk;
    }

    public static double getDistance(int i, int j) { // Find distance between point and end goal
        double distance = Math.sqrt(Math.pow((10 - i), 2) + Math.pow((10 - j), 2));
        System.out.println(distance);
        return distance;
    }
}

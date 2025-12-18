//https://stackoverflow.com/questions/19648240/the-best-way-to-print-a-java-2d-array
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The next point in the path must be CLOSEST to the end goal (move towards it) and the SAFEST (lowest risk value)
 * It cannot move diagonally and should not include the "fences" (zeros) in the risk calculation.
 * It will always be moving towards (10,10) so it can be simplified to only check the risk of two ahead values.
 * The "risk" of a point is based on the added risk values of it's following 2 points. Those risk values are based on the same (only goes one ahead unless two points contain the same risk values)
 * This then means that decisions are based on the risk of the entire area rather than just one point. 
 */
public class solution {
    // public static int[][] mappedGrid = inputGenerator.getTestInput();
    public static int[][] mappedGrid = inputGenerator.createInput();

    public static void main(String[] args) {

        ArrayList<String> coordinatePt = new ArrayList<>(); // The coordinates that corespond with each risk val
        ArrayList<Integer> riskVals = new ArrayList<>(); // A list of the calculated risk vals around a point 
        ArrayList<Double> distVals = new ArrayList<>(); // The distance from the end point for each coordinate

        int currentCoordinateIdx = 0; // Where you are in the lists
        int firstCoord = 1; // X val
        int secondCoord = 1; // Y val
        String coordinates = "11"; // All of the traveled to points

        while (firstCoord < 10 | secondCoord < 10) { // While the "elf" is not at the "factory" (1,1 to 10,10)
            for (int i = -1; i < 2; i ++) { // Check surrounding points
                for (int j = -1; j < 2; j ++) {
                    if (i != 0 | j != 0) { // Make sure it's not the current point (i.e [1,1])
                        if ((i != -1 | j != -1) && (i != -1 | j != 1) && (i != 1 | j != -1) && (i != 1 | j != 1) && getDistance(firstCoord, secondCoord) > getDistance(firstCoord + i, secondCoord + j)) { // Ensures it's not looking at diagonals, as it cannot move that direction.
                            if ((firstCoord + i) > 0 && (secondCoord + j) > 0 & (firstCoord + i) < 11 && (secondCoord + j) < 11) {
                                riskVals.add(checkRisk(firstCoord + i, secondCoord + j));
                                distVals.add(getDistance(firstCoord + i, secondCoord + j));
                                coordinatePt.add((firstCoord + i)+","+(secondCoord + j));
                                currentCoordinateIdx++;
                            }
                        }
                    }
                }
            }
            
            currentCoordinateIdx = 0;
            for (int i = 0; i < coordinatePt.size(); i ++) {
                if (distVals.get(i) < getDistance(firstCoord, secondCoord)) {
                    if (riskVals.get(i) < riskVals.get(currentCoordinateIdx) && distVals.get(i) < distVals.get(currentCoordinateIdx)) {
                        currentCoordinateIdx = i;
                    } else if ((riskVals.get(i) == riskVals.get(currentCoordinateIdx)) | (distVals.get(i) == distVals.get(currentCoordinateIdx)) ) {
                        int iFirst = Integer.parseInt(coordinatePt.get(i).substring(0,coordinatePt.get(i).indexOf(",")));
                        int iSecond = Integer.parseInt(coordinatePt.get(i).substring(coordinatePt.get(i).indexOf(",")+1));

                        int currentFirst = Integer.parseInt(coordinatePt.get(currentCoordinateIdx).substring(0,coordinatePt.get(currentCoordinateIdx).indexOf(",")));
                        int currentSecond = Integer.parseInt(coordinatePt.get(currentCoordinateIdx).substring(coordinatePt.get(currentCoordinateIdx).indexOf(",")+1));

                        double riskINew = checkRisk(iFirst + 1, iSecond) + checkRisk(currentFirst, currentSecond + 1);
                        double riskCurrentNew = checkRisk(currentFirst + 1, currentSecond) + checkRisk(currentFirst, currentSecond + 1);
                        if (riskINew < riskCurrentNew) {
                            currentCoordinateIdx = i;
                        }
                    }
                }
            }

            firstCoord = Integer.parseInt(coordinatePt.get(currentCoordinateIdx).substring(0,coordinatePt.get(currentCoordinateIdx).indexOf(",")));
            secondCoord = Integer.parseInt(coordinatePt.get(currentCoordinateIdx).substring(coordinatePt.get(currentCoordinateIdx).indexOf(",")+1));
            coordinates += firstCoord + "" + secondCoord;

            currentCoordinateIdx = 0;
            coordinatePt = new ArrayList<>();
            riskVals = new ArrayList<>();
            distVals = new ArrayList<>();
        }

        
        System.out.println(Arrays.deepToString(mappedGrid).replace("], ","]\n"));
        System.out.println(coordinates);
    }
            

    /**
     * Default risk is set to incredibly high number, so inputs that are walls or should not be moved to are automatically a higher risk than the algorithm is willing to take.
     * If it is NOT an edge case, then it adds the risk of the current position and the positions to the right and bottom of it. This generates the risk value for the area.
     * Returns this value.
     * The idea is to help prevent a greedy search by making it look ahead of it's moves.
     * @param i First index (x value)
     * @param j Second index (y value)
     * @return
     */
    public static int checkRisk(int i, int j) { 
        int totalRisk = 10000;
        if (i != 0 && j != 0 && i != 11 && j != 11) {
            totalRisk = mappedGrid[i][j];
            totalRisk += mappedGrid[i+1][j];
            totalRisk += mappedGrid[i][j+1];
        }
            return totalRisk;
    }

    /**
     * Calculates the distance away from the end goal.
     * Just uses the standard distance formula (sqrt(x2-x1)^2+(y2-y1)^2)
     * Does not round because some minor changes in distance might make the path more optimal.
     * @param i First index (x value)
     * @param j Second index (y value)
     * @return
     */
    public static double getDistance(int i, int j) {
        double distance = Math.sqrt(Math.pow((10 - i), 2) + Math.pow((10 - j), 2));
        return distance;
    }
}

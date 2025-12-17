//https://stackoverflow.com/questions/19648240/the-best-way-to-print-a-java-2d-array
import java.util.Arrays;

public class solution {
    public static void main(String[] args) {
        // int[][] mappedGrid = inputGenerator.createInput();
        int[][] mappedGrid = inputGenerator.getTestInput();
        System.out.println(Arrays.deepToString(mappedGrid).replace("], ","]\n"));
    }

    public static int checkRisk(int i, int j) {
        
        return 0;
    }
}
public class inputGenerator {

    public static int[][] createInput() {
      int[][] generatedArray = new int[12][12];
        for (int i = 0; i < generatedArray.length; i++) {
            for (int j = 0; j < generatedArray[0].length; j++) {
                if (i == 0 | j == 0 | i == 11 | j == 11) {
                    generatedArray[i][j] = 0;
                } else{
                    generatedArray[i][j] = (int)(Math.random() * (9-1 + 1) + 1);
                }
                
            }
        }
        generatedArray[1][1] = 1;
        generatedArray[10][10] = 1;
        return generatedArray;
    }

    public static int[][] getTestInput() {
        int[][] testInput = 
        {{0,0,0,0,0,0,0,0,0,0,0,0},
        {0,1,3,2,7,8,6,4,1,1,1,0},
        {0,4,4,5,7,8,1,1,2,3,4,0},
        {0,3,3,7,1,2,9,4,9,9,3,0},
        {0,8,1,8,5,4,5,8,1,2,3,0},
        {0,9,6,6,4,3,6,7,1,3,4,0},
        {0,9,7,9,1,1,3,6,2,4,6,0},
        {0,1,1,4,2,3,5,5,3,5,1,0},
        {0,3,2,1,3,6,7,4,1,5,2,0},
        {0,5,4,4,5,8,8,3,1,4,3,0},
        {0,6,7,6,6,7,1,2,3,6,1,0},
        {0,0,0,0,0,0,0,0,0,0,0,0}};

        return testInput;
    }
}
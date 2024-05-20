import java.util.Random;

public class Laplace {
    public static void main(String[] args){
        double[][] matrix = createRandomMatrix(4, 4, -100, 100);
        System.out.println();
        long startTime = System.nanoTime();
        double det = determinant(matrix);
        long endTime = System.nanoTime();
        long executionTime = (endTime - startTime);
        determinant(matrix);
        System.out.println("Time elapsed: " + executionTime/1000000000 + "." + executionTime%1000000000 + " seconds");
    }

    public static double[][] createRandomMatrix(int rows, int columns, int min, int max){
        double[][] matrix = new double[rows][columns];
        Random rand = new Random();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                matrix[i][j] = rand.nextInt((max - min) + 1) + min;
            }
        }
        return matrix;
    }

    public static int determinant(double[][] matrix){
        if(matrix.length != matrix[0].length){
            throw new IllegalArgumentException("Matrix must be square");
           // return 0;
        }else if(matrix.length == 2){
            return (int) (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]);
        }
        int ans = 0;
        for(int i = 0; i < matrix.length; i++){
            double[][] miniMatrix = new double[matrix.length - 1][matrix.length - 1];
            for(int j = 1; j < matrix.length; j++){
                int l = 0;
                for(int k = 0; k < matrix.length; k++){
                    if(k != i){
                        miniMatrix[j - 1][l] = matrix[j][k];
                        l++;
                    }
                }
            }
            if(i % 2 == 0){
                ans+= matrix[0][i] * determinant(miniMatrix);
            }else{
                ans-= matrix[0][i] * determinant(miniMatrix);
            }
        }
        return ans;
    }
}

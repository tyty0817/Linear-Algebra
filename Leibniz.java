import java.util.Random;

public class Leibniz {
    public static void main(String[] args){
        double[][] matrix = createRandomMatrix(4, 4, -100, 100);
        System.out.println();
        long startTime = System.nanoTime();
        double det = determinant(matrix);
        long endTime = System.nanoTime();
        long executionTime = (endTime - startTime);
        //System.out.println("Determinant: " + det);
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

    public static double determinant(double[][] matrix) {
        int n = matrix.length;
        if (n != matrix[0].length) {
            throw new IllegalArgumentException("Matrix must be square bro");
        }

        return leibnizDeterminant(matrix, n);
    }

    private static double leibnizDeterminant(double[][] matrix, int n) {
        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) {
            permutation[i] = i;
        }

        return calculate(matrix, permutation, 0);
    }

    private static double calculate(double[][] matrix, int[] permutation, int start) {
        int n = permutation.length;
        if (start == n - 1) {
            return get(matrix, permutation);
        } 
        else {
            double sum = 0.0;
            for (int i = start; i < n; i++) {
                swap(permutation, start, i);
                sum += calculate(matrix, permutation, start + 1);
                swap(permutation, start, i); // back
            }
            return sum;
        }
    }

    private static double get(double[][] matrix, int[] permutation) {
        int n = permutation.length;
        double product = 1.0;

        for (int i = 0; i < n; i++) {
            product *= matrix[i][permutation[i]];
        }

        return (isEven(permutation) ? 1 : -1) * product;
    }

    private static boolean isEven(int[] permutation) {
        int bbb = 0;

        int n = permutation.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (permutation[i] > permutation[j]) {
                    bbb++;
             }
            
            }
        }
        return bbb % 2 == 0;
    }

    //goes through all the combos
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

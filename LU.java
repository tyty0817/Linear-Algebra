
public class LU{
    public static void main(String[] args){
        double[][] matrix = createRandomMatrix(14, 14, -100, 100);
        System.out.println();
        long startTime = System.nanoTime();
        determinant(matrix);
        long endTime = System.nanoTime();long executionTime = (endTime - startTime);
        System.out.println("time elapsed " + executionTime/1000000000 + "." + executionTime%1000000000);
    }

    public static double[][] createRandomMatrix(int rows, int columns, int min, int max){
        double[][] matrix = new double[rows][columns];
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                matrix[i][j] = (int) (Math.random() * (max - min + 2) + min - 1);
            }
        }
        return matrix;
    }

    public static int determinant(double[][] matrix) {
        if (matrix.length != matrix[0].length) {
            System.out.println("Matrix must be square bro");
            return 0;
        }

        int n = matrix.length;
        int det = 1;
        double[][] LU = new double[n][n];

        //copy of input matrix
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, LU[i], 0, n);
        }

        // Perform LU factorization
        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                if (LU[k][k] == 0) {
                    System.out.println("Pivot is zero so it cant perform LU factorization :(");
                    return 0;
                }
                double factor = LU[i][k] / LU[k][k];
                for (int j = k + 1; j < n; j++) {
                    LU[i][j] -= factor * LU[k][j];
                }
                LU[i][k] = factor;
            }
        }

        // Calculate determinant mult diags
        for (int i = 0; i < n; i++) {
            det *= LU[i][i];
        }

        return (int) det;
    }

}


public class Strassen{
    public static void main(String[] args){
        double[][] matrix = createRandomMatrix(14, 14, -100, 100);
        System.out.println();
        long startTime = System.nanoTime();
        strassenDeterminant(matrix);
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

    // Strassen Algorithm for matrix multiplication
    public static double[][] strassenMultiply(double[][] A, double[][] B) {
        int n = A.length;
        double[][] C = new double[n][n];

        if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
        } else {
            // Divide matrices into submatrices
            double[][] A11 = new double[n / 2][n / 2];
            double[][] A12 = new double[n / 2][n / 2];
            double[][] A21 = new double[n / 2][n / 2];
            double[][] A22 = new double[n / 2][n / 2];
            double[][] B11 = new double[n / 2][n / 2];
            double[][] B12 = new double[n / 2][n / 2];
            double[][] B21 = new double[n / 2][n / 2];
            double[][] B22 = new double[n / 2][n / 2];

            // Split matrices into submatrices
            splitMatrix(A, A11, 0, 0);
            splitMatrix(A, A12, 0, n / 2);
            splitMatrix(A, A21, n / 2, 0);
            splitMatrix(A, A22, n / 2, n / 2);
            splitMatrix(B, B11, 0, 0);
            splitMatrix(B, B12, 0, n / 2);
            splitMatrix(B, B21, n / 2, 0);
            splitMatrix(B, B22, n / 2, n / 2);

            // Strassen Algorithm steps
            double[][] M1 = strassenMultiply(addMatrices(A11, A22), addMatrices(B11, B22));
            double[][] M2 = strassenMultiply(addMatrices(A21, A22), B11);
            double[][] M3 = strassenMultiply(A11, subtractMatrices(B12, B22));
            double[][] M4 = strassenMultiply(A22, subtractMatrices(B21, B11));
            double[][] M5 = strassenMultiply(addMatrices(A11, A12), B22);
            double[][] M6 = strassenMultiply(subtractMatrices(A21, A11), addMatrices(B11, B12));
            double[][] M7 = strassenMultiply(subtractMatrices(A12, A22), addMatrices(B21, B22));

            // Calculate result submatrices
            double[][] C11 = addMatrices(subtractMatrices(addMatrices(M1, M4), M5), M7);
            double[][] C12 = addMatrices(M3, M5);
            double[][] C21 = addMatrices(M2, M4);
            double[][] C22 = addMatrices(subtractMatrices(addMatrices(M1, M3), M2), M6);

            // Combine result submatrices
            combineMatrix(C11, C, 0, 0);
            combineMatrix(C12, C, 0, n / 2);
            combineMatrix(C21, C, n / 2, 0);
            combineMatrix(C22, C, n / 2, n / 2);
        }
        return C;
    }

    public static double[][] subtractMatrices(double[][] A, double[][] B) {
        int n = A.length;
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    public static void splitMatrix(double[][] P, double[][] C, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {
                C[i1][j1] = P[i2][j2];
            }
        }
    }

    public static void combineMatrix(double[][] C, double[][] P, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {
                P[i2][j2] = C[i1][j1];
            }
        }
    }
    public static double[][] addMatrices(double[][] first, double[][] second){
        if(first.length != second.length || first[0].length != second[0].length){
            System.out.println("nope");
            return null;
        }
        double[][] sum = new double[first.length][first[0].length];
        for(int i = 0; i < first.length; i++){
            for(int j = 0; j < second.length; j++){
                sum[i][j] += first[i][j]+second[i][j];
            }
        }
        return sum;
    }

    // Function to calculate determinant using Strassen Algorithm
    public static double determinantStrassen(double[][] matrix) {
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Matrix must be square");
        }
        return strassenDeterminant(matrix);
    }

    public static double strassenDeterminant(double[][] matrix) {
        int n = matrix.length;
        if (n == 1) {
            return matrix[0][0];
        } else {
            double det = 0;
            for (int j = 0; j < n; j++) {
                double[][] subMatrix = new double[n - 1][n - 1];
                for (int i = 1; i < n; i++) {
                    int k = 0;
                    for (int l = 0; l < n; l++) {
                        if (l != j) {
                            subMatrix[i - 1][k] = matrix[i][l];
                            k++;
                        }
                    }
                }
                double subDet = strassenDeterminant(subMatrix);
                det += matrix[0][j] * Math.pow(-1, j) * subDet;
            }
            return det;
        }
    }
}

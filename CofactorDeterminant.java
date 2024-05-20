public class CofactorDeterminant {
    public static void main(String[] args){
        double[][] matrix = createRandomMatrix(11, 11, -100, 100);
        System.out.println();
        long startTime = System.nanoTime();
        deteminant(matrix);
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

    public static double deteminant(double[][] matrix){
        if(matrix.length != matrix[0].length){
            return 0;
        }else if(matrix.length == 2){
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        double ans = 0;
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
                ans+= matrix[0][i] * deteminant(miniMatrix);
            }else{
                ans-= matrix[0][i] * deteminant(miniMatrix);
            }
        }
        return ans;
    }
}

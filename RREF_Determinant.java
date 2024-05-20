public class RREF_Determinant {
    public static void main(String[] args){
        double[][] matrix;
        for (int i = 0; i < 1; i++){
            matrix = createRandomMatrix(200, 200, -100, 100);
            long startTime = System.nanoTime();
            determinant(matrix);
            long endTime = System.nanoTime();long executionTime = (endTime - startTime);
            System.out.println("time elapsed " + executionTime/1000000000 + "." + executionTime%1000000000);
        }
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
    public static double determinant(double[][] matrix){
        double ans = 1;
        for(int i = 0; i < matrix.length; i++){
            if(matrix[i][i] == 0){
                for(int j = 0; j < matrix.length; j++){
                    //makes sure thet the new row doesn't also have a 0 on the diagonal and this row doesn't have a 0 on the other row's diagonal
                    if(matrix[j][i] != 0 && matrix[i][j] !=0){
                        double[] tempRow = matrix[j];
                        matrix[j] = matrix[i];
                        matrix[i] = tempRow;
                        ans = -ans;
                        break;
                    }
                }
            }
        }
        //goes through the bottom left of the matrix and does the gauss shit
        for(int i = 0; i < matrix.length - 1; i++){
            for(int j = i + 1; j < matrix.length; j++){
                if(matrix[j][i] != 0){
                    double diff = matrix[j][i] / matrix[i][i];
                    for(int k = i; k < matrix.length; k++){
                        matrix[j][k]-=(matrix[i][k] * diff);;
                    }
                }
            }
        }
        //same for the top right of the matrix
        for(int i = matrix.length - 1; i > 0; i--){
            for(int j = i - 1; j >= 0; j--){
                if(matrix[j][i] != 0){
                    double diff = matrix[j][i] / matrix[i][i];
                    for(int k = 0; k < matrix.length; k++){
                        matrix[j][k]-=(matrix[i][k] * diff);;
                    }
                }
            }
        }
        for(int i = 0; i < matrix.length; i++){
            ans*=matrix[i][i];
        }
        return ans;
    }

    public static void displayMatrix(double[][] matrix){
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                if(j!=0){
                    if(matrix[i][j] >= 0){
                        if(matrix[i][j] < 10){
                            System.out.print(",   ");
                        }else if(matrix[i][j] < 100){
                            System.out.print(",  ");
                        }else{
                            System.out.print(", ");
                        }
                    }else{
                        if(matrix[i][j] > -10){
                            System.out.print(",  ");
                        }else{
                            System.out.print(",   ");
                        }
                    }
                }else if(matrix[i][j] >= 0){
                    System.out.print(" ");
                }
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}

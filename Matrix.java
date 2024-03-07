
import java.util.*;

public class Matrix {
    static Scanner sc = new Scanner(System.in);

/*Other methods to add
 * solves the matrix
 */

    public static void main(String[] args){
        double[][] A = {{0, 1, 0, 6, 0},{0, 0, 1, -1, 1},{0, 0, 0, 0, 1}};
        display(A);
        System.out.println(isRREF(A));
    }

    public static double[][] createMatrix(int rows, int columns){
        double[][] matrix = new double[rows][columns];
        for(int i=0; i<rows; i++){
            System.out.println("enter number for row " + (i + 1) + " (" + columns + " long)");
            for(int j=0; j<columns; j++){
                matrix[i][j] = sc.nextInt();
            }
        }
        return matrix;
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

    public static double[][] multiplyMatrices(double[][] first, double[][] second){
        if(first[0].length != second.length){
            System.out.println("nope");
            return null;
        }
        double[][] solution = new double[first.length][second[0].length];
        for(int i=0; i < first.length; i++){
            for(int j = 0; j < second[0].length; j++){
                int ans = 0;
                for(int k = 0; k < second.length; k++){
                    ans+=(first[i][k] * second[k][j]);
                }
                solution[i][j] = ans;
            }
        }
        return solution;
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

    public static double trace(double[][] matrix){
        if(matrix.length != matrix[0].length){
            System.out.println("Matrix needs to be square");
            return 0; //?
        }
        int ans = 0;
        for(int i = 0; i < matrix.length; i++){
            ans+=matrix[i][i];
        }
        return ans;
    }

    public static int deteminant(double[][] matrix){
        if(matrix.length != matrix[0].length){
            return 0;
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
                ans+= matrix[0][i] * deteminant(miniMatrix);
            }else{
                ans-= matrix[0][i] * deteminant(miniMatrix);
            }
        }
        return ans;
    }

    public static void switchRows(double[][] matrix, int row1, int row2){
        row1--;
        row2--;
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }

    public static void addRows(double[][] matrix, int unchaningRow, int changedRow, int multiplier){
        unchaningRow--;
        changedRow--;
        for(int i = 0; i < matrix[0].length; i++){
            matrix[changedRow][i] += matrix[unchaningRow][i] * multiplier;
        }
    }

    public static void multiplyRow(double[][] matrix, int row, int multiplier){
        for(int i = 0; i < matrix[0].length; i++){
            matrix[row][i] *= multiplier;
        }
    }

    public static boolean isDiagonal(double[][] matrix){
        if(matrix.length != matrix[0].length)
            return false;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                if(i != j && matrix[i][j] != 0)
                    return false;
            }
        }
        return true;
    }

    public static boolean isUpperTriangular(double[][] matrix){
        if(matrix.length != matrix[0].length)
            return false;
        if(matrix.length == 1)
            return true;
        for(int i = 1; i < matrix.length; i++){
            for(int j = 0; j < i; j++){
                if(matrix[i][j] != 0){
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean isLowerTriangular(double[][] matrix){
        if(matrix.length != matrix[0].length)
            return false;
        if(matrix.length == 1)
            return true;
        for(int i = 1; i < matrix.length; i++){
            for(int j = 0; j < i; j++){
                if(matrix[j][i] != 0){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isREF(double[][] matrix){
        int columns = -1;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] != 0){
                    if(matrix[i][j] != 1 || j <= columns)
                        return false;
                    columns = j;
                    break;
                }else if(j == matrix[0].length - 1){
                    columns = matrix[0].length;
                }
            }
        }
        return true;
    }

    public static boolean isRREF(double[][] matrix){
        int columns = -1;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] != 0){
                    if(matrix[i][j] != 1 || j < columns)
                        return false;
                    columns = j;
                    for(int k = 0; k < i; k++){
                        System.out.println(matrix[k][j] + " " + k + " " + j);
                        if(matrix[k][j] != 0)
                            return false;
                    }
                    break;
                }else if(j == matrix.length - 1){
                    columns = matrix.length;
                }
            }
        }
        return true;
    }

    public static void display(double[][] matrix){
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
/*
  Problem from hacker rank
  URL https://www.hackerrank.com/challenges/magic-square-forming/problem

  In this problem first thing come into mind
  that there are limited magic matrix of 3X3
  and everybody uses brute force method to do this
  but what if 4x4 matrix is there or greater than that
  then you can't remember all matrix
  so this solution provides you better solution
  and this solution can also be modified
  for greater size of matrix
 */

import java.util.Scanner;

public class FormMagic {
    private int[][] arr;
    private boolean[] available;
    private static int[][] input;
    private static int minCost;

    private FormMagic(){
        arr = new int[3][3];
        input = new int[3][3];
        minCost = Integer.MAX_VALUE;
        available = new boolean[9];
        for (int i = 0; i < available.length; i++) {
            available[i] = true;
        }
    }

   private void printMinCost(){
        int permutationCost = 0;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
                permutationCost += Math.abs(input[i][j] - arr[i][j]);
        }
        minCost = Math.min(minCost, permutationCost);
    }
// This is recursive function which calculate all magic matrix of size 3x3
    private void permute(int row,int col){
        for (int i = 1; i <= 9; i++) {
            if (available[i-1]) {
                arr[row][col] = i;
                available[i - 1] = false;
                if (isFilled()) {
                    if (isValidSolution())
                        printMinCost();
                } else {
                    if (col != 2) {
                        permute(row, col + 1);
                    } else if (row != 2) {
                        permute(row + 1, 0);
                    }
                }
                arr[row][col] = 0;
                available[i - 1] = true;
            }
        }
    }
//This method checks whether the matrix is magic matrix or not
    private boolean isValidSolution() {
        for (int i = 0; i < 3; i++) {
            int rowsum=0,colsum=0;
            for (int j = 0;j<3;j++){
                rowsum = rowsum + arr[i][j];
                colsum = colsum + arr[j][i];

            }
            if (rowsum != 15 || colsum != 15)
                return false;

        }
        return ((arr[0][0] + arr[1][1] + arr[2][2] == 15) && (arr[2][0] + arr[1][1] + arr[0][2] == 15));
    }

    private boolean isFilled() {
        for(boolean i : available) {
            if (i) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        FormMagic fm = new FormMagic();
        Scanner sc = new Scanner(System.in);
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                input[i][j] = sc.nextInt();
            }
        }
        fm.permute(0,0);
        System.out.println(minCost);
    }
}

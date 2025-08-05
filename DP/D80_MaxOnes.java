// A merchant has two types of idols, gold and silver.
// He has arranged the idols in the form of m*n grid, 
// 	- the gold idols are represented as 1's 
// 	- the silver idols are represented as 0's.

// Your task is to find the longest consecutive arrangement of gold idols, 
// The arrangement can be either horizontal, vertical, diagonal or 
// antidiagonal, but not the combination of these.


// Input Format:
// -------------
// Line-1: Two space separated integers m and n, grid size.
// Next m lines : n space separated integers, arrangement of idols.

// Output Format:
// --------------
// Print an integer, longest arranement of gold idols.


// Sample Input:
// ---------------
// 4 5
// 1 0 1 1 1
// 0 1 0 1 0
// 1 0 1 0 1
// 1 1 0 1 1

// Sample Output:
// ----------------
// 4

import java.util.*;

public class D80_MaxOnes {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        int[][] mat=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                mat[i][j]=sc.nextInt();
            }
        }
        int[][][] dp=new int[m][n][4];
        int res=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(mat[i][j]==1){
                    dp[i][j][0]=(j>0)?dp[i][j-1][0]+1:1;
                    dp[i][j][1]=(i>0)?dp[i-1][j][1]+1:1;
                    dp[i][j][2]=(i>0 && j>0)?dp[i-1][j-1][2]+1:1;
                    dp[i][j][3]=(i>0 && j<n-1)?dp[i-1][j+1][3]+1:1;
                    for(int d=0;d<4;d++){
                        res=Math.max(res,dp[i][j][d]);
                    }
                }
            }
        }
        System.out.println(res);
        sc.close();
    }
}

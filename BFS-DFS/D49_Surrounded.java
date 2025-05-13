// Pranav has a puzzle board filled with square boxes in the form of a grid.
// Some cells in the grid may be empty. '0' - indicates empty, '1' - indicates a box. 

// Pranav wants to find out the number of empty spaces which are completely 
// surrounded by the square boxes (left, right, top, bottom) in the board.

// You are given the board in the form of a grid M*N, filled wth 0's and 1's.
// Your task is to help Pranav to find the number of empty groups surrounded by
// the boxes in the puzzle board.

// Input Format:
// -------------
// Line-1: Two integers M and N, the number of rows and columns in the board.
// Next M lines: contains N space-separated either 0 or 1.

// Output Format:
// --------------
// Print an integer, the number of empty spaces in the puzzle board.


// Sample Input-1:
// ---------------
// 6 7
// 1 1 1 1 0 0 1
// 1 0 0 0 1 1 0
// 1 0 0 0 1 1 0
// 0 1 1 1 0 1 0
// 1 1 1 0 0 1 1
// 1 1 1 1 1 1 1

// Sample Output-1:
// ----------------
// 2

// Explanation:
// ------------
// The 2 empty groups are as follows:
// 1st group starts at cell(1,1), 2nd group starts at cell(3,4).
// The groups which are starts at cell(0,4), cell(1,6) and cell(3,0)
// are not valid empty groups, because they are not completely surrounded by boxes.


// Sample Input-2:
// ---------------
// 6 6
// 1 1 0 0 1 1
// 1 0 1 1 0 1
// 0 1 0 1 0 0
// 1 1 0 0 0 1
// 0 0 1 0 1 1
// 1 1 0 1 0 0

// Sample Output-2:
// ----------------
// 1

// Explanation:
// ------------
// The only empty group starts at cell(1,1) is surrounded by boxes.

import java.util.*;

public class D49_Surrounded {
    private static void dfs(int i , int j, int m, int n, int[][] mat, boolean[][] vis){
        if(i<0 || i>=m || j<0 || j>=n || mat[i][j]!=0 || vis[i][j]){
            return;
        }
        vis[i][j]=true;
        dfs(i,j+1,m,n,mat,vis);
        dfs(i,j-1,m,n,mat,vis);
        dfs(i-1,j,m,n,mat,vis);
        dfs(i+1,j,m,n,mat,vis);
    }
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
        boolean[][] vis=new boolean[m][n];
        int res=0;
        for(int i=0;i<m;i++){
            if(mat[i][0]==0 && !vis[i][0]){
                dfs(i,0,m,n,mat,vis);
            }
            if(mat[i][n-1]==0 && !vis[i][n-1]){
                dfs(i,n-1,m,n,mat,vis);
            }
        }
        for(int j=0;j<n;j++){
            if(mat[0][j]==0 && !vis[0][j]){
                dfs(0,j,m,n,mat,vis);
            }
            if(mat[m-1][j]==0 && !vis[m-1][j]){
                dfs(m-1,j,m,n,mat,vis);
            }
        }
        for(int i=1;i<m-1;i++){
            for(int j=1;j<n-1;j++){
                if(mat[i][j]==0 && !vis[i][j]){
                    dfs(i,j,m,n,mat,vis);
                    res+=1;
                }
            }
        }
        System.out.println(res);
        sc.close();
    }
}

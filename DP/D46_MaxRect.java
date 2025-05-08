// A group of researchers is analyzing satellite imagery of agricultural fields, 
// represented by a grid of land sections. Each section is marked either as 
// fertile (1) or infertile (0). To efficiently plan crop planting, the researchers 
// need to identify the largest rectangular area consisting exclusively of fertile 
// land sections.

// Given a binary grid representing the land (1 for fertile and 0 for infertile), 
// your task is to compute the area of the largest rectangle consisting entirely 
// of fertile land sections.

// Input Format:
// -------------
// Line-1: Two integers rows(r) and columns(c) of grid.
// Next r lines: c space separated integers, each row of the grid.

// Output Format:
// --------------                         
// Print an integer, area of the largest rectangle consisting entirely of fertile land sections.

// Example:
// --------
// input=
// 4 5
// 1 0 1 0 0
// 1 0 1 1 1
// 1 1 1 1 1
// 1 0 0 1 0

// output=
// 6

import java.util.*;

public class D46_MaxRect {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        int[][] mat=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                mat[i][j]=sc.nextInt();
            }
        }
        int[][] pref=new int[m+1][n];
        for(int i=1;i<=m;i++){
            for(int j=0;j<n;j++){
                pref[i][j]=pref[i-1][j]+mat[i-1][j];
            }
        }
        int res=Integer.MIN_VALUE;
        for(int i=1;i<=m;i++){
            for(int k=i;k<=m;k++){
                int t=0;
                int c=0;
                for(int j=0;j<n;j++){
                    if(pref[k][j]-pref[i-1][j]==(k-i+1)){
                        c+=1;
                    }
                    else{
                        c=0;
                    }
                    t=Math.max(t,c);
                }
                res=Math.max(t*(k-i+1),res);
            }
        }
        System.out.println(res);
        sc.close();
    }
}

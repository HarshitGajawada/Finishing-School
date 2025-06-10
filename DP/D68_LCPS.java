// Alex and his twin brother Jordan often create secret messages. One day, Jordan 
// gives Alex two encrypted messages and challenges him to find the longest common 
// palindromic pattern hidden within both messages.

// Alex wants your help to decode the longest common palindromic subsequence that 
// exists in both strings.

// Your task is to determine the length of the longest subsequence that:
// - Appears in both messages
// - Is a palindrome

// Input Format:
// -------------
// input1: A string representing the first encrypted message.
// input2: A string representing the second encrypted message.

// Output Fromat:
// --------------
// Return an integer representing the length of the longest common palindromic 
// subsequence shared by both messages.


// Sample Input: 
// -------------
// adfa
// aagh

// Sample Output:
// --------------
// 2


// Sample Input-2:
// ---------------
// abcda
// fxaaba

// Sample Output:
// --------------
// 3

// Explanation:
// ------------
// The longest palindromic subsequence common to both is "aba" with length 3.

import java.util.*;

public class D68_LCPS {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s1=sc.next();
        String s2=sc.next();
        sc.close();
        int m=s1.length();
        int n=s2.length();
        int[][] dp=new int[m+1][n+1];
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j]=1+dp[i-1][j-1];
                }
                else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        StringBuilder lcs=new StringBuilder();
        int i=m,j=n;
        while(i>0 && j>0){
            if(s1.charAt(i-1)==s2.charAt(j-1)){
                lcs.append(s1.charAt(i-1));
                i-=1;
                j-=1;
            } 
            else if(dp[i-1][j]>dp[i][j-1]){
                i-=1;
            } 
            else {
                j-=1;
            }
        }
        lcs.reverse();
        int l=lcs.length();
        int[][] dpp=new int[l][l];
        for(i=0;i<l;i++){
            dpp[i][i]=1;
        }
        int res=0;
        for(int len=2;len<=l;len++){
            for(i=0;i<=l-len;i++){
                j=i+len-1;
                if(lcs.charAt(i)==lcs.charAt(j)){
                    dpp[i][j]+=dpp[i+1][j-1]+2;
                }
            }
        }
        for(i=0;i<l;i++){
            for(j=0;j<l;j++){
                res=Math.max(res,dpp[i][j]);
            }
        }
        System.out.println(res);
    }
}

// You are working in a genetics laboratory where you are tasked with correcting 
// DNA sequences. Each DNA strand is represented as a sequence of characters 
// 'A', 'C', 'G', and 'T'.
// Sometimes, due to mutations or errors during sequencing, the DNA strand (originalDNA) 
// must be modified to match a targetDNA sequence exactly.

// You can perform the following mutation operations:
// - Insert a nucleotide (A, C, G, or T) into the DNA strand.
// - Delete a nucleotide from the DNA strand.
// - Replace a nucleotide with another one.

// Each operation counts as one step.

// Your task is to find the minimum number of mutation operations needed to 
// transform the originalDNA into the targetDNA.

// Input format:
// -------------
// 2 space seperated strings, originalDNA and targetDNA

// Output format:
// --------------
// An integer, the minimum number of mutation operations


// Example 1:
// -----------
// Input:
// ACGT AGT

// Output:
// 1

// Explanation:
// Delete 'C': "ACGT" → "AGT"
// Only 1 mutation is needed.

// Example 2:
// ----------
// Input:
// GATTAC GCATGCU

// Output:
// 4

// Explanation:
// - Replace 'A' with 'C': "GATTAC" → "GCTTAC"
// - Replace 'T' with 'A': "GCTTAC" → "GCATAC"
// - Replace 'A' with 'G': "GCATAC" → "GCATGC"
// - Insert 'U' at the end: "GCATGC" → "GCATGCU"

// Thus, 4 mutations are needed.

import java.util.*;

public class D41_MutateDNA {
    private static int changes(String s1, String s2){
        int n1=s1.length();
        int n2=s2.length();
        int[][]dp=new int[n1+1][n2+1];
        for(int i=0;i<=n1;i++){
            dp[i][0]=i;
        }
        for(int i=0;i<=n2;i++){
            dp[0][i]=i;
        }
        for(int i=1;i<=n1;i++){
            for(int j=1;j<=n2;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }
                else{
                    dp[i][j]=Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                    
                }
            }
        }
        return dp[n1][n2];
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s1=sc.next();
        String s2=sc.next();
        System.out.println(changes(s1,s2));
        sc.close();
    }
}

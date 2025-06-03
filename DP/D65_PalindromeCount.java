// Mr. Parandamayya is working with Strings.
// He is given a String S. He has to find the palindromes in S, 
// A palindrome can be a substring of S (Strictly substrings only, not subsequences).

// Your task is to find the count the number of palindromes can be formed from S.

// NOTE: Count each occurence of palindromes if duplicate substrings found. 

// Input Format:
// -------------
// A string S

// Output Format:
// --------------
// Print an integer, number of palindromes.


// Sample Input-1:
// ---------------
// divider

// Sample Output-1:
// ----------------
// 9

// Explanation:
// -------------
// Palindromes are d, i, v, i, d, e, r, ivi, divid

// Sample Input-2:
// ---------------
// abcdef

// Sample Output-2:
// ----------------
// 6

// Explanation:
// -------------
// Palindromes are a, b, c, d, e, f.

import java.util.*;

public class D65_PalindromeCount{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        int n=s.length();
        boolean[][] dp=new boolean[n][n];
        int res=0;
        for(int len=1;len<=n;len++){
            for(int i=0;i<=n-len;i++){
                int j=i+len-1;
                if(s.charAt(i)==s.charAt(j)){
                    if(len==1 || len==2 || dp[i+1][j-1]){
                        dp[i][j]=true;
                        res+=1;
                    }
                }
            }
        }
        System.out.println(res);
        sc.close();
    }
}
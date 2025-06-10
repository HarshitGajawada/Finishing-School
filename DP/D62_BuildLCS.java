// Preethi is playing with strings. She is given 2 strings. 
// Preethi can remove any number of characters from 2 strings such that 2 strings 
// to be equal after removal (Sequence of characters shouldn't change).
// Find the smallest ASCII sum possible for the removed characters.

// Input Format:
// -------------
// Line-1: Two space seperated strings

// Output Format:
// --------------
// return an integer , represents ASCII sum with the given constraints.

// Sample Input-1:
// ---------------
// treat create

// Sample Output-1:
// ----------------
// 316

// Explanation:
// -------------
// Remove 't' in string1 and 'c' and 'e' in string 2. so sum= 116+99+101=316


// Sample Input-2:
// ---------------
// interest pintrest

// Sample Output-2:
// ----------------
// 213

// Explanation:
// -------------
// Remove 'e' in string1 and 'p' in string2. so sum=101+112=213

import java.util.*;

public class D62_BuildLCS {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s1=sc.next();
        String s2=sc.next();
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
        int res=0;
        // System.out.println(lcs);
        int ind=0;
        for(char c:s1.toCharArray()){
            if(ind>=lcs.length() || c!=lcs.charAt(ind)){
                // System.out.println(c);
                res+=(int)c;
            }
            else{
                ind+=1;
            }
        }
        ind=0;
        for(char c:s2.toCharArray()){
            if(ind>=lcs.length() || c!=lcs.charAt(ind)){
                // System.out.println(c);
                res+=(int)c;
            }
            else{
                ind+=1;
            }
        }
        System.out.println(res);
        sc.close();
    }
}

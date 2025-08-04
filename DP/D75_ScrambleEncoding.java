// A secret agent encodes a message by recursively scrambling it using a random encryption 
// protocol.  The encryption process follows these rules:
//  - If the message is a single character, leave it unchanged.
//  - If the message has more than one character:
// 		- Split the message into two non-empty parts at any random position.
// 		- With a 50% chance, swap the two parts; otherwise, keep them in the same order.
// 		- Repeat this scrambling recursively on each part.

// This encryption method produces a scrambled version of the original message.

// You are now given two messages:
// original: the message before scrambling.
// scrambled: a possibly scrambled version of the original message.

// Write a program to determine whether the scrambled message could have been produced 
// by scrambling the original message using the above protocol.

// Sample Input:
// -------------
// Two strings, original and scrambled, each of equal length.

// Sample Output:
// ---------------
// Return true if the scrambled string could be a scrambled version of the original using 
// the given encryption protocol. Otherwise, return false.


// Sample Input:
// -------------
// secure cesure

// Sample Output:
// ---------------
// true

// Explanation: 
// ------------
// One possible scrambling path leads from "secure" to "cesure".

// Sample Input:
// -------------
// planet npealt

// Sample Output:
// ---------------
// false

// Explanation: 
// ------------
// No sequence of valid splits and swaps can lead to "petlan" from "npealt".

import java.util.*;

public class D75_ScrambleEncoding {
    static String s1;
    static String s2;
    static int[][][] dp;
    private static boolean isScrambled(int i, int j, int len){
        if(dp[i][j][len]!=0){
            return dp[i][j][len]==1;
        }
        int a=i;
        int b=j;
        int c=0;
        while(a<i+len && b<j+len){
            if(s1.charAt(a)==s2.charAt(b)){
                c+=1;
            }
            a+=1;
            b+=1;
        }
        if(c==len){
            dp[i][j][len]=1;
            return true;
        }
        int[] cnt=new int[26];
        for(int l=0;l<len;l++){
            cnt[s1.charAt(i+l)-'a']+=1;
            cnt[s2.charAt(j+l)-'a']-=1;
        }
        for(int ch:cnt){
            if(ch!=0){
                dp[i][j][len]=-1;
                return false;
            }
        }
        for(int k=1;k<len;k++){
            if((isScrambled(i,j,k) && isScrambled(i+k,j+k,len-k)) || (isScrambled(i,j+len-k,k) && isScrambled(i+k,j,len-k))){
                dp[i][j][len]=1;
                return true;
            }
        }
        dp[i][j][len]=-1;
        return false;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        s1=sc.next();
        s2=sc.next();
        int n=s1.length();
        dp=new int[n][n][n+1];
        System.out.println(isScrambled(0,0,n));
        sc.close();
    }
}

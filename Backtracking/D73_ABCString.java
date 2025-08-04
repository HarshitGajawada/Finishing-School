// Ravi is playing with strings.Given Three alphabets [a,b,c] , ravi has to 
// make strings such that no two adjacents alphabets are same.

// For example, He can make strings as "ab", "acb", "b" and "cbabcabcb" which 
// are acceptable.where as the strings "bb", "bcc" and "aabbc" are not acceptable.

// Given two integers N and L, Ravi made a list of all acceptable strings of 
// length N sorted in lexicographical order.

// Return the Lth string of this list or return an empty string if there are less 
// than L acceptable strings of length N.

// Input Format:
// -------------
// Line-1: Two space separated integers N and L.

// Output Format:
// --------------
// Print a string result.


// Sample Input-1:
// ---------------
// 2 3

// Sample Output-1:
// ----------------
// ba

// Explanation:
// -------------
// Strings in lexigraphical order are ab,ac,ba,bc,ca,cb. and 3rd one is ba.


// Sample Input-2:
// ---------------
// 3 4

// Sample Output-2:
// ----------------
// acb

// Explanation:
// ------------
// Strings in lexigraphical order are aba,abc,aca,acb,bab....

import java.util.*;

public class D73_ABCString {
    static String res="";
    static int ind=0;
    private static void backtrack(char ch, StringBuilder curr, int n, int l){
        if(curr.length()==n){
            ind+=1;
            if(ind==l){
                // System.out.println(curr);
                res=curr.toString();
            }
            return;
        }
        for(char c='a';c<='c';c++){
            if(c!=ch){
                curr.append(c);
                backtrack(c,curr,n,l);
                curr.deleteCharAt(curr.length()-1);
            }
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int l=sc.nextInt();
        backtrack('\0',new StringBuilder(""),n,l);
        System.out.println(res);
        sc.close();
    }
}

// Given two strings S1 and S2, find if S2 can match S1 or not.

// A match that is both one-to-one (an injection) and onto (a surjection), 
// i.e. a function which relates each letter in string S1 to a separate and 
// distinct non-empty substring in S2, where each non-empty substring in S2
// also has a corresponding letter in S1.

// Return true,if S2 can match S1.
// Otherwise false.

// Input Format:
// -------------
// Line-1 -> Two strings S1 and S2

// Output Format:
// --------------
// Print a boolean value as result.


// Sample Input-1:
// ---------------
// abab kmitngitkmitngit

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// aaaa kmjckmjckmjckmjc

// Sample Output-2:
// ----------------
// true

// Sample Input-3:
// ---------------
// mmnn pqrxyzpqrxyz

// Sample Output-3:
// ----------------
// false

import java.util.*;

public class D35_WordMatch {
    private static boolean backtrack(int i1, int i2, String s1, String s2, HashMap<Character,String> mp, HashSet<String> seen){
        if(i1==s1.length() && i2==s2.length()){
            return true;
        }
        if(i1==s1.length() || i2==s2.length()){
            return false;
        }
        char c=s1.charAt(i1);
        if(mp.containsKey(c)){
            String curr=mp.get(c);
            if(!s2.startsWith(curr,i2)){
                return false;
            }
            return backtrack(i1+1, i2+curr.length(), s1, s2, mp, seen);
        }
        for(int ind=i2+1;ind<=s2.length();ind++){
            String curr=s2.substring(i2, ind);
            if(seen.contains(curr)){
                continue;
            }
            mp.put(c, curr);
            seen.add(curr);
            if(backtrack(i1+1, ind, s1, s2, mp, seen)){
                return true;
            }
            mp.remove(c);
            seen.remove(curr);
        }
        return false;
    }
    private static boolean matchCheck(String s1, String s2){
        HashMap<Character,String> mp=new HashMap<>();
        HashSet<String> seen=new HashSet<>();
        return backtrack(0,0,s1,s2,mp,seen);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String[] inp=sc.nextLine().split(" ");
        System.out.println(matchCheck(inp[0],inp[1]));
        sc.close();
    }
    
}
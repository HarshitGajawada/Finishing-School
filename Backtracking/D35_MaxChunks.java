// Captain Raynor is on a mission to decode an alien transmission.
// The transmission contains a single long string S, and it is believed to be 
// made up of multiple unique signal chunks, sent one after another with no spaces.

// Your task is to help Captain Raynor split the transmission into the maximum 
// number of non-empty, unique signal chunks such that when all chunks are 
// concatenated in order, they exactly recreate the original transmission S.

// Your goal is to maximize the number of unique chunks the message can be split into.

// Input Format:
// Line-1: A string S representing the alien transmission.

// Output Format:
// Print a single integer – the maximum number of distinct chunks the string can be split into.

// Sample Input-1:
// ---------------
// banana

// Sample Output-1:
// ----------------
// 4

// Explanation: 
// ------------
// One valid way to split the string is: "b", "a", "n", "ana".
// This keeps all chunks unique.
// Another way like "b", "a", "n", "an", "a" is invalid because "a" appears twice.


// Sample Input-2:
// ---------------
// mississippi

// Sample Output-2:
// ----------------
// 7

// Explanation: 
// ------------
// One valid way to split it is: "m", "i", "s", "si", "ssi", "p", "pi".
// All chunks are distinct and together recreate the original transmission.

// NOTE: Only contiguous chunks (i.e., substrings) are allowed. Subsequence-based 
//       splitting is not permitted.

import java.util.*;

public class D35_MaxChunks {
    private static void backtrack(int ind, String s, HashSet<String> parts, int[] res){
        if(ind==s.length()){
            res[0]=Math.max(res[0],parts.size());
            return;
        }
        for(int i=ind+1;i<=s.length();i++){
            String part=s.substring(ind,i);
            if(!parts.contains(part)){
                parts.add(part);
                backtrack(i,s,parts,res);
                parts.remove(part);
            }
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        HashSet<String> parts=new HashSet<>();
        int[] res=new int[1];
        backtrack(0,s,parts,res);
        System.out.println(res[0]);
        sc.close();
    }
}

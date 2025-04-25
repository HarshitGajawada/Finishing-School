// In the ancient land of Palindoria, wizards write magical spells as strings of 
// lowercase letters. However, for a spell to be effective, each segment of the 
// spell must read the same forward and backward.

// Given a magical spell 'spell', your task is to partition it into sequences of 
// valid magical spell segments. 
// Your goal is to help the wizard discover all valid combinations of magical spell 
// segmentations.

// Example 1:
// ----------
// Input:  
// aab
  
// Output:  
// [[a, a, b], [aa, b]]

// Example 2:

// Input:  
// a  
// Output:  
// [[a]]

// Spell Constraints:

// - The length of the spell is between 1 and 16 characters.  
// - The spell contains only lowercase English letters.  

import java.util.*;

public class D36_PalPartitions {
    private static boolean isPal(String s){
        int n=s.length();
        for(int i=0;i<n;i++){
            if(s.charAt(i)!=s.charAt(n-i-1)){
                return false;
            }
        }
        return true;
    }
    private static void backtrack(int ind, String s, ArrayList<String> part, ArrayList<ArrayList<String>> res){
        if(ind==s.length()){
            for(String ss:part){
                if(!isPal(ss)){
                    return;
                }
            }
            res.add(new ArrayList<>(part));
            return;
        }
        for(int i=ind+1;i<=s.length();i++){
            String curr=s.substring(ind,i);
            part.add(curr);
            backtrack(i,s,part,res);
            part.remove(part.size()-1);
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        ArrayList<ArrayList<String>> res=new ArrayList<>();
        backtrack(0,s,new ArrayList<String>(),res);
        System.out.println(res);
        sc.close();
    }
}

// Given a classic mobile phone, and the key pad of the phone looks like below.
// 	1		2		3
// 		   abc	   def
		 
// 	4		5		6
//    ghi     jkl     mno
  
// 	7		8		9
//   pqrs     tuv    wxyz
	
// 	*		0		#


// You are given a string S contains digits between [2-9] only, 
// For example: S = "2", then the possible words are "a", "b", "c".

// Now your task is to find all possible words that the string S could represent.
// and print them in a lexicographical order. 

// Input Format:
// -------------
// A string S, consist of digits [2-9]

// Output Format:
// --------------
// Print the list of words in lexicographical order.


// Sample Input-1:
// ---------------
// 2

// Sample Output-1:
// ----------------
// [a, b, c]


// Sample Input-2:
// ---------------
// 24

// Sample Output-2:
// ----------------
// [ag, ah, ai, bg, bh, bi, cg, ch, ci]

import java.util.*;

public class D34_KeyPad {
    public static ArrayList<String> res;
    public static void backtrack(String s, ArrayList<String> res, HashMap<Character, String> mp, int ind, StringBuilder curr) {
        if(ind==s.length()){
            res.add(curr.toString());
            return;
        }
        char key=s.charAt(ind);
        for(char ch:mp.get(key).toCharArray()){
            curr.append(ch);
            backtrack(s,res,mp,ind+1,curr);
            curr.deleteCharAt(curr.length()-1);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        HashMap<Character, String> mp = new HashMap<>();
        mp.put('2',"abc");
        mp.put('3',"def");
        mp.put('4',"ghi");
        mp.put('5',"jkl");
        mp.put('6',"mno");
        mp.put('7',"pqrs");
        mp.put('8',"tuv");
        mp.put('9',"wxyz");
        res=new ArrayList<>();
        backtrack(s,res,mp,0,new StringBuilder());
        System.out.println(res);
        sc.close();
    }
}

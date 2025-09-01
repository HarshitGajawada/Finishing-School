// Mr Saurabh is given a list of words.
// Your task is to help Mr Saurabh to find the size of largest group

// A group is formed using the following rules:
// - Pick one smallest word (in length) and form a group with this word
//   (if it is not part of any other group yet)
// - Add a letter at any place in the word without changing the relative order 
//   of the letters in it, and if it forms a word which is existing in the list[],
//   then add the word to the group.
// - Repeat the above two steps, till all the words in the list are part of groups.

// NOTE:You move form more than one group.

// Input Format:
// -------------
// Space separated words, wordsList[].

// Output Format:
// --------------
// Print an integer result


// Sample Input-1:
// ---------------
// many money n an mony any one mone on

// Sample Output-1:
// ----------------
// 5

// Explanation:
// ------------
// the words group is : [n, on, one, mone, money]


// Sample Input-2:
// ---------------
// a ab abb babb abab baba bab abbaa

// Sample Output-2:
// ----------------
// 4

import java.util.*;

public class D95_LargestGroupChain {
    private static int res=0;
    static boolean validnext(String shorter, String longer){
        if(longer.length()-shorter.length()!=1){
            return false;
        }
        int i=0; 
        int j=0;
        while(i<shorter.length() && j<longer.length()){
            if(shorter.charAt(i)==longer.charAt(j)){
                i++;
            }
            j++;
        }
        return i==shorter.length(); 
    }
    private static void backtrack(int ind, int size, boolean[] vis, String[] s){
        res=Math.max(res,size);
        for(int i=ind+1;i<s.length;i++){
            if(!vis[i] && validnext(s[ind],s[i])){
                vis[i]=true;
                backtrack(i,size+1,vis,s);
                vis[i]=false;
            }
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String[] s=sc.nextLine().split(" ");
        int n=s.length;
        boolean[] vis=new boolean[n];
        Arrays.sort(s,(a,b)-> a.length()-b.length());
        for(int i=0;i<n;i++){
            vis[i]=true;
            backtrack(i,1,vis,s);
            vis[i]=false;
        }
        System.out.println(res);
        sc.close();
    }
}

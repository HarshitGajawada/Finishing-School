// Prabhath is working on words.  He used to take out every letter that was repeated 
// in the word. 
// A word W is given to Prabhath. His objective is to eliminate every duplicate 
// letter from W such that the resultant word R contains every unique letter from W
// and did not contain any duplicate letters. 
// And R should be at the top of the dictionary order.

// NOTE:
// -----
// He is not allowed to shuffle the relative order of the letters of the word W to
// create the word R.

// Input Format:
// -------------
// A String, the word W.

// Output Format:
// --------------
// Print a String, resultant word R.


// Sample Input-1:
// ---------------
// cbadccb

// Sample Output-1:
// ----------------
// adcb


// Sample Input-2:
// ---------------
// silicosis

// Sample Output-2:
// ----------------
// ilcos

import java.util.*;

public class D79_RemoveDuplicates {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        Stack<Character> st=new Stack<>();
        int[] lastocc=new int[26];
        Arrays.fill(lastocc,-1);
        for(int i=0;i<s.length();i++){
            lastocc[s.charAt(i)-'a']=i;
        }
        boolean[] seen=new boolean[26];
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(seen[ch-'a']==true){
                continue;
            }
            while(!st.isEmpty() && st.peek()>ch && lastocc[st.peek()-'a']>i){
                seen[st.peek()-'a']=false;
                st.pop();
            }
            st.push(ch);
            seen[ch-'a']=true;
        }
        StringBuilder res=new StringBuilder("");
        while(!st.isEmpty()){
            res.append(st.peek());
            st.pop();
        }
        System.out.println(res.reverse());
        sc.close();
    }
}

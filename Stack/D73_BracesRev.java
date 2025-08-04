// Aruna as a type writer, typing a document in her laptop. Suddently her system got
// hacked and whatever she types as words are displaying in reverse and with simple 
// braces. She types only lowercase letters.

// Inorder to get the actual words, Aruna has to reverse each word starting with the
// word which is in innermost most braces and remove those braces.
// Help Aruna to get actual words.

// Constraints:
// ------------
//   - 0 <= word.length <= 2000
//   - Word only contains lower case English characters and parentheses.
//     It's guaranteed that all braces are balanced.


// Input Format:
// -------------
// Line-1: a string represents an encoded word.

// Output Format:
// --------------
// return the original string.


// Sample Input-1:
// ---------------
// (pqrs)

// Sample Output-1:
// ----------------
// srqp


// Sample Input-2:
// ---------------
// (uoy(are)woh)

// Sample Output-2:
// ----------------
// howareyou

// Explanation
// ------------
// Initially "are" will be revesed as "era".
// Then (uoyerawoh) will be reversed.

import java.util.*;

public class D73_BracesRev {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        Stack<Integer> st=new Stack<>();
        int n=s.length();
        int[] pair=new int[n];
        Arrays.fill(pair,-1);
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='('){
                st.push(i);
            }
            if(s.charAt(i)==')'){
                int open=st.peek();
                st.pop();
                pair[open]=i;
                pair[i]=open;
            }
        }
        int len=0;
        for(int i=0;i<n;i++){
            if(pair[i]==-1){
                len+=1;
            }
        }
        // System.out.println(Arrays.toString(pair));
        // System.out.println(len);
        StringBuilder res=new StringBuilder("");
        int ind=0;
        int dir=1;
        while(ind>=0 && ind<n){
            if(s.charAt(ind)!='(' && s.charAt(ind)!=')'){
                res.append(s.charAt(ind));
            }
            else{
                ind=pair[ind];
                dir*=-1;
            }
            ind+=dir;
        }
        System.out.println(res.toString());
        sc.close();
    }
}

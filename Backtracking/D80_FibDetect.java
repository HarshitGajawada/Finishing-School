// Pramod is working on Strings consist of digits only. He wants to findout, 
// whether the given string can form Fibonacci sequence or not.

// A String can form a Fibonacci Sequence, if it contains at least 
// three numbers, and numbers are in the following order:
// first, second, third  = first + second, fourth = third + second, .. so on.

// Return true, if the given string can form fibonacci sequence,
// otherwise, return false.

// Note: Numbers in the fibonacci sequence contains no leading 0's.
// for example, 2, 03,5 or 2,3,05 or 02,3,5 are not valid.

// Input Format:
// -------------
// A String consist of digits only

// Output Format:
// --------------
// Print a boolean value as result.


// Sample Input-1:
// ---------------
// 23581321

// Sample Output-1:
// ----------------
// true

// Explanation: 
// ------------
// Fibonacci Sequence is : 2, 3, 5, 8, 13, 21
// 2, 3, 2+3=5, 3+5=8, 5+8=13, 8+13=21.

// Sample Input-2:
// ---------------
// 199100199

// Sample Output-2:
// ----------------
// true

// Explanation: 
// ------------
// Fibonacci Sequence is : 1 99 100 199
// 1, 99, 1+99=100, 99+100=199.

import java.util.*;

public class D80_FibDetect {
    private static boolean backtrack(int ind, String s, ArrayList<Long> seg){
        if(ind==s.length() && seg.size()>=3){
            return true;
        }
        for(int i=ind+1;i<=s.length();i++){
            String num=s.substring(ind,i);
            if(num.length()>1 && num.charAt(0)=='0'){
                break;
            }
            long val=Long.parseLong(num);
            if(seg.size()>=2){
                long psum=seg.get(seg.size()-2)+seg.get(seg.size()-1);
                if(val<psum){
                    continue;
                }
                else if(val>psum){
                    break;
                }
            }
            seg.add(val);
            if(backtrack(i,s,seg)){
                return true;
            }
            seg.remove(seg.size()-1);
        }
        return false;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        System.out.println(backtrack(0,s,new ArrayList<Long>()));
        sc.close();
    }
}

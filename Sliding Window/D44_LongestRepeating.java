// Archaeologists discovered an ancient manuscript represented by a string 'text', 
// where each character represents an ancient symbol. They suspect the manuscript 
// might contain repeated symbol patterns (substrings).

// Your task is to analyze the text and determine the length of the longest 
// repeating symbol pattern. If the text contains no repeating patterns, return '0'.

// Example:
// --------
// Input=
// scarabankhscarab

// Output=
// 6

// Explanation: The longest repeating symbol pattern is "scarab", appearing twice.

//  Constraints:
// - 1 <= text.length <= 2000
// - 'text' consists of lowercase English letters ('a' - 'z').

import java.util.*;

public class D44_LongestRepeating{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        sc.close();
        HashSet<String> vis=new HashSet<>();
        for(int len=s.length()-1;len>=1;len--){
            StringBuilder curr=new StringBuilder("");
            for(int i=0;i<len;i++){
                curr.append(s.charAt(i));
            }
            if(vis.contains(curr.toString())){
                System.out.println(len);
                return;
            }
            vis.add(curr.toString());
            for(int i=1;i<=s.length()-len;i++){
                curr.deleteCharAt(0);
                curr.append(s.charAt(i+len-1));
                if(vis.contains(curr.toString())){
                    System.out.println(len);
                    return;
                }
                vis.add(curr.toString());
            }
        }
        System.out.println(0);
    }
}
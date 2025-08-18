// Mr Gnanesh is working with words. He has given a list of words. 
// Each word in the list contains only two lowercase letters [a-z].

// He wants to create biggest word BW, by concatenating words from the list, which 
// is a palindrome too. He is allowed to use each word from the list atmost once.

// Return the length of the biggest word can be formed using the list.
// If it is impossible to create such word, return 0.

// Input Format:
// -------------
// Space separated strings, words[], two letter words.

// Output Format:
// --------------
// Print an integer result.


// Sample Input-1:
// ---------------
// ab ba dd

// Sample Output-1:
// ----------------
// 6

// Explanation: 
// ------------
// The biggest word is, ab,dd,ba => abddba, which is a palindrome.


// Sample Input-2:
// ---------------
// pq rs sr mk km pq

// Sample Output-2:
// ----------------
// 8

// Explanation: 
// ------------
// The biggest word is, rs,sr,mk,km => rsmkkmsr or mkrssrkm..etc, 
// which is a palindrome.


// Sample Input-3:
// ---------------
// aa bb cc

// Sample Output-3:
// ----------------
// 2

import java.util.*;

public class D88_MakePalindrome {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String[] words=sc.nextLine().split(" ");
        HashMap<String,Integer> f=new HashMap<>();
        for(String w:words){
            f.put(w,f.getOrDefault(w,0)+1);
        }
        HashSet<String> vis=new HashSet<>();
        int res=0;
        boolean mid=false;
        for(String w:f.keySet()){
            if(vis.contains(w)==false){
                String rev=new StringBuilder(w).reverse().toString();
                if(w.equals(rev)){
                    int c=f.get(w);
                    res+=(c/2)*4;  
                    if(c%2==1 &&!mid){
                        res+=2;
                        mid=true;
                    }
                }
                else if(f.containsKey(w) && f.containsKey(rev)){
                    // System.out.println(w);
                    // System.out.println(rev);
                    res+=Math.min(f.get(w),f.get(rev))*4;
                }
                vis.add(w);
                vis.add(rev);
            }
        }
        System.out.println(res);
        sc.close();
    }
}

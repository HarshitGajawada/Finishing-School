// Imagine you're a digital security analyst reviewing a suspicious email. The email’s 
// content is a continuous string of characters, and you have a list of keywords 
// commonly used in phishing scams. Your mission is to scan the email text and flag 
// every segment that exactly matches one of these keywords. In other words, identify 
// all index pairs [i, j] such that the substring from position i to j in the email 
// text is one of the suspicious keywords. Return these pairs sorted by their starting 
// index, and if two pairs share the same starting index, sort them by their ending index.

// Input Format
// ------------
// Line-1: string STR(without any space)
// Line-2: space separated strings, suspicious keywords[]

// Output Format
// -------------
// Print the pairs[i, j] in sorted order.


// Example 1:
// ---------- ̑
// Input:  
// cybersecuritybreachalert
// breach alert cyber

// Output: 
// 0 4
// 13 18
// 19 23

// Example 2:
// ----------
// Input:  
// phishphishingphish
// phish phishing

// Output:
// 0 4
// 5 9
// 5 12
// 13 17


// Explanation: Notice that keywords can overlap—for instance, the word "phish" appears 
// as part of the substring [5,9] in addition to the complete "phishing" substring [5,12].

// Constraints:

// - 1 <= emailText.length <= 100  
// - 1 <= suspiciousWords.length <= 20  
// - 1 <= suspiciousWords[i].length <= 50  
// - emailText and each suspicious word consist of lowercase English letters.  
// - All suspicious words are unique.

import java.util.*;

class TrieNode{
    boolean end;
    TrieNode[] children;
    TrieNode(){
        end=false;
        children=new TrieNode[26];
    }
}

class Trie{
    TrieNode root;
    Trie(){
        root=new TrieNode();
    }
    void insert(String s){
        TrieNode t=root;
        for(char c:s.toCharArray()){
            int ind=c-'a';
            if(t.children[ind]==null){
                t.children[ind]=new TrieNode();
            }
            t
        }
        t.end=true;
    }
    ArrayList<Integer> ispres(String s, int i){
        TrieNode t=root;
        ArrayList<Integer> inds=new ArrayList<>();
        for(;i<s.length();i++){
            int ind=s.charAt(i)-'a';
            if(t.children[ind]==null){
                return inds;
            }
            if(t.children[ind].end==true){
                inds.add(i);
            }
            t=t.children[ind];
        }
        return inds;
    }
}

public class D26_SubstringLoc {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Trie t=new Trie();
        String email=sc.nextLine();
        String[] keys=sc.nextLine().split(" ");
        for(String k:keys){
            t.insert(k);
        }
        for(int i=0;i<email.length();i++){
            ArrayList<Integer> inds=t.ispres(email,i);
            for(int ind:inds){
                System.out.println(i+" "+ind);
            }
        }
        sc.close();
    }
}

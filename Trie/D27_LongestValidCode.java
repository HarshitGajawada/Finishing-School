// Imagine you're playing a fantasy video game where secret level codes unlock new 
// worlds. These codes are strings made up of letters, and a level code is only 
// considered valid if every shorter code formed by its leading characters has been
// discovered along the journey. In other words, a code is unlockable only when all
// of its prefixes are also present in your collection.

// Given a list of strings representing the level codes you’ve collected, find the 
// longest valid level code such that every prefix of that code is in the list. 
// If there is more than one valid code of the same length, choose the one that 
// comes first in alphabetical order. If no such code exists, return an empty string.

// Input Format
// -------------
// Line1: Space separated codes (strings)
 
// Output Format
// --------------
// string 


// Example 1:
// ----------
// Input:
// m ma mag magi magic magici magicia magician magicw
// Output: 
// "magician"

// Explanation: The code "magician" is unlockable because every 
// prefix—"m", "ma", "mag", "magi", "magic", "magici", and "magicia"—is present in 
// the list. Although "magicw" appears too, it fails since its prefix "magica" is missing.


// Example 2:
// ----------
// Input:
// a banana app appl ap apply apple
// Output: 
// "apple"  

// Explanation: Both "apple" and "apply" have every prefix in the list, but "apple" 
// comes first in alphabetical order.

// Example 3:
// ----------
// Input: 
// abc bc ab abcd
// Output: 
// ""

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
            t=t.children[ind];
        }
        t.end=true;
    }
    boolean haspref(String s){
        TrieNode t=root;
        for(int i=0;i<s.length()-1;i++){
            int ind=s.charAt(i)-'a';
            if(t.children[ind]==null){
                return false;
            }
            if(t.children[ind].end==true){
                t=t.children[ind];;
            }
            else{
                return false;
            }
        }
        return true;
    }
}

public class D27_LongestValidCode {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String[] inp=sc.nextLine().split(" ");
        Trie t=new Trie();
        for(String s:inp){
            t.insert(s);
        }
        StringBuilder res=new StringBuilder("");
        for(String s:inp){
            if(t.haspref(s)){
                if(s.length()>=res.length() && res.toString().compareTo(s)>0){
                    res=new StringBuilder(s);
                }
            }
        }
        System.out.print(res.toString());
        sc.close();
    }
}

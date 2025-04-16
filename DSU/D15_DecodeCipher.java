// In the world of secret codes and cryptography, you are entrusted with deciphering a hidden message. 
// You have two encoded keys, key1 and key2, both of equal length. Each character 
// in key1 is paired with the corresponding character in key2. 

// This relationship follows the standard rules of an equivalence cipher:
// • Self-Mapping: Every character inherently maps to itself.  
// • Mutual Mapping: If a character from key1 maps to one in key2, then that 
//   character in key2 maps back to the one in key1.  
// • Chain Mapping: If character A maps to B, and B maps to C, then A, B, and C 
//   are all interchangeable in this cipher.

// Using this mapping, you must decode a cipherText, by replacing every character 
// with the smallest equivalent character from its equivalence group. 
// The result should be the relatively smallest possible decoded message.


// Input Format:
// -------------
// Three space separated strings, key1 , key2 and cipherText

// Output Format:
// --------------
// Print a string, decoded message which is relatively smallest string of cipherText.

// Example 1: 
// input=
// attitude progress apriori
// output=
// aaogoog


// Explanation: The mapping pairs form groups: [a, p], [o, r, t], [g, i], [e, u], 
// [d, e, s]. By substituting each character in cipherText with the smallest from 
// its group, you decode the message to "aaogoog".


// Constraints:  
// • 1 <= key1.length, key2.length, cipherText.length <= 1000  
// • key1.length == key2.length  
// • key1, key2, and cipherText consist solely of lowercase English letters.


import java.util.*;

class DSU{
    HashMap<Character,Character> parent;
    DSU(){
        parent=new HashMap<>();
        char c='a';
        for(int i=0;i<26;i++){
            parent.put(c,c);
            c+=1;
        }
    }
    char find(char c){
        if(parent.get(c)!=c){
            parent.put(c,find(parent.get(c)));
        }
        return parent.get(c);
    }
    void union(char c1, char c2){
        char p1=find(c1);
        char p2=find(c2);
        if(p1<p2){
            parent.put(p2,p1);
        }
        else if(p2<p1){
            parent.put(p1,p2);
        }
    }
}

public class D15_DecodeCipher{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String k1=sc.next();
        String k2=sc.next();
        String cy=sc.next();
        StringBuilder res=new StringBuilder(cy);
        DSU du=new DSU();
        for(int i=0;i<k1.length();i++){
            du.union(k1.charAt(i),k2.charAt(i));
        }
        for(int i=0;i<res.length();i++){
            res.setCharAt(i,du.find(res.charAt(i)));
        }
        System.out.println(res.toString());
        sc.close();
    }
}

// In a forgotten realm, explorers often find ancient treasure maps written as 
// long strings of mysterious characters with no spaces. Luckily, they also carry 
// an ancient wordbook (pathBook) containing all the known names of places, 
// landmarks, and directions.

// Your task is to help the explorer decode the map by inserting spaces such that 
// each segment is a valid location or direction from the pathBook. Return all 
// possible ways to break the map string into a valid sequence of known locations.

// You can reuse entries from the pathBook as many times as needed.

// Example 1:
// ----------
// input:
// deserttemplegolds			//Trasure map
// desert temple gold golds	//pathBook

// output:
// [desert temple gold]

// Explanation: The map can be decoded directly into three known places.

// Example 2:
// ----------
// input:
// forestforesthill
// forest hill

// output:
// [forest forest hill]

// Explanation: The explorer can reuse 'forest' more than once.

// Example 3:
// ----------
// input:
// oceanmountaintemple
// mountain temple

// output:
// []

// Explanation: The map begins with 'ocean', which is missing from the pathBook, so no decoding is possible.


// Map Decoding Constraints:
// - 1 <= map.length <= 20
// - 1 <= pathBook.length <= 1000
// - 1 <= pathBook[i].length <= 10
// - All strings consist of lowercase English letters.
// - All entries in pathBook are unique.
// - Input is structured so the total number of valid decoded strings does not exceed 10^5.

import java.util.*;

public class D37_ValidSequence {
    private static void backtrack(int ind, ArrayList<String> currpath, String s, HashSet<String> pathbooks, ArrayList<String> res){
        if(ind==s.length()){
            res.add(String.join(" ",currpath));
            return;
        }
        for(int i=ind+1;i<=s.length();i++){
            String path=s.substring(ind, i);
            if(pathbooks.contains(path)){
                currpath.add(path);
                backtrack(i, currpath, s, pathbooks, res);
                currpath.remove(currpath.size()-1);
            }
        }

    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        sc.nextLine();
        String[] books=sc.nextLine().trim().split(" ");
        HashSet<String> pathbooks=new HashSet<String>(Arrays.asList(books));
        ArrayList<String> res=new ArrayList<>();
        backtrack(0,new ArrayList<String>(),s,pathbooks,res);
        System.out.println(res);
        sc.close();
    }
}

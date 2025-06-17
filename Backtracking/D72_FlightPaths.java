// Gopal would like to go on Tour, and planned a schedule.
// Airport authority has created a new way of issuing tickets.
// Gopal purchased a set of airline tickets, 
// each ticket contains the 'departure from' and 'arrival to'.

// Redesign the Gopal's tour schedule in an order.
// Gopal intially must begin his tour from BZA.
// Hence the tour schedule's start point should begin with BZA. 

// You are given a list of flight tickets which Gopal has purchased.
// Your task is to find out and return the tour schedule that has the least 
// lexical order. Gopal must use all the tickets and only once.

// Note:
// ------
// If there are multiple valid schedules, you should return the schedule 
// that has the smallest lexical order when read as a single string. 
// For example, the schedule ["BZA", "LGA"] has a smaller lexical order than ["BZA", "LGB"].

// All airports are represented by three capital letters.
// You may assume all tickets form at least one valid schedule.

// Input Format:
// -------------
// Single Line of tickets separated by comma, and each ticket separated by space, 
// Gopal's flight tickets.

// Output Format:
// --------------
// Print the schedule, which is smallest lexical order of tour schedule.


// Sample Input-1:
// ----------------
// DEL HYD,BZA DEL,BLR MAA,HYD BLR

// Sample Output-1:
// --------------------
// [BZA, DEL, HYD, BLR, MAA]


// Sample Input-2:
// ------------------
// BZA BLR,BZA CCU,BLR CCU,CCU BZA,CCU BLR

// Sample Output-2:
// ------------------
// [BZA, BLR, CCU, BZA, CCU, BLR]

import java.util.*;

public class D72_FlightPaths {
    static boolean found=false;
    static ArrayList<String> res=new ArrayList<>();
    private static void dfs(String curr, HashMap<String,ArrayList<String>> adj, HashMap<String,Boolean> vis, ArrayList<String> path, int n){
        if(found){
            return;
        }
        if(path.size()==n+1){
            res=new ArrayList<>(path);
            found=true;
            return;
        }
        if(!adj.containsKey(curr)){
            return;
        }
        for(String nex:adj.get(curr)){
            if(!vis.get(curr+"-"+nex)){
                vis.put(curr+"-"+nex,true);
                path.add(nex);
                dfs(nex,adj,vis,path,n);
                path.remove(path.size()-1);
                vis.put(curr+"-"+nex,false);
            }
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        ArrayList<ArrayList<String>> paths=new ArrayList<>();
        String[] inp=sc.nextLine().split(",");
        HashMap<String,Boolean> vis=new HashMap<>();
        HashMap<String,ArrayList<String>> adj=new HashMap<>();
        for(String i:inp){
            String[] ii=i.split(" ");
            ArrayList<String> edge=new ArrayList<>();
            edge.add(ii[0]);
            edge.add(ii[1]);
            paths.add(edge);
            if(!adj.containsKey(ii[0])){
                adj.put(ii[0],new ArrayList<String>());
            }
            adj.get(ii[0]).add(ii[1]);
            vis.put(ii[0]+"-"+ii[1],false);
        }
        for(String k:adj.keySet()){
            Collections.sort(adj.get(k));
        }
        ArrayList<String> path=new ArrayList<>();
        path.add("BZA");
        dfs("BZA",adj,vis,path,paths.size());
        System.out.println(res);
        sc.close();
    }
}

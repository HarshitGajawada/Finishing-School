// Imagine you're the master chef in a renowned kitchen, tasked with preparing a 
// spectacular dinner consisting of numDishes unique dishes, labeled from 
// 0 to numDishes - 1. However, the recipes have dependencies—certain dishes can 
// only be prepared after completing others. You’re given a list called dependecies, 
// where each element dependencies[i] = [Xi, Yi] means that you must finish 
// preparing dish Yi before starting dish Xi.

// For instance, the pair [2, 1] implies that to create dish 2, 
// dish 1 must be prepared first.

// Return the ordering of dishes that a chef should take to finish all dishes.
// 	- the result set should follow the given order of conditions.
// 	- If it is impossible to complete all dishes, return an empty set.


// Input Format:
// -------------
// Line-1: An integer numDishes, number of Dishes.
// Line-2: An integer m, number of dependencies.
// Next m lines: Two space separated integers, Xi and Yi.

// Output Format:
// --------------
// Return a list of integers, the ordering of dishes that a chef should finish.

// Example 1:
// ------------
// Input=
// 4
// 3
// 1 2
// 3 0
// 0 1
// Output=
// [2, 1, 0, 3]


// Explanation: There are 4 dishes. Since dish 1 requires dish 2, dish 3 requires 
// dish 0 and dish 0 requires dish 1, you can prepare dishes in the order 2 1 0 3.


// Example 2:
// ----------
// Input=
// 2
// 2
// 1 0
// 0 1
// Output=
// []

// Explanation: There are 2 dishes, but dish 1 depends on dish 0 and dish 0 depends 
// on dish 1. This circular dependency makes it impossible to prepare all dishes.

// Constraints:

// - 1 <= numDishes <= 2000  
// - 0 <= m <= 5000  
// - dependencies[i].length == 2  
// - 0 <= Xi, Yi < numDishes  
// - All the dependency pairs are unique.


import java.util.*;

public class D20_Kitchen {
    private static boolean cycle;
    private static void dfs(int node, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> res, int[] vis){
        vis[node]=1;
        for(int neigh:adj.get(node)){
            if(vis[neigh]==1){
                cycle=true;
                return;
            }
            if(vis[neigh]==0){
                dfs(neigh,adj,res,vis);
            }
        }
        vis[node]=2;
        res.add(node);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int r=sc.nextInt();
        cycle=false;
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        int[] indegree=new int[n];
        for(int i=0;i<r;i++){
            int x=sc.nextInt();
            int y=sc.nextInt();
            indegree[y]+=1;
            adj.get(x).add(y);
        }
        int independent=0;
        ArrayList<Integer> res=new ArrayList<>();
        for(int in:indegree){
            if(in==0){
                independent+=1;
            }
            if(independent>1){
                System.out.println(res);
                sc.close();
                return;
            }
        }
        int[] vis=new int[n];
        for(int i=0;i<n;i++){
            if(vis[i]==0){
                dfs(i,adj,res,vis);
            }
        }
        if(cycle){
            System.out.println(res);
            sc.close();
            return;
        }

        System.out.println(res);
        sc.close();
    }
}

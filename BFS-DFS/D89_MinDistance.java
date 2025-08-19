// There are N cities in a state.
// The cities are connected with two types of roadways:
// 1) concrete roadway 2) asphalt roadway.
// The roadways may be parallel.

// You are given the lists of concrete roadways and asphalt roadways
// between the cities. All roadways are unidirectional.
// Concrete_Roadway[i,j] indiactes: a concrete road from city-i to city-j. Similarly,
// Asphalt[i,j] indiactes: an asphalt road from city-i to city-j. Similarly,

// Your task is to find and return the list of lengths of the shortest paths from 
// city-0 to city-P, where P start from 0 to N-1. And the path should contains 
// alternative roadways like as follows: concrete - asphalt - concrete -asphalt --etc
// or vice versa. If there is no such shortest path exist print -1.

// Input Format:
// -------------
// Line-1: 3 space separated integers N, C & A, Number of cities, routes between the cities.
// 		number of concrete roads and number of asphalt roads
// Next C lines: Two space separated integers, concrete road between city-i to city-j.		
// Next A lines: Two space separated integers, asphalt road between city-i to city-j.		

// Output Format:
// --------------
// Print the list of lengths, the shortest paths.


// Sample Input-1:
// ---------------
// 4 2 1
// 0 1
// 2 3
// 1 2

// Sample Output-1:
// ----------------
// 0 1 2 3

// Sample Input-2:
// ---------------
// 4 2 1
// 1 0
// 2 3
// 1 2

// Sample Output-2:
// ----------------
// 0 -1 -1 -1


// Sample Input-3:
// ---------------
// 4 3 2
// 1 0
// 1 2
// 2 3
// 0 1
// 1 2

// Sample Output-3:
// ----------------
// 0 1 2 -1

import java.util.*;

public class D89_MinDistance {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int c=sc.nextInt();
        int a=sc.nextInt();
        ArrayList<ArrayList<Integer>> con=new ArrayList<>();
        ArrayList<ArrayList<Integer>> asp=new ArrayList<>();
        for(int i=0;i<n;i++){
            con.add(new ArrayList<>());
        }
        for(int i=0;i<n;i++){
            asp.add(new ArrayList<>());
        }
        for(int i=0;i<c;i++){
            int src=sc.nextInt();
            int dst=sc.nextInt();
            con.get(src).add(dst);
        }
        for(int i=0;i<a;i++){
            int src=sc.nextInt();
            int dst=sc.nextInt();
            asp.get(src).add(dst);
        }
        int[] res=new int[n];
        Arrays.fill(res,-1);
        res[0]=0;
        Queue<int[]> q=new LinkedList<>();
        HashSet<String> vis=new HashSet<>();
        q.add(new int[]{0,0,0});
        q.add(new int[]{0,1,0});
        vis.add(0+","+0);
        vis.add(0+","+1);
        while(!q.isEmpty()){
            int[] curr=q.poll();
            if(res[curr[0]]==-1){
                res[curr[0]]=curr[2];
            }
            else{
                res[curr[0]]=Math.min(res[curr[0]],curr[2]);
            }
            if(curr[1]==0){
                for(int neigh:asp.get(curr[0])){
                    if(!vis.contains(neigh+","+1)){
                        q.add(new int[]{neigh,1,curr[2]+1});
                        vis.add(neigh+","+1);
                    }
                }
            }
            else{
                for(int neigh:con.get(curr[0])){
                    if(!vis.contains(neigh+","+0)){
                        q.add(new int[]{neigh,0,curr[2]+1});
                        vis.add(neigh+","+0);
                    }
                }
            }
        }
        Arrays.stream(res).forEach(d->System.out.print(d+" "));
        sc.close();
    }
}

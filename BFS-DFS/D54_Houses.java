// Arjun wants to build some homes in a land of size R*C.
// He wanted to construct homes in rectangular shape.
// The place which is remained will be used for gradening.
// Accordingly he has prepared the plan and given as
// an 2d array plan[][], where 1 indicates home, and 0 indicates garden area.

// A home is set of cells with value 1 in rectangular shape.
// He wants to findout all the homes in the plan and store their co-ordinates in 
// the following order, coords[i] = [x1,y1,x2,y2], where (x1,y1) is the starting
// co-ordinate (top left corner), and (x2,y2) is the ending co-ordinate 
// (bottom right corner) of i-th home.

// Your task is to help Arjun to find all the homes and return the coords[][] of 
// all the homes from top left corner to bottom right corner.

// NOTE: No two homes are adjacent to each other in 4 directions,
// (left, right, top, bottom).

// Input Format:
// -------------
// Line-1: Two integers R and C, size of the land.
// Next R lines: C space separated integers, either 0 or 1
// 0- represents garden area land and 1- represents the home.

// Output Format:
// --------------
// Print 2d array, the co-ordinates of all homes.


// Sample Input-1:
// ---------------
// 2 3
// 1 0 0
// 0 1 1
 
// Sample Output-1:
// ----------------
// [0, 0, 0, 0][1, 1, 1, 2]


// Sample Input-2:
// ---------------
// 4 4
// 1 1 0 1
// 0 0 0 0
// 1 1 0 1
// 1 1 0 1
 
// Sample Output-2:
// ----------------
// [0, 0, 0, 1][0, 3, 0, 3][2, 0, 3, 1][2, 3, 3, 3]

import java.util.*;

public class D54_Houses {
    private static int[][] dirs={{0,1},{1,0},{-1,0},{0,-1}};
    public static List<Integer> bfs(int[][] arr, int m, int n, int i, int j){
        List<Integer> home=new ArrayList<>();
        home.add(i);
        home.add(j);
        arr[i][j]=0;
        Queue<int[]> q=new LinkedList<>();
        q.add(new int[]{i,j});
        int temp1=i;
        int temp2=j;
        while(!q.isEmpty()){
            int[] curr=q.poll();
            int r=curr[0];
            int c=curr[1];
            for(int[] dir:dirs){
                int newr=r+dir[0];
                int newc=c+dir[1];
                if(newr>=0 && newr<n && newc>=0 && newc<m && arr[newr][newc]==1){
                    arr[newr][newc]=0;
                    temp1=Math.max(temp1,newr);
                    temp2=Math.max(temp2,newc);
                    q.offer(new int[]{newr,newc});
                }
            }
        }
        home.add(temp1);
        home.add(temp2);
        return home;
    }
    public static List<List<Integer>> getHomes(int[][] arr, int m, int n){
        List<List<Integer>> res=new ArrayList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(arr[i][j]==1){
                    res.add(bfs(arr,m,n,i,j));
                }
            }
        }
        return res;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);    
        int m=sc.nextInt();
        int n=sc.nextInt();
        int[][] arr=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                arr[i][j]=sc.nextInt();
            }
        }
        List<List<Integer>> res=getHomes(arr,m,n);
        for(List<Integer> i:res){
            System.out.print(i);
        }
        sc.close();
    }
}

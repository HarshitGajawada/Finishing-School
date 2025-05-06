// You are navigating a spaceship through a galaxy represented as an m x n space map.  
// The spaceship starts from the top-left sector (sector[0][0]) and its mission is 
// to safely reach the bottom-right sector (sector[m-1][n-1]).

// Each sector on the map can either be clear (0) or blocked by an asteroid field (1).  
// The spaceship can only move right or downward at any moment.  
// It cannot pass through sectors with asteroid fields.

// Find the total number of distinct safe routes the spaceship can take to reach 
// its destination at the bottom-right corner.


// Input format:
// -------------
// 2 space seperated integers, m & n
// next m lines representing the Map 

// Output format:
// --------------
// An integer, the total number of distinct safe routes



// Example 1:
// ----------
// Input:
// 3 3
// 0 0 0
// 0 1 0
// 0 0 0

// Output:
// 2

// Explanation:  
// There’s one asteroid field blocking the center of the 3×3 map.  
// Two possible safe navigation paths:
// - Move → Move → Down → Down
// - Down → Down → Move → Move

// Example 2:
// ---------
// Input:
// 2 2
// 0 1
// 0 0

// Output:
// 1


// Constraints:
// - m == sectorMap.length
// - n == sectorMap[i].length
// - 1 <= m, n <= 100
// - sectorMap[i][j] is either 0 (clear) or 1 (asteroid field)

import java.util.*;

public class D42_ReachEnd {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        int[][] space=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                space[i][j]=sc.nextInt();
            }
        }
        int[][] dp=new int[m][n];
        if(space[0][0]==0){
            dp[0][0]=1;
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(space[i][j]==1){
                    continue;
                }
                if(i>0){
                    dp[i][j]+=dp[i-1][j];
                }
                if(j>0){
                    dp[i][j]+=dp[i][j-1];
                }
            }
        }
        // System.out.println(Arrays.deepToString(dp));
        System.out.println(dp[m-1][n-1]);
        sc.close();
    }
}

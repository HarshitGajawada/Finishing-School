// You are managing a fleet of exploratory spacecraft, each carrying containers 
// composed of two types of critical resources: 
// fuel units (represented by '0') and oxygen tanks (represented by '1'). 
// You're given a list 'containers', where each container is represented by a 
// binary string indicating its resource composition, 
// along with two integers: 'fuelLimit' (maximum allowed fuel units) and 
// 'oxygenLimit' (maximum allowed oxygen tanks).

// Your goal is to select the largest possible subset of containers such that the 
// total number of fuel units does not exceed 'fuelLimit' and the total number of 
// oxygen tanks does not exceed 'oxygenLimit'.

// Input format:
// -------------
// Line 1: Space seperated strings, represents the container strings
// Line 2: Two space separated integers, represents fuelLimit & oxygenLimit

// Output format:
// --------------
// An integer, largest possible subset of containers.


// Example 1:
// ----------
// Input=
// 10 0001 111001 1 0
// 5 3

// Output=
// 4

// Explanation: The largest subset meeting the constraints is {"10", "0001", "1", "0"}.
// - Total fuel units = 5 (within limit)
// - Total oxygen tanks = 3 (within limit)
// Container "111001" cannot be included as it exceeds the oxygen tank limit.


// Example 2:
// ----------
// Input=
// 10 0 1
// 1 1

// Output=
// 2

// Explanation: The largest subset meeting the constraints is {"0", "1"}.
// - Total fuel units = 1
// - Total oxygen tanks = 1


// Constraints:
// - 1 <= containers.length <= 600
// - 1 <= containers[i].length <= 100
// - 'containers[i]' consists only of digits '0' and '1'.
// - 1 <= fuelLimit, oxygenLimit <= 100

import java.util.*;

public class D43_SubsetContainers {
    // private static int solve(int ind, String[] containers, int f, int o, int fl, int ol, int[] fcnts){
    //     if(ind>=containers.length){
    //         return 0;
    //     }
    //     if(f>fl || o>ol){
    //         return 0;
    //     }
    //     int pick=0;
    //     if(f+fcnts[ind]<=fl && o+containers[ind].length()-fcnts[ind]<=ol){
    //         pick=1+solve(ind+1,containers,f+fcnts[ind],o+containers[ind].length()-fcnts[ind],fl,ol,fcnts);
    //     }
    //     int npick=solve(ind+1,containers,f,o,fl,ol,fcnts);
    //     return Math.max(pick,npick);
    // }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String[] containers=sc.nextLine().split(" ");
        int fl=sc.nextInt();
        int ol=sc.nextInt();
        int n=containers.length;
        int[] fcnts=new int[n];
        for(int i=0;i<n;i++){
            for(char c:containers[i].toCharArray()){
                if(c=='0'){
                    fcnts[i]+=1;
                }
            }
        }
        int[][][] dp=new int[n+1][fl+1][ol+1];
        for(int i=1;i<=n;i++){
            int cf=fcnts[i-1];
            int co=containers[i-1].length()-fcnts[i-1];
            for(int f=0;f<=fl;f++){
                for(int o=0;o<=ol;o++){
                    dp[i][f][o]=dp[i-1][f][o];
                    if(f>=cf && o>=co){
                        dp[i][f][o]=Math.max(dp[i][f][o],1+dp[i-1][f-cf][o-co]);
                    }
                }
            }
        }
        System.out.println(dp[n][fl][ol]);
        // System.out.println(solve(0,containers,0,0,fl,ol,fcnts));
        // sc.close();
    }
}

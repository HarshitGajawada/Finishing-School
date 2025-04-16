// You are given an integer array nums and two integers l and r. Your task is to 
// analyze the volatility of a sequence of values. The volatility of a sequence is 
// defined as the difference between the maximum and minimum values in that sequence.

// You need to determine the sequence with the highest volatility among all 
// sequences of length between l and r (inclusive).

// Return the highest volatility. If no such sequence exists, return -1.

// Input Format:
// -------------
// Line-1: 3 space separated integers, n, l, r
// Line-2: n space separated integers, nums[].

// Output Format:
// -------------
// An integer, the highest volatility.


// Sample Input-1:
// ---------------
// 5 2 3
// 8 3 1 6 2

// Sample Output-1:
// ----------------
// 7

// Explanation:
// ------------
// The possible sequences of length between l = 2 and r = 3 are:

// [8, 3] with a volatility of 8−3=5
// [3, 1] with a volatility of 3−1=2
// [1, 6] with a volatility of 6−1=5
// [8, 3, 1] with a volatility of 8−1=7
// The sequence [8, 3, 1] has the highest volatility of 7.

// Sample Input-2:
// ---------------
// 4 2 4
// 5 5 5 5

// Sample Output-2:
// ----------------
// 0

// Explanation:
// ------------
// All possible sequences have no volatility as the maximum and minimum values 
// are the same, resulting in a difference of 0.
 

import java.util.*;

public class D3_VolatilityAnalyzer {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        @SuppressWarnings("unused")
        int l=sc.nextInt();
        int r=sc.nextInt();
        int[] nums=new int[n];
        for(int i=0;i<n;i++){
            nums[i]=sc.nextInt();
        }
        int res=0;
        
        ArrayDeque<Integer> inc=new ArrayDeque<>();
        ArrayDeque<Integer> dec=new ArrayDeque<>();
        for(int i=0;i<n;i++){
            while(!inc.isEmpty() && i-inc.peekFirst()>=r){
                inc.pollFirst();
            }
            while(!inc.isEmpty() && nums[inc.peekLast()]>nums[i]){
                inc.pollLast();
            }
            while(!dec.isEmpty() && i-dec.peekFirst()>=r){
                dec.pollFirst();
            }
            while(!dec.isEmpty() && nums[dec.peekLast()]<nums[i]){
                dec.pollLast();
            }
            inc.addLast(i);
            dec.addLast(i);
            if(i>=r-1){
                res=Math.max(res,nums[dec.peekFirst()]-nums[inc.peekFirst()]);
            }
        }
        
        System.out.println(res);
        sc.close();
    }
}
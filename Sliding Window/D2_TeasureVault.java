// You are a treasure hunter exploring an ancient vault filled with 
// treasure boxes. The vault is represented as an array treasures of 
// n integers, where each integer corresponds to the value of a treasure. 
// You have a special key that allows you to scan and select treasures 
// from a sub-vault (a segment of the array) of size k. Additionally, 
// you have a magical power factor f and a priority filter x.

// The priority-weighted treasure sum of a sub-vault is calculated as follows:
// 	1. Count the occurrences of each treasure value in the sub-vault.
// 	2. Assign a priority score to each treasure based on its frequency 
// 	multiplied by the treasure's value raised to the power of f 
// 	(i.e., priority_score[treasure] = frequency[treasure] * (value^f)).
// 	3. Select only the top x treasures based on their priority scores. 
// 	If two treasures have the same priority score, the treasure with 
// 	the higher value is prioritized.
// 	4. Calculate the total value of the selected treasures.

// Your task is to return an integer array priority_sums of length n - k + 1, 
// where priority_sums[i] represents the priority-weighted treasure sum for 
// the sub-vault corresponding to treasures[i..i + k - 1].

// Input Format:
// ---------------
// Line-1: Four space separated integers, N, K, X, F
// Line-2: N space separated integers, boxes[].

// Output Format:
// -----------------
// An integer array, priority_sums[], of length n - k + 1


// Sample Input-1:
// -----------------
// 8 5 2 2
// 1 2 3 1 2 2 3 4

// Sample Output-1:
// --------------------
// [7, 9, 10, 7]

// Explanation:
// We calculate the priority-weighted treasure sum for each sub-vault:

// 1. Sub-vault 1: [1, 2, 3, 1, 2]
//    - Frequencies: {1: 2, 2: 2, 3: 1}
//    - Priority scores:
//      - 1 → 2 * (1^2) = 2
//      - 2 → 2 * (2^2) = 8
//      - 3 → 1 * (3^2) = 9
//    - Top 2 treasures by priority: 3 (score 9) and 2 (score 8).
//    - Total value: 2 + 3 + 2  = 7.

// 2. Sub-vault 2: [2, 3, 1, 2, 2]
//    - Frequencies: {2: 3, 3: 1, 1: 1}
//    - Priority scores:
//      - 2 → 3 * (2^2) = 12
//      - 3 → 1 * (3^2) = 9
//      - 1 → 1 * (1^2) = 1
//    - Top 2 treasures by priority: 2 (score 12) and 3 (score 9).
//    - Total value: 2 + 2 + 2 + 3 = 9.

// 3. Sub-vault 3: [3, 1, 2, 2, 3]
//    - Frequencies: {3: 2, 2: 2, 1: 1}
//    - Priority scores:
//      - 3 → 2 * (3^2) = 18
//      - 2 → 2 * (2^2) = 8
//      - 1 → 1 * (1^2) = 1
//    - Top 2 treasures by priority: 3 (score 18) and 2 (score 8).
//    - Total value: 3 + 2 + 2 + 3 = 10.

// 4. Sub-vault 4: [1, 2, 2, 3, 4]
//    - Frequencies: {1: 1, 2: 2, 3: 1, 4: 1}
//    - Priority scores:
//      - 2 → 2 * (2^2) = 8
//      - 3 → 1 * (3^2) = 9
//      - 4 → 1 * (4^2) = 16
//      - 1 → 1 * (1^2) = 1
//    - Top 2 treasures by priority: 4 (score 16) and 3 (score 9).
//    - Total value: 3 + 4  = 7.

// Sample Input-2:
// -----------------
// 6 3 2 1
// 5 5 6 7 5 6

// Sample Output-1:
// --------------------
// [16, 13, 13, 13]

// Constraints:
// 1. 1 <= n == treasures.length <= 50
// 2. 1 <= treasures[i] <= 50
// 3. 1 <= x <= k <= treasures.length
// 4. 1 <= f <= 10

import java.util.*;

public class D2_TeasureVault{
    private static double calc(int a,int b,int c){
        return a*(Math.pow(b,c));
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        int x=sc.nextInt();
        int fact=sc.nextInt();
        int[] boxes=new int[n];
        for(int i=0;i<n;i++){
            boxes[i]=sc.nextInt();
        }
        ArrayList<Long> res=new ArrayList<>();
        HashMap<Integer,Integer> f=new HashMap<>();
        for(int i=0;i<k;i++){
            f.put(boxes[i],f.getOrDefault(boxes[i],0)+1);
        }
        PriorityQueue<double[]> mh=new PriorityQueue<>((a,b)->Double.compare(b[0],a[0])); 
        for(int b:f.keySet()){
            mh.add(new double[]{calc(f.get(b),b,fact),b});
        }
        int r=0;
        int c=0;
        while(c<x && !mh.isEmpty()){
            double[] cc=mh.poll();
            r+=cc[1]*f.get((int)cc[1]);
            c+=1;
        }
        res.add((long)r);
        for(int i=1;i<=n-k;i++){
            f.put(boxes[i+k-1],f.getOrDefault(boxes[i+k-1],0)+1);
            f.put(boxes[i-1],f.get(boxes[i-1])-1);
            if(f.get(boxes[i-1])==0){
                f.remove(boxes[i-1]);
            }
            mh=new PriorityQueue<>((a,b)->Double.compare(b[0],a[0])); 
            for(int b:f.keySet()){
                mh.add(new double[]{calc(f.get(b),b,fact),b});
            }
            r=0;
            c=0;
            while(c<x && !mh.isEmpty()){
                double[] cc=mh.poll();
                r+=cc[1]*f.get((int)cc[1]);
                c+=1;
            }
            res.add((long)r);
        }
        System.out.println(res);
        sc.close();
    }
}
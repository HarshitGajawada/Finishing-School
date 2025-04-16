// Imagine you're the chief engineer aboard a futuristic spaceship. The ship is 
// powered by N series of fuel cells arranged in a row, where each fuel cell holds
// a specific amount of energy measured in megajoules. Your job is to manage these 
// fuel cells by performing two main operations:

// Option 1: Calculate the total energy available in a consecutive block of fuel 
//           cells between indices start and end (inclusive).
// Option 2: Update the energy level with given 'newEnergy' of a specific 'index' 
//           fuel cell when it's refilled.

// Input Format:
// -------------
// Line-1: Two integers N and Q, where N is the number of fuel cells and Q is the number of operations.
// Line-2: N space separated integers.
// next Q lines: Three integers option, start/index and end/newEnergy.

// Output Format:
// --------------
// An integer result, for every totalEnergy.


// Example 1:
// -----------
// Input:
// 8 5
// 1 2 13 4 25 16 17 8
// 1 2 6		//totalEnerge
// 1 0 7		//totalEnerge
// 2 2 18		//recharge
// 2 4 17		//recharge
// 1 2 7		//totalEnerge

// Output:
// 75
// 86
// 80


// Example 2:
// ----------
// Input:
// 16 1
// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
// 1 0 15

// Output:
// 136


// Constraints

// - 1 <= N <= 3*10^4  
// - -100 <= fuelCells[i] <= 100  
// - 0 <= index < fuelCells.length  
// - -100 <= newEnergy <= 100  
// - 0 <= start <= end < fuelCells.length  
// - At most 3*10^4 calls will be made to recharge and totalEnergy.

import java.util.*;

class FenwickTree {
    int[] bits;
    int n;
    FenwickTree(int s){
        n=s;
        bits=new int[n+1];
    }
    void build(int[] nums){
        for(int i=0;i<nums.length;i++){
            update(i, nums[i]);
        }
    }
    void update(int i, int val){
        i+=1;
        while(i<=n){
            bits[i]+=val;
            i+=i&-i;
        }
    }
    int query(int i){
        i+=1;
        int sum=0;
        while(i>0){
            sum+=bits[i];
            i-=i&-i;
        }
        return sum;
    }
}

public class FuelCells{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int q=sc.nextInt();
        int[] nums=new int[n];
        for(int i=0;i<n;i++){
            nums[i]=sc.nextInt();
        }
        FenwickTree fen=new FenwickTree(n);
        fen.build(nums);
        for(int i=0;i<q;i++){
            int opt=sc.nextInt();
            if(opt==1){
                int l=sc.nextInt();
                int r=sc.nextInt();
                System.out.println(fen.query(r)-fen.query(l-1));
            }
            else{
                int ind=sc.nextInt();
                int val=sc.nextInt();
                fen.update(ind+1,val-nums[ind]);
                nums[ind]=val;
            }
        }
        sc.close();
    }
}


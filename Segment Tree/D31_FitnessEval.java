// Coach Arjun is training a cricket team and is experimenting with a new fitness 
// evaluation drill. He provided the fitness scores of N players in the team. As 
// part of the drill, he asked the team manager to keep track and perform these 
// two specific operations on the players' fitness scores:

// 1. bestFitness(start, end) - Report the maximum fitness score among the players 
//    whose jersey numbers lie between the positions start and end inclusive.
// 2. improveFitness(index, newScore) - Update the fitness score of the player at 
//    the specific index position with a new fitness score newScore.

// Your task is to efficiently handle these requests by using a Segment Tree data structure.

// Input Format:  
// -------------
// Line-1: Two integers N and Q, representing the number of players and the total 
//         number of queries respectively.
// Line-2: N space-separated integers representing the initial fitness scores of 
//         the players.
// The next Q lines: Each line contains three integers: 
//     - First integer (option) specifies the type of query:
//       - If option = 1, the next two integers (start, end) represent the range 
//         of jersey numbers (inclusive) for which to report the maximum fitness.
//       - If option = 2, the next two integers (index, newScore) represent the 
//         player's index to update and their new fitness score.

// Output Format:  
// --------------
// - Output an integer value for every bestFitness query, representing the maximum 
//   fitness score within the specified range.

// Sample Input:  
// -------------

// 8 5
// 1 2 13 4 25 16 17 28
// 1 2 6        
// 1 0 7        
// 2 2 18       
// 2 4 36       
// 1 2 7       


// Sample Output:  
// --------------
// 25
// 28
// 36

import java.util.*;

class SegmentTree {
    int size;
    int[] tree;
    int[] arr;
    SegmentTree(int[] inp){
        arr=inp;
        size=arr.length;
        tree=new int[4*size];
        build(0,0,size-1);
    }
    void build(int node, int start, int end){
        if(start==end){
            tree[node]=arr[start];
        }
        else{
            int mid=(start+end)/2;
            build(2*node+1,start,mid);
            build(2*node+2,mid+1,end);
            tree[node]=Math.max(tree[2*node+1],tree[2*node+2]); 
        }
    }
    int query(int l, int r){
        return querycal(0,0,size-1,l,r);
    }
    int querycal(int node, int start, int end, int l, int r){
        if(r<start || l>end){
            return 0;
        }
        if(l<=start && r>=end){
            return tree[node];
        }
        int mid=(start+end)/2;
        return Math.max(querycal(2*node+1, start, mid, l, r),querycal(2*node+2, mid+1, end, l, r));
    }
    void update(int ind, int val){
        updateutil(0,0,size-1,ind,val);
    }
    void updateutil(int node, int start, int end, int ind, int val){
        if(start==end){
            tree[node]=val;
            return;
        }
        int mid=(start+end)/2;
        if(ind<=mid){
            updateutil(2*node+1, start, mid, ind, val);
        }
        else{
            updateutil(2*node+2, mid+1, end, ind, val);
        }
        tree[node]=Math.max(tree[2*node+1],tree[2*node+2]);
    }
}

public class D31_FitnessEval {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int q=sc.nextInt();
        int[] scores=new int[n];
        for(int i=0;i<n;i++){
            scores[i]=sc.nextInt();
        }
        SegmentTree st=new SegmentTree(scores);
        for(int i=0;i<q;i++){
            int opt=sc.nextInt();
            if(opt==1){
                System.out.println(st.query(sc.nextInt(),sc.nextInt()));
            }
            else{
                st.update(sc.nextInt()-1,sc.nextInt());
            }
        }
        sc.close();
    }
}

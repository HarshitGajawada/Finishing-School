// Given the in-order and post-order traversals of a binary tree, construct 
// the original binary tree. For the given Q number of queries, 
// each query consists of a lower level and an upper level. 
// The output should list the nodes in the order they appear in a level-wise.

// Input Format:
// -------------
// An integer N representing the number nodes.
// A space-separated list of N integers representing the similar to in-order traversal.
// A space-separated list of N integers representing the similar to post-order traversal.
// An integer Q representing the number of queries.
// Q pairs of integers, each representing a query in the form:
// Lower level (L)
// Upper level (U)

// Output Format:
// For each query, print the nodes in order within the given depth range

// Example
// Input:
// 7
// 4 2 5 1 6 3 7
// 4 5 2 6 7 3 1
// 2
// 1 2
// 2 3
// Output:
// [1, 2, 3]
// [2, 3, 4, 5, 6, 7]

// Explanation:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7
// Query 1 (Levels 1 to 2): 1 2 3
// Query 2 (Levels 2 to 3): 2 3 4 5 6 7


import java.util.*;

class Node{
    int val;
    Node left,right;
    Node(int v){
        val=v;
        left=null;
        right=null;
    }
}

public class D5_InPost{
    private static Node construct(int[] inOrd, int l1, int r1, int[] postOrd, int l2, int r2){
        if(l1>=r1|| l2>=r2){
            return null;
        }
        int r=postOrd[r2-1];
        int root=l1;
        Node curr=new Node(r);
        for(int i=l1;i<r1;i++){
            if(inOrd[i]==r){
                root=i;
                break;
            }
        }
        int lc=root-l1;
        curr.left=construct(inOrd,l1,root,postOrd,l2,l2+lc);
        curr.right=construct(inOrd,root+1,r1,postOrd,l2+lc,r2-1);
        return curr;
    }
    private static ArrayList<ArrayList<Integer>> levelOrder(Node curr){
        ArrayList<ArrayList<Integer>> levels=new ArrayList<>();
        if(curr==null){
            return levels;
        }
        Queue<Node> q=new LinkedList<>();
        q.add(curr);
        while(!q.isEmpty()){
            int s=q.size();
            ArrayList<Integer> level=new ArrayList<>();
            for(int i=0;i<s;i++){
                Node c=q.poll();
                level.add(c.val);
                if(c.left!=null){
                    q.add(c.left);
                }
                if(c.right!=null){
                    q.add(c.right);
                }
            }
            levels.add(level);
        }
        return levels;
    }
    
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Node root;
        int[] inOrd=new int[n];
        int[] postOrd=new int[n];
        for(int i=0;i<n;i++){
            inOrd[i]=sc.nextInt();
        }
        for(int i=0;i<n;i++){
            postOrd[i]=sc.nextInt();
        }
        root=construct(inOrd,0,n,postOrd,0,n);
        // preOrder(root);
        int q=sc.nextInt();
        int[][] queries=new int[q][2];
        for(int i=0;i<q;i++){
            for(int j=0;j<2;j++){
                queries[i][j]=sc.nextInt();
            }
        }
        ArrayList<ArrayList<Integer>> levels=levelOrder(root);
        // System.out.println(levels);
        for(int[] que:queries){
            int l=que[0];
            int r=que[1];
            ArrayList<Integer> nn=new ArrayList<>();
            for(int i=l;i<=r;i++){
                nn.addAll(levels.get(i-1));
            }
            System.out.println(nn);
            sc.close();
        }
    }
}
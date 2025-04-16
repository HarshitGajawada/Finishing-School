// Construct Tree from the given Level-order and In-order.
// Compute the Depth and Sum of the Deepest nodes in the Binary tree

// Input Format:
// -------------
// An integer N representing the number of nodes.
// A space-separated list of N integers representing the in-order traversal.
// A space-separated list of N integers representing the level-order traversal.

// Output Format:
// --------------
// Print two values:
// ->The maximum number of levels.
// ->The sum of all node values at the deepest level.

// Example:
// -------------
// Input:
// 11
// 7 8 4 2 5 9 11 10 1 6 3
// 1 2 3 4 5 6 7 9 8 10 11

// Output:
// 6 11

// Explanation:
// The binary tree structure:
//            1
//          /   \
//        2       3
//       / \     /
//      4   5   6
//     /     \   
//    7       9
//     \       \
//      8      10
//             /
//           11
// Maximum Depth: 6
// nodes at the Deepest Level (6): 11
// Sum of nodes at Level 6: 11


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

public class D5_InLevel{
    private static Node construct(int[] inOrd, int l1, int r1, ArrayList<Integer> levelOrd){
        if(l1>r1 || levelOrd.isEmpty()){
            return null;
        }
        Node curr=new Node(levelOrd.get(0));
        int i=-1;
        for(int ii=l1;ii<=r1;ii++){
            if(inOrd[ii]==levelOrd.get(0)){
                i=ii;
                break;
            }
        }
        ArrayList<Integer> left=new ArrayList<>();
        ArrayList<Integer> right=new ArrayList<>();
        for(int v=1;v<levelOrd.size();v++){
            int val=levelOrd.get(v);
            for(int ind=l1;ind<i;ind++){
                if(inOrd[ind]==val){
                    left.add(inOrd[ind]);
                    break;
                }
            }
            for(int ind=i+1;ind<=r1;ind++){
                if(inOrd[ind]==val){
                    right.add(inOrd[ind]);
                    break;
                }
            }
        }
        curr.left=construct(inOrd,l1,i-1,left);
        curr.right=construct(inOrd,i+1,r1,right);
        return curr;
    }
    private static int[] deep(Node root) {
        if(root==null) return new int[]{};
        Queue<Node> q=new LinkedList<>();
        q.add(root);
        int s=0;
        int c=0;
        while(!q.isEmpty()){
            int size=q.size();
            s=0; 
            for(int i=0;i<size;i++){
                Node curr=q.poll();
                s+=curr.val;
                if (curr.left!=null) q.add(curr.left);
                if (curr.right!=null) q.add(curr.right);
            }
            c+=1;
        }
        int[] res=new int[]{c,s};
        return res;
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] inOrd=new int[n];
        int[] levelOrd=new int[n];
        for(int i=0;i<n;i++){
            inOrd[i]=sc.nextInt();
        }
        for(int i=0;i<n;i++){
            levelOrd[i]=sc.nextInt();
        }
        ArrayList<Integer> l=new ArrayList<>();
        for(int i:levelOrd){
            l.add(i);
        }
        Node root=construct(inOrd,0,n-1,l);
        int[] res=deep(root);
        System.out.print(res[0]+" "+res[1]);
        sc.close();
    }
}
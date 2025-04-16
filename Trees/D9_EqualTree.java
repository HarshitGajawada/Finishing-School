// Balbir Singh is working with networked systems, where servers are connected 
// in a hierarchical structure, represented as a Binary Tree. Each server (node) has 
// a certain processing load (integer value).

// Balbir has been given a critical task: split the network into two balanced 
// sub-networks by removing only one connection (edge). The total processing 
// load in both resulting sub-networks must be equal after the split.

// Network Structure:
// - The network of servers follows a binary tree structure.
// - Each server is represented by an integer value, indicating its processing load.
// - If a server does not exist at a particular position, it is represented as '-1' (NULL).
	
// Help Balbir Singh determine if it is possible to split the network into two equal 
// processing load sub-networks by removing exactly one connection.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print a boolean value.


// Sample Input-1:
// ---------------
// 1 2 3 5 -1 -1 5

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// 3 2 4 3 2 -1 7

// Sample Output-2:
// ----------------
// false

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
public class D9_EqualTree{
    private static Node construct(int[] levelOrd){
        if(levelOrd.length==0){
            return null;
        }
        Node root=new Node(levelOrd[0]);
        Queue<Node> q=new LinkedList<>();
        q.add(root);
        int i=1;
        while(!q.isEmpty() && i<levelOrd.length){
            Node curr=q.poll();
            if(levelOrd[i]!=-1){
                curr.left=new Node(levelOrd[i]);
                q.add(curr.left);
            }
            i+=1;
            if(i==levelOrd.length){
                break;
            }
            if(levelOrd[i]!=-1){
                curr.right=new Node(levelOrd[i]);
                q.add(curr.right);
            }
            i+=1;
        }
        return root;
    }
    private static int modify(Node r){
        if(r==null){
            return 0;
        }
        if(r.left==null && r.right==null){
            return r.val;
        }
        r.val+=modify(r.left)+modify(r.right);
        return r.val;
    }
    private static boolean find(int rval, Node r){
        if(r==null){
            return false;
        }
        return r.val==rval||find(rval,r.left) || find(rval,r.right);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String[] inp1=sc.nextLine().split(" ");
        int[] levelOrd=new int[inp1.length];
        for(int i1=0;i1<inp1.length;i1++){
            levelOrd[i1]=Integer.parseInt(inp1[i1]);
        }
        Node root=construct(levelOrd);
        int t=modify(root);

        System.out.println(t%2==0&&find(t/2,root));
        sc.close();
    }
}

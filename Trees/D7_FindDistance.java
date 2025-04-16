// Bubloo is working with computer networks, where servers are connected 
// in a hierarchical structure, represented as a Binary Tree. Each server (node) 
// is uniquely identified by an integer value.

// Bubloo has been assigned an important task: find the shortest communication 
// path (in terms of network hops) between two specific servers in the network.

// Network Structure:
// ------------------
// The network of servers follows a binary tree topology.
// Each server (node) has a unique identifier (integer).
// If a server does not exist at a certain position, it is represented as '-1' (NULL).

// Given the root of the network tree, and two specific server IDs (E1 & E2), 
// determine the minimum number of network hops (edges) required to 
// communicate between these two servers.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.


// Sample Input-1:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 4 8

// Sample Output-1:
// ----------------
// 4

// Explanation:
// ------------
// The edegs between 4 and 8 are: [4,1], [1,2], [2,3], [3,8]


// Sample Input-2:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 6 6

// Sample Output-2:
// ----------------
// 0

// Explanation:
// ------------
// No edegs between 6 and 6.

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

class D7_FindDistance{
    private static Node construct(int[] levelOrd){
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
    
    private static boolean path(Node r, int e, ArrayList<Integer> p){
        if(r==null){
            return false;
        }
        if(r.val==e){
            return true;
        }
        if(r.left!=null){
            p.add(r.left.val);
            if(path(r.left,e,p)){
                return true;
            }
            p.remove(p.size()-1);
        }
        if(r.right!=null){
            p.add(r.right.val);
            if(path(r.right,e,p)){
                return true;
            }
            p.remove(p.size()-1);
        }
        return false;
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String[] inp1=sc.nextLine().split(" ");
        int[] levelOrd=new int[inp1.length];
        for(int i1=0;i1<inp1.length;i1++){
            levelOrd[i1]=Integer.parseInt(inp1[i1]);
        }
        int e1=sc.nextInt();
        int e2=sc.nextInt();
        Node root=construct(levelOrd);
        ArrayList<Integer> p1=new ArrayList<>();
        p1.add(root.val);
        path(root,e1,p1);
        ArrayList<Integer> p2=new ArrayList<>();
        p2.add(root.val);                       
        path(root,e2,p2);
        // System.out.println(p1);
        // System.out.println(p2);
        int i1=0;
        int i2=0;
        while(i1<p1.size() && i2<p2.size()){
            if(p1.get(i1)==p2.get(i2)){
                i1+=1;
                i2+=1;
            }
            else{
                break;
            }
        }
        // 1 4
        // 1 2 3 8
        System.out.println(p1.size()-i1+p2.size()-i2);
        sc.close();
    }
}

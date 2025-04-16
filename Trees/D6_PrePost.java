// Given the preorder and postorder traversals of a binary tree, construct 
// the original binary tree and print its level order traversal.

// Input Format:
// ---------------
// Space separated integers, pre order data
// Space separated integers, post order data

// Output Format:
// -----------------
// Print the level-order data of the tree.


// Sample Input:
// ----------------
// 1 2 4 5 3 6 7
// 4 5 2 6 7 3 1

// Sample Output:
// ----------------
// [[1], [2, 3], [4, 5, 6, 7]]

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7
    
//         1
//        / \
//       2   3
//      /   / \
//     4   6   7
// 1 2 4 3 6 7
// 4 2 6 7 3 1

// Sample Input:
// ----------------
// 1 2 3
// 2 3 1

// Sample Output:
// ----------------
// [[1], [2, 3]]

// Explanation:
// --------------
//     1
//    / \
//   2  3

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

public class D6_PrePost{
    private static Node construct(int[] preOrd, int[] postOrd, HashMap<Integer,Integer> pre, HashMap<Integer,Integer> post){
        int l1=preOrd.length;
        HashSet<Integer> vis=new HashSet<>();
        Node root=new Node(preOrd[0]);
        Queue<Node> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node curr=q.poll();
            int preind=pre.get(curr.val);
            if(preind<l1-1 && vis.contains(preOrd[preind+1])==false){
                curr.left=new Node(preOrd[preind+1]);
                vis.add((preOrd[preind+1]));
                q.add(curr.left);
            }
            int postind=post.get(curr.val);
            if(postind>0 && vis.contains(postOrd[postind-1])==false){
                curr.right=new Node(postOrd[postind-1]);
                vis.add((postOrd[postind-1]));
                q.add(curr.right);
            }
            vis.add(preOrd[preind]);
        }
        return root;
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
        String[] inp1=sc.nextLine().split(" ");
        String[] inp2=sc.nextLine().split(" ");
        int[] preOrd=new int[inp1.length];
        int[] postOrd=new int[inp2.length];
        HashMap<Integer,Integer> pre=new HashMap<>();
        HashMap<Integer,Integer> post=new HashMap<>();
        for(int i1=0;i1<inp1.length;i1++){
            preOrd[i1]=Integer.parseInt(inp1[i1]);
            pre.put(preOrd[i1],i1);
        }
        for(int i1=0;i1<inp2.length;i1++){
            postOrd[i1]=Integer.parseInt(inp2[i1]);
            post.put(postOrd[i1],i1);
        }
        Node root=construct(preOrd,postOrd,pre,post);
        System.out.println(levelOrder(root));
        sc.close();
        
    }
}

// In an Intelligence Agency, each senior officer supervises either two junior officers or none. 
// The senior officer is assigned a clearance level equal to the higher clearance level of the two junior officers they supervise.
// The clearance levels are represented as integer values in the range [1, 50], and multiple officers may have the same clearance level.
// At the end, all officers (senior and junior) are collectively referred to as agents in the system.
// You are provided with a hierarchical clearance level tree where each node represents an officer's clearance level. The tree structure follows these rules:
// - If a node has two children, its clearance level is the maximum of the two children's clearance levels.
// - If a node has no children, it's clearance level is same as exists.
// - The value -1 indicates an empty (null) position.
// Your task is to find the second highest clearance level among all agents in the agency.
// If no such level exists, réturn -2.
// Input Format:
// A single line of space separated integers, clearance levels of each individual.
// Output Format:
// Print an integer, second top agent based on rank.
// Sample Input-1:
// 2 5 2 -1 -1 2 4
// Sample Output-1:
// 4

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

public class D8_SecondHighest {
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
    private static int helper(Node r, int[] maxes){
        if(r.left==null && r.right==null){
            if(r.val>maxes[0]){
                maxes[1]=maxes[0];
                maxes[0]=r.val;
            }
            if(r.val<maxes[0] && r.val>maxes[1]){
                maxes[1]=r.val;
            }
            return r.val;
        }
        r.val=Math.max(helper(r.left,maxes),helper(r.right,maxes));
        if(r.val>maxes[0]){
            maxes[1]=maxes[0];
            maxes[0]=r.val;
        }
        if(r.val<maxes[0] && r.val>maxes[1]){
            maxes[1]=r.val;
        }
        return r.val;
    }
    // private static void printOrder(Node root){
    //     Queue<Node> q=new LinkedList<>();
    //     q.add(root);
    //     while(!q.isEmpty()){
    //         int s=q.size();
    //         for(int i=0;i<s;i++){
    //             Node c=q.poll();
    //             System.out.print(c.val+" ");
    //             if(c.left!=null){
    //                 q.add(c.left);
    //             }
    //             if(c.right!=null){
    //                 q.add(c.right);
    //             }
    //         }
    //     }
    // }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String[] inp1=sc.nextLine().split(" ");
        int[] levelOrd=new int[inp1.length];
        for(int i1=0;i1<inp1.length;i1++){
            levelOrd[i1]=Integer.parseInt(inp1[i1]);
        }
        Node root=construct(levelOrd);
        int[] maxes=new int[2];
        helper(root,maxes);
        if(maxes[1]==0 || maxes[0]==maxes[1]){
            System.out.println(-2);
        }
        else{
            System.out.println(maxes[1]);
        }
        sc.close();
    }

}

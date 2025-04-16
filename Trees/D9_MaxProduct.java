// Balbir Singh is working with Binary Trees.
// The elements of the tree is given in the level order format.
// Balbir has a task to split the tree into two parts by removing only one edge in the tree, such that the product of sums of both the splitted-trees should be maximum.
// You will be given the root of the binary tree.
// Your task is to help the Balbir Singh to split the binary tree as specified. print the product value, as the product may be large, print the (product % 1000000007)
// NOTE:
// Please
// do consider the node with data as -1' as null in the given trees.
// Input Format:
// Space separated integers, elements of the tree.
// Output Format:
// Print an integer value.
// Sample Input-1:
// 1 2 4 3 5 6
// Sample Output-1:
// 110
// Sample Input-2:
// 3 2 4 3 2 -1 6
// Sample Output-2:
// 100

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
public class D9_MaxProduct{
    static HashSet<Integer> set=new HashSet<>();
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
        set.add(r.val);
        return r.val;
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
        int res=0;
        for(int x:set){
            res=Math.max(res,x*(t-x));
        }
        System.out.println(res);
        sc.close();
    }
}

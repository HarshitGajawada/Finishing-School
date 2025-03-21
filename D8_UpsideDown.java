// VishnuVardan is working with Decision Trees for AI-based predictions.
// To analyze alternative outcomes, Kishore has planned to flip the decision tree horizontally to simulate a reverse processing approach.
// Rules for Flipping the Decision Tree:
// - The original
// root node becomes the new rightmost node.
// - The original left child becomes the new root node.
// The original right child becomes the new left child.
// This transformation is applied level by level recursively.
// Note:
// - Each node in the given tree has either e or 2 children.
// - Every right node in the tree has a left sibling sharing the same parent.
// - Every right node has no further children (i.e., they are leaf nodes) • Your task is to help VishnuVardan flip the Decision Tree while following the given transformation rules.

// Sample Input-1:
// ---------------
// 4 2 3 5 1

// Sample Output-1:
// ----------------
// 5 1 2 3 4

// Sample Input-2:
// ---------------
// 4 2 5

// Sample Output-2:
// ----------------
// 2 5 4




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

public class D8_UpsideDown {
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
    private static Node flip(Node r){
        if(r==null || r.left==null){
            return r;
        }
        Node newn=flip(r.left);
        r.left.right=r;
        r.left.left=r.right;
        r.left=null;
        r.right=null;
        return newn;
    }
    private static void printOrder(Node root){
        Queue<Node> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int s=q.size();
            for(int i=0;i<s;i++){
                Node c=q.poll();
                System.out.print(c.val+" ");
                if(c.left!=null){
                    q.add(c.left);
                }
                if(c.right!=null){
                    q.add(c.right);
                }
            }
            // System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String[] inp1=sc.nextLine().split(" ");
        int[] levelOrd=new int[inp1.length];
        for(int i1=0;i1<inp1.length;i1++){
            levelOrd[i1]=Integer.parseInt(inp1[i1]);
        }
        Node root=construct(levelOrd);
        Node newroot=flip(root);
        printOrder(newroot);
        sc.close();
    }
}

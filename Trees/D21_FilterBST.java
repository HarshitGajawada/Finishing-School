// Imagine you're the curator of an ancient manuscript archive. Each manuscript is
// assigned a unique significance score, and the archive is arranged so that every 
// manuscript on the left has a lower score and every manuscript on the right has a
// higher score, forming a special ordered display. Now, for an upcoming exhibition,
// scholars have decided that only manuscripts with significance scores between low 
// and high (inclusive) are relevant. Your task is to update the archive by removing
// any manuscripts whose scores fall outside the range [low, high]. Importantly, 
// while you remove some manuscripts, you must preserve the relative order of the 
// remaining ones—if a manuscript was originally displayed as a descendant of another, 
// that relationship should remain intact. It can be proven that there is a unique 
// way to update the archive.

// Display the manuscript of the updated archive. Note that the main manuscript 
// (the root) may change depending on the given score boundaries.

// Input format:
// Line 1: space separated scores to build the manuscript archive
// Line 2: two space seperated integers, low and high.

// Output format:
// space separated scores of the updated archive

// Example 1:
// input=
// 1 0 2
// 1 2
// output=
// 1 2

// Explanation:
// Initial archieve:
//       1
//      / \
//     0   2


// Updated archieve:
//     1
//      \
//       2
// After removing manuscripts scores below 1 (i.e. 0), only the manuscript with 1 
// and its right manuscript 2 remain.

// Example 2:
// input=
// 3 0 4 2 1
// 1 3
// output=
// 3 2 1

// Explanation:
// Initial archieve:
//           3
//          / \
//         0   4
//          \
//           2
//          /
//         1

// Updated archieve:
//       3
//      /
//     2
//    /
//   1


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

public class D21_FilterBST{
    private static Node insert(Node root, int val) {
        if(root==null){
            return new Node(val);
        }
        if(val<root.val){
            root.left=insert(root.left,val);
        } 
        else if(val>root.val){
            root.right=insert(root.right,val);
        }
        return root;
     }
    private static Node preOrder(Node r, int l, int rt){
        if(r==null){
            return r;
        }
        if(r.val<l){
            return preOrder(r.right,l,rt);
        }
        if(r.val>rt){
            return preOrder(r.left,l,rt);
        }
        r.left=preOrder(r.left,l,rt);
        r.right=preOrder(r.right,l,rt);
        return r;
    }
    private static ArrayList<Integer> levelOrder(Node curr){
        ArrayList<Integer> levels=new ArrayList<>();
        if(curr==null){
            return levels;
        }
        Queue<Node> q=new LinkedList<>();
        q.add(curr);
        while(!q.isEmpty()){
            int s=q.size();
            for(int i=0;i<s;i++){
                Node c=q.poll();
                levels.add(c.val);
                if(c.left!=null){
                    q.add(c.left);
                }
                if(c.right!=null){
                    q.add(c.right);
                }
            }
        }
        return levels;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String[] inp1=sc.nextLine().split(" ");
        int l=sc.nextInt();
        int r=sc.nextInt();
        Node root=null;
        for(int i1=0;i1<inp1.length;i1++){
            root=insert(root, Integer.parseInt(inp1[i1]));
        }
        root=preOrder(root,l,r);
        System.out.println(levelOrder(root));
        sc.close();
    }
}

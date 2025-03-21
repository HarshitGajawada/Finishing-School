// Imagine you are designing a grand castle where each room holds a specific amount 
// of treasure. The castle is built in a binary layout, meaning every room may lead 
// to two adjacent wings—a left wing and a right wing. 

// An "organized section" of the castle follows this rule: for any given room, 
// every room in its left wing contains a treasure value that is strictly less 
// than the current room’s value, and every room in its right wing contains a 
// value that is strictly greater. Additionally, each wing must itself be organized
// according to the same rule.

// Your challenge is to determine the maximum total treasure (i.e., the sum of 
// treasure values) that can be gathered from any such organized section of the castle.

// Example 1:
// input=
// 1 4 3 2 4 2 5 -1 -1 -1 -1 -1 -1 4 6
// output=
// 20

// Castle:
//           1
//         /   \
//        4     3
//       / \   / \
//      2   4 2   5
//               / \
//              4   6

// Explanation: The best organized section starts at the room with a treasure value 
// of 3, yielding a total treasure of 20.

// Example 2:
// input=
// 4 3 -1 1 2
// output=
// 2

// Castle:
//     4
//    /
//   3
//  / \
// 1   2

// Explanation: The optimal organized section is just the single room with a 
// treasure value of 2.

// Example 3:
// input=
// -4 -2 -5
// output=
// 0

// Castle:
//    -4
//   /  \
// -2   -5
 
// Explanation: Since all rooms contain negative treasure values, no beneficial 
// organized section exists, so the maximum total treasure is 0.

// Constraints:

// - The number of rooms in the castle ranges from 1 to 40,000.
// - Each room’s treasure value is an integer between -40,000 and 40,000.


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

public class D23_ValidSubBST {
    private static Node build(String[] values){
        if(values.length==0 || values[0].equals("-1")) return null;
        Node root=new Node(Integer.parseInt(values[0]));
        Queue<Node> queue=new LinkedList<>();
        queue.add(root);
        int i=1;
        while(i<values.length){
            Node curr=queue.poll();
            if(!values[i].equals("-1")){
                curr.left=new Node(Integer.parseInt(values[i]));
                queue.add(curr.left);
            }
            i++;
            if(i<values.length && !values[i].equals("-1")){
                curr.right=new Node(Integer.parseInt(values[i]));
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }

    private static int[] postOrder(Node r, int[] res){
        if(r==null){
            return new int[]{0,1,Integer.MIN_VALUE,Integer.MAX_VALUE};
        }
        int[] l=postOrder(r.left,res);
        int[] rt=postOrder(r.right,res);
        if(l[1]!=1 || rt[1]!=1){
            return new int[]{Integer.MIN_VALUE,0,0,0};
        }
        if(l[2]<r.val && rt[3]>r.val){
            int sum=l[0]+rt[0]+r.val;
            res[0]=Math.max(res[0],sum);
            return new int[]{sum,1,Math.max(r.val,rt[2]),Math.min(r.val,l[3])};
        }
        return new int[]{Integer.MIN_VALUE,0,0,0};
    }
    
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String[] inp1=sc.nextLine().split(" ");
        Node root=build(inp1);
        int[] res=new int[1];
        postOrder(root,res);
        System.out.println(res[0]);
        sc.close();
    }
}

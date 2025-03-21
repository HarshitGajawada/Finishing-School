// Imagine you are a librarian organizing books on vertical shelves in a grand library. The books are currently scattered across a tree-like structure, where each book (node) has a position determined by its shelf number (column) and row
// number (level) •
// Your task is to arrange the books on shelves so that:
// 1. Books are placed column by column from left to right.
// 2. Within the same column, bocks are arranged from top to bottom (i.e., by row).
// 3. If multiple books belong to the same shelf and row, they should be arranged from left to right, just as they appear in the original scattered arrangement.
// Sample Input:
// 3 9 20-1 -1 15 7
// Sample Output:
// [[9],[3,15],[20],[7]]

import java.util.*;

class Node {
    int val;
    Node left, right;
    Node(int v) {
        val=v;
        left=null;
        right=null;
    }
}

public class D9_VerticalTraversalBFS {
    private static Node construct(int[] levelOrd) {
        if (levelOrd.length == 0 || levelOrd[0] == -1) {
            return null;
        }
        Node root = new Node(levelOrd[0]);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int i=1;

        while(!q.isEmpty() && i<levelOrd.length){
            Node curr=q.poll();
            if(i<levelOrd.length && levelOrd[i]!=-1){
                curr.left=new Node(levelOrd[i]);
                q.add(curr.left);
            }
            i++;
            if(i<levelOrd.length && levelOrd[i]!=-1){
                curr.right=new Node(levelOrd[i]);
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }

    public static List<List<Integer>> traverse(Node r) {
        if(r==null){
            return new ArrayList<>();
        }
        TreeMap<Integer,List<Integer>> verticalMap=new TreeMap<>();
        Queue<Pair> q=new LinkedList<>();
        q.add(new Pair(r,0));
        while(!q.isEmpty()){
            Pair p=q.poll();
            Node curr=p.node;
            int vertical=p.vertical;
            verticalMap.putIfAbsent(vertical,new ArrayList<>());
            verticalMap.get(vertical).add(curr.val);
            if(curr.left!=null){
                q.add(new Pair(curr.left,vertical-1));
            }
            if(curr.right!=null){
                q.add(new Pair(curr.right,vertical+1));
            }
        }
        List<List<Integer>> res=new ArrayList<>();
        for(List<Integer> nodes:verticalMap.values()){
            res.add(nodes);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String[] inp=sc.nextLine().split(" ");
        int[] levelOrd = new int[inp.length];
        for(int i=0;i<inp.length; i++){
            levelOrd[i]=Integer.parseInt(inp[i]);
        }
        Node root = construct(levelOrd);
        List<List<Integer>> res=traverse(root);
        System.out.println(res);
        sc.close();
    }
}

class Pair {
    Node node;
    int vertical;
    Pair(Node node, int vertical) {
        this.node = node;
        this.vertical = vertical;
    }
}
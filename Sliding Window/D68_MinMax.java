// Luke likes to construct and play with arrays. His dad gave him an array M of 
// length N and assigned him the following tasks to be done in order:
//  - reate continuous groups of size i from the array M (where i goes from 1 to N).
//  - For each group of size i, find the minimum value.
//  - Among all the minimums from that step, select the maximum.
//  - Add this selected value as the i-th element of a new array P.
//  - Repeat until i = N.

// Note: Use 1-based indexing for array P in logic.

// Input Format:
// -------------
// input1: Integer N – length of array M
// input2: Integer array M of length N

// Output Format:
// --------------
// Return the array P as output.

// Sample Input:
// -------------
// 3
// 1 2 3

// Sample Output:
// --------------
// 3 2 1


// Sample Input: 
// -------------
// 4
// 20 10 30 40

// Sample Output: 
// --------------
// 40 30 10 10

import java.util.*;

public class D68_MinMax{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int[] prev=new int[n];
        int[] nex=new int[n];
        int[] res=new int[n+1];
        for(int i=0;i<=n;i++){
            res[i]=Integer.MIN_VALUE;
        }
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<n;i++){
            while(!stack.isEmpty() && arr[stack.peek()]>=arr[i]){
                stack.pop();
            }
            if(stack.isEmpty()){
                prev[i]=-1;
            } 
            else{
                prev[i]=stack.peek();
            }
            stack.push(i);
        }
        stack.clear();
        for(int i=n-1;i>=0;i--){
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                stack.pop();
            }
            if (stack.isEmpty()) {
                nex[i]=n;
            } else {
                nex[i]=stack.peek();
            }
            stack.push(i);
        }
        for(int i=0;i<n;i++){
            int size=nex[i]-prev[i]-1;
            res[size]=Math.max(res[size],arr[i]);
        }
        for(int i=n-1;i>=1;i--){
            res[i]=Math.max(res[i],res[i+1]);
        }
        for(int i=1;i<=n;i++){
            System.out.print(res[i]+" ");
        }
        sc.close();
    }
}

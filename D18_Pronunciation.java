// "Emphatic Pronunciation" of a given word is where we take the word and
// replicate some of the letter to emphasize their impact.

// Instead of saying 'oh my god', someone may say "ohhh myyy goddd", 
// We define emphatic pronunciation of a word, which is derived by replicating 
// a group (or single) of letters in the original word. 

// So that the replicated group is atleast 3 characters or more and 
// greater than or equal to size of original group. 
// For example Good -> Goood is an emphatic pronunciation,
// but Goodd is not because in Goodd the 'd' are only occuring twice consecutively.
 
// In the question you are given the "Emphatic pronunciation" word, 
// you have to findout how many words can legal result in the 
// "emphatic pronunciation" word.

// Input Format:
// -------------
// Line-1 -> A String contains a single word, Emphatic Pronunciation word
// Line-2 -> Space seperated word/s

// Output Format:
// --------------
// Print an integer as your result


// Sample Input-1:
// ---------------
// goood
// good goodd

// Sample Output-1:
// ----------------
// 1


// Sample Input-2:
// ---------------
// heeelllooo
// hello hi helo

// Sample Output-2:
// ----------------
// 2



import java.util.*;

public class D18_Pronunciation{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String emp=sc.nextLine();
        String[] strs=sc.nextLine().split(" ");
        int res=0;
        for(String curr:strs){
            int i1=0;
            int i2=0;
            boolean exp=true;
            while(i1<emp.length() && i2<curr.length()){
                if(emp.charAt(i1)!=curr.charAt(i2)){
                    exp=false;
                    break;
                }
                int empc=0;
                int currc=0;
                char ch=emp.charAt(i1);
                while(i1<emp.length() && emp.charAt(i1)==ch){
                    empc+=1;
                    i1+=1;
                }
                while(i2<curr.length() && curr.charAt(i2)==ch){
                    currc+=1;
                    i2+=1;
                }
                if((empc<3 && empc!=currc) || empc<currc){
                    exp=false;
                    break;
                }
            }
            if(exp && (i1==emp.length() && i2==curr.length())){
                res+=1;
            }
        }
        System.out.println(res);
        sc.close();
    }
}

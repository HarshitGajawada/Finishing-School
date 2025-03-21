// Birbal is creating a new binary code system BBC (Birbal Binary Code) as follows:

// I	f(I)
// -------
// 0	""
// 1	"0"
// 2	"1"
// 3	"00"
// 4	"01"
// 5	"10"
// 6	"11"
// 7	"000"

// You are given an integer value I, where I is positive number.
// Your task is to find BBC representation of  the given number I.

// Input Format:
// -------------
// An integer I

// Output Format:
// --------------
// Print the BBC representation of I.


// Sample Input-1:
// ---------------
// 23

// Sample Output-1:
// ----------------
// 1000


// Sample Input-2:
// ---------------
// 45

// Sample Output-2:
// ----------------
// 01110

import java.util.*;

public class D19_BirbalBinary {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        String bin=Integer.toBinaryString(n);
        StringBuilder nearest=new StringBuilder("0".repeat(bin.length()));
        nearest.setCharAt(0,'1');
        // System.out.println(nearest);
        int res=Integer.parseInt(nearest.toString(),2);
        // System.out.println(res);
        String r=Integer.toBinaryString(n-(res-1));
        System.out.println("0".repeat(bin.length()-1-r.length())+r);
        sc.close();
    }
}

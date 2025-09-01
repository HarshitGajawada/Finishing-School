// You are given multiple threads, each representing an atom: Nitrogen (N) or 
// Oxygen (O). Your task is to synchronize these threads so they form Laughing Gas 
// (nitrous oxide - N₂O) molecules. Each molecule must be formed by exactly 
// 2 Nitrogen atoms and 1 Oxygen atom. Threads must:

//     -Pass a barrier only in complete groups of three (2×N + 1×O).
//   - 'Bond' immediately once a complete trio is present, before any atom from 
//     the next molecule proceeds.

// You will implement synchronization so that if we split the output stream into 
// groups of 3 characters, each group contains exactly N, N, O in any order.

// Constraints:
// ------------
// - The input is a string atoms made only of characters 'N' and 'O'.
// - Let k be the number of molecules. Then the string must contain exactly 2k 'N' and k 'O'.
// - 1 ≤ k ≤ 20 (i.e., up to 60 threads).

// Input Fromat:
// -------------
// A single line string atoms of 'N' and 'O', e.g., NOONNN.

// Output Fromat:
// --------------
// A single line string that represents a valid bonding sequence. Every 3-character
// chunk must contain 2 'N' and 1 'O'. Order within each chunk may vary 
// (e.g., NNO, NON, ONN). If the input is not having  exactly 2k*N and k*O letters
// print "Invalid"


// Sample Input:
// -------------
// NOONNN

// Sample Output:
// --------------
// N2O - 1 is formed
// N2O - 2 is formed


// Sample Input:
// -------------
// NOONNNNNNNOO

// Sample Output:
// --------------
// N2O - 1 is formed
// N2O - 2 is formed
// N2O - 3 is formed
// N2O - 4 is formed

import java.util.*;
import java.util.concurrent.*;

public class D90_CyclicBarrierEx {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String inp=sc.nextLine().trim();
        sc.close();
        int oc=0,nc=0;
        for(char c:inp.toCharArray()){
            if(c=='N'){
                nc+=1;
            }
            else if(c=='O'){
                oc+=1;
            }
            else{
                System.out.println("Invalid");
                return;
            }
        }
        if(oc*2!=nc){
            System.out.println("Invalid");
            return;
        }
        ArrayList<Thread> threads=new ArrayList<>();
        N2O n2o=new N2O();
        for(char c:inp.toCharArray()){
            if(c=='N'){
                threads.add(new Thread(()->{
                    try{
                        n2o.nitrogen();
                    }
                    catch(InterruptedException e){}
                }));
            }
            else{
                threads.add(new Thread(()->{
                    try{
                        n2o.oxygen();
                    }
                    catch(InterruptedException e){}
                }));
            }
        }
        for(Thread t:threads){
            t.start();
        }
        for(Thread t:threads){
            try{
                t.join();
            }
            catch(InterruptedException e){}
        }
    }
}

class N2O{
    private Semaphore n=new Semaphore(2);
    private Semaphore o=new Semaphore(1);
    private CyclicBarrier b;
    private static int mols=0;
    
    N2O(){
        b=new CyclicBarrier(3,()->{
            mols+=1;
            System.out.println("N2O - "+mols+" is formed");
            n.release(2);
            o.release(1);
        });
    }
    public void nitrogen() throws InterruptedException{
        n.acquire();
        try{
            b.await();
        }
        catch(BrokenBarrierException e){
            e.printStackTrace();
        }
    }
    public void oxygen() throws InterruptedException{
        o.acquire();
        try{
            b.await();
        }
        catch(BrokenBarrierException e){
            e.printStackTrace();
        }
    }
}

// There are a group of kids playing a game in a circle to test their arithmetic division skills.
// The game starts with Kid 1, who says the number 1 to begin. Then, in clockwise order, each kid 
// takes a turn and says the next number, but with the following rules:
//  - If the number is divisible by 3 (but not by 5), the kid says "Hi".
//  - If the number is divisible by 5 (but not by 3), the kid says "Hello".
//  - If the number is divisible by both 3 and 5, the kid says "HiHello".
//  - Otherwise, the kid simply says the number itself.

// The same game rules are now implemented in a multi-threaded environment.
// We are given an instance of a HiHelloGame class, which has four methods:

//  - hi(printHi) → Prints "Hi" when the number is divisible by 3 only.
//  - hello(printHello) → Prints "Hello" when the number is divisible by 5 only.
//  - hiHello(printHiHello) → Prints "HiHello" when the number is divisible by both 3 and 5.
//  - number(printNumber) → Prints the number itself when none of the above conditions are met.

// The same instance of HiHelloGame will be shared across four threads:

// Thread A: Calls hi() to output "Hi".
// Thread B: Calls hello() to output "Hello".
// Thread C: Calls hiHello() to output "HiHello".
// Thread D: Calls number() to output the number.

// Your task is to modify the HiHelloGame class so that it outputs the correct sequence for numbers 
// from 1 to N in the correct order according to the rules above.

// Input Format:
// -------------
// Line-1: An integer N.

// Output Format:
// --------------
// Print a string array[].

// Constraints:
// • 1 <= n <= 10^4

// Sample Input-1:
// ---------------
// 5

// Sample Output-1:
// ----------------
// 1 2 Hi 4 Hello

// Sample Input-2:
// ---------------
// 15

// Sample Output-2:
// ----------------
// 1 2 Hi 4 Hello Hi 7 8 Hi Hello 11 Hi 13 14 HiHello 

import java.util.*;
import java.util.function.IntConsumer;

class HiHelloGame {
    int curr = 1;
    int n;

    HiHelloGame(int n) {
        this.n = n;
    }

    public void hi(Runnable printHi) throws InterruptedException {
        synchronized (this) {
            while (curr <= n) {
                if (curr % 3 == 0 && curr % 5 != 0) {
                    printHi.run();
                    curr++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }

    public void hello(Runnable printHello) throws InterruptedException {
        synchronized (this) {
            while (curr <= n) {
                if (curr % 5 == 0 && curr % 3 != 0) {
                    printHello.run();
                    curr++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }

    public void hiHello(Runnable printHiHello) throws InterruptedException {
        synchronized (this) {
            while (curr <= n) {
                if (curr % 3 == 0 && curr % 5 == 0) {
                    printHiHello.run();
                    curr++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        synchronized (this) {
            while (curr <= n) {
                if (curr % 3 != 0 && curr % 5 != 0) {
                    printNumber.accept(curr);
                    curr++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HiHelloGame game = new HiHelloGame(n);
        Thread t1 = new Thread(() -> {
            try {
                game.hi(() -> System.out.print("Hi "));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                game.hello(() -> System.out.print("Hello "));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                game.hiHello(() -> System.out.print("HiHello "));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t4 = new Thread(() -> {
            try {
                game.number(num -> System.out.print(num + " "));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
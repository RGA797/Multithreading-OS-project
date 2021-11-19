import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String args[]) throws InterruptedException {
        //create Semaphore=&amp;amp;gt; #permits = 1
        Semaphore sem1 = new Semaphore(1);
        Semaphore sem2 = new Semaphore(1);
        Pens pens = new Pens();
        Booth booth = new Booth();
        PaperBallot paperBallot = new PaperBallot();
        // Create thread instances T1 &amp;amp;amp; T2
        //T1=&amp;amp;gt; Increments the count; T2=&amp;amp;gt; Decrements the count
        Voter bob = new Voter("Bob", sem1, sem2, paperBallot, pens, booth);
        Voter alice = new Voter("Alice", sem1, sem2, paperBallot, pens, booth);

        // start T1 &amp;amp;amp; T2
        bob.start();
        alice.start();
        // Wait T1 &amp;amp;amp; T2
        bob.join();
        alice.join();
    }
}

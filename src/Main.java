import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String args[]) throws InterruptedException {

        //create Semaphore=&amp;amp;gt; #permits = 1
        Semaphore sem1 = new Semaphore(10);
        Semaphore sem2 = new Semaphore(10);
        Semaphore totalScoreSem = new Semaphore(1);
        Pen[] pens = new Pen[10];
        Booth[] booths = new Booth[10];
        PaperBallot paperBallot = new PaperBallot();
        // Create thread instances T1 &amp;amp;amp; T2
        //T1=&amp;amp;gt; Increments the count; T2=&amp;amp;gt; Decrements the count
//        Voter bob = new Voter("Bob", sem1, sem2, paperBallot, pens, booth);
//        Voter alice = new Voter("Alice", sem1, sem2, paperBallot, pens, booth);
        Voter[] voters = new Voter[1000000];

        //create pens
        for (int i = 0; i < pens.length; i++) {
            pens[i] = new Pen();
        }

        //create booths
        for (int i = 0; i < booths.length; i++) {
            booths[i] = new Booth();
        }


        for (int i = 0; i < voters.length; i++) {
            voters[i] = new Voter("default", i, sem1, sem2, totalScoreSem, paperBallot, pens, booths);
        }

        for (int i = 0; i < voters.length; i++) {
            voters[i].start();
        }
//        // start T1 &amp;amp;amp; T2
//        bob.start();
//        alice.start();
//        // Wait T1 &amp;amp;amp; T2
//        bob.join();
//        alice.join();
    }
}

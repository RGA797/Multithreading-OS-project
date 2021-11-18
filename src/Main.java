import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String args[]) throws InterruptedException {
        //create Semaphore=&amp;amp;gt; #permits = 1
        Semaphore sem = new Semaphore(1);
        SharedRes sharedRes = new SharedRes();
        // Create thread instances T1 &amp;amp;amp; T2
        //T1=&amp;amp;gt; Increments the count; T2=&amp;amp;gt; Decrements the count
        ThreadClass thread1 = new ThreadClass(sem, "T1", sharedRes);
        ThreadClass thread2 = new ThreadClass(sem, "T2", sharedRes);

        // start T1 &amp;amp;amp; T2
        thread1.start();
        thread2.start();
        // Wait T1 &amp;amp;amp; T2
        thread1.join();
        thread2.join();
        System.out.println("count: " + SharedRes.count);    // display final count.
    }
}

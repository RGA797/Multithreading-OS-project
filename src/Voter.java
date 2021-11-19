import java.util.concurrent.Semaphore;

public class Voter extends Thread{

    Pens pen;
    Booth booth;
    PaperBallot paperBallot;
    Semaphore sem1;
    Semaphore sem2;
    String threadName;
    public Voter(String threadName, Semaphore sem1, Semaphore sem2, PaperBallot paperBallot, Pens pen, Booth booth)   {
        super(threadName);
        this.sem1 = sem1;
        this.sem2 = sem2;
        this.threadName = threadName;
        this.paperBallot = paperBallot;
        this.pen = pen;
        this.booth = booth;
    }

    @Override
    public void run() {
        int times;
        if (threadName.equals("Bob")) {
            times = 4;
        } else times = 6;

        if (threadName.equals("Bob")) {
            try {
                sem1.acquire();
                sleep(100);
                sem2.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                sem2.acquire();
                sleep(100);
                sem1.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            sem1.acquire();
            int pensLeft = pen.getNumberOfPens();
            for (int i = 0; i < times; i++) {
                try {
                    if (threadName.equals("Bob"))
                        sleep(500);
                    else sleep(200);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                vote();
                pensLeft--;
            }
            pen.setNumberOfPens(pensLeft);
            System.out.println(threadName + " used pen : " + pen.getNumberOfPens() + " pens left");

        } catch (InterruptedException exc) {
            System.out.println(exc);
        }

        System.out.println(threadName + ":Released the permit");
        sem1.release();
    }

    public void vote() {
        booth.use();
        pen.write(paperBallot);
//        System.out.println(threadName + " used pen :" + pen.getNumber() + " pens left");
    }

//    @Override
//    public void run() {
//        // Thread T1 processing
//        if(this.getName().equals("T1"))  {
//            System.out.println("Start: " + threadName);
//            try  {
//                System.out.println(threadName + " :waiting for a permit.");
//                // acquire the permit
//                sem.acquire();
//                System.out.println(threadName + ":Acquired permit");
//                // access shared resource
//                for(int i=0; i < 5; i++)   {
//                    Pen.count++;
//                    System.out.println(threadName + ": " + Pen.count);
//                    Thread.sleep(10);
//                }
//
//            } catch (InterruptedException exc) {
//                System.out.println(exc);
//            }
//            // Release the permit.
//            System.out.println(threadName + ":Released the permit");
////            sem.release();
//        }
//        // Thread T2 processing
//        else  {
//            System.out.println("Start: " + threadName);
//            try  {
//                System.out.println(threadName + ":waiting for a permit.");
//                // acquire the lock
////                sem.acquire();
//                System.out.println(threadName + ":Acquired permit");
//                // process the shared resource
//                for(int i=0; i < 5; i++)  {
//                    Pen.count--;
//                    System.out.println(threadName + ": " + Pen.count);
//                    Thread.sleep(10);
//                }
//            } catch (InterruptedException exc) {
//                System.out.println(exc);
//            }
//            // Release the permit.
//            System.out.println(threadName + ":Released the permit.");
////            sem.release();
//        }
//    }
}

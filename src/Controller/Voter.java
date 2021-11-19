package Controller;

import Model.Booth;
import Model.PaperBallot;
import Model.Pen;

import java.util.concurrent.Semaphore;

public class Voter extends Thread{
    static int TOTAL_VOTES = 0;
    Pen[] pens;
    Booth[] booths;
    PaperBallot paperBallot;
    Semaphore sem1;
    Semaphore sem2;
    Semaphore totalScoreSem;
    String threadName;
    int ID;
    public Voter(String threadName, int ID, Semaphore sem1, Semaphore sem2, Semaphore totalScoreSem, PaperBallot paperBallot, Pen[] pens, Booth[] booths) {
        super(threadName);
        this.sem1 = sem1;
        this.sem2 = sem2;
        this.totalScoreSem = totalScoreSem;
        this.threadName = threadName;
        this.paperBallot = paperBallot;
        this.pens = pens;
        this.booths = booths;
        this.ID = ID;
    }

    @Override
    public void run() {
        try {
            sleep((int) (10 + Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            if (ID % 2 == 0) {
                try {
                    sem1.acquire();
                    sleep(3);

                    if (sem2.tryAcquire()) {
                        vote();
                        sem1.release();
                        sem2.release();
                        break;
                    } else {
                        sem1.release();
                        System.out.println("voter " + ID + " : sem2 not acquired, sem1 released");
                        sleep((int) (1 + Math.random() * 2));
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    sem2.acquire();
                    sleep(3);

                    if (sem1.tryAcquire()) {
                        vote();
                        sem1.release();
                        sem2.release();
                        break;
                    } else {
                        sem2.release();
                        System.out.println("voter" + ID + " : sem1 not acquired, sem2 released");
                        sleep((int) (1 + Math.random() * 4));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void vote() throws InterruptedException {
        Pen myPen = findAvailablePen();
        Booth myBooth = findAvailableBooth();

        myPen.write();
        paperBallot.choose(chooseParty());
        //totalScoreSem.acquire();
        TOTAL_VOTES++;
        System.out.println("Total votes:" + TOTAL_VOTES);
        //totalScoreSem.release();
        myBooth.leave();
    }


    public Pen findAvailablePen() {
        for (int i = 0; i < pens.length; i++) {
            if (!(pens[i].isInUse())) {
                pens[i].setInUse();
                return pens[i];
            }
        }
        return null;
    }

    public Booth findAvailableBooth() {
        for (int i = 0; i < booths.length; i++) {
            if (!(booths[i].isOccupied())) {
                booths[i].use();
                return booths[i];
            }
        }
        return null;
    }

    private int chooseParty() {
        return (int) (Math.random() * 5);
    }
}

import Controller.Voter;
import Model.Booth;
import Model.PaperBallot;
import Model.Party;
import Model.Pen;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String args[]) throws InterruptedException {

        //create Semaphore=&amp;amp;gt; #permits = 1
        Semaphore sem1 = new Semaphore(10);
        Semaphore sem2 = new Semaphore(10);
        Semaphore totalScoreSem = new Semaphore(1);
        Pen[] pens = new Pen[10];
        Booth[] booths = new Booth[10];
        Voter[] voters = new Voter[1000];
        Party[] parties = {new Party("Socialdemotrakiet"),
                new Party("Venstre"),
                new Party("Fremskridtspartiet"),
                new Party("Enhedslisten"),
                new Party("Liberal Alliance")};

        PaperBallot[] paperBallots = new PaperBallot[voters.length];

        //create pens
        for (int i = 0; i < pens.length; i++) {
            pens[i] = new Pen();
        }

        //create booths
        for (int i = 0; i < booths.length; i++) {
            booths[i] = new Booth();
        }

        //create paper ballots
        for (int i = 0; i < paperBallots.length; i++) {
            paperBallots[i] = new PaperBallot();
        }

        for (int i = 0; i < voters.length; i++) {
            voters[i] = new Voter("default", i, sem1, sem2, totalScoreSem, paperBallots[i], pens, booths);
        }

        for (int i = 0; i < voters.length; i++) {
            voters[i].start();
        }

        for (int i = 0; i < voters.length; i++) {
            voters[i].join();
        }
    }
}

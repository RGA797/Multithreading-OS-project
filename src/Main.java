import Controller.VoteCounter;
import Controller.Voter;
import Model.Booth;
import Model.PaperBallot;
import Model.Party;
import Model.Pen;

import java.util.HashMap;
import java.util.List;
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
        VoteCounter[] voteCounters = new VoteCounter[10];
        HashMap<String, Party> parties = null;

        List<PaperBallot> paperBallots = null;

        //create parties and add to hashmap
        assert false;
        parties.put("Socialdemokratiet", new Party("Socialdemokratiet"));
        parties.put("Venstre", new Party("Venstre"));
        parties.put("Fremskridtspartiet", new Party("Fremskridtspartiet"));
        parties.put("Enhedslisten", new Party("Enhedslisten"));
        parties.put("Liberal ALliance", new Party("Liberal ALliance"));

        //create pens
        for (int i = 0; i < pens.length; i++) {
            pens[i] = new Pen();
        }

        //create booths
        for (int i = 0; i < booths.length; i++) {
            booths[i] = new Booth();
        }

        //create paper ballots
        for (int i = 0; i < paperBallots.size(); i++) {
            paperBallots.add(new PaperBallot());
        }

        //create voters
        for (int i = 0; i < voters.length; i++) {
            voters[i] = new Voter("default", i, sem1, sem2, totalScoreSem, paperBallots.get(i), pens, booths);
        }

        //create vote counters
        for (int i = 0; i < voteCounters.length; i++) {
            voteCounters[i] = new VoteCounter(paperBallots, parties);
        }

        //start voters
        for (int i = 0; i < voters.length; i++) {
            voters[i].start();
        }

        //wait for all voters to finish
        for (int i = 0; i < voters.length; i++) {
            voters[i].join();
        }
    }

}

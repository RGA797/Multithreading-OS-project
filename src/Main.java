import Controller.VoteCounter;
import Controller.Voter;
import Model.Booth;
import Model.PaperBallot;
import Model.Party;
import Model.Pen;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String args[]) throws InterruptedException {

        //create Semaphore=&amp;amp;gt; #permits = 1
        Semaphore sem1 = new Semaphore(10);
        Semaphore sem2 = new Semaphore(10);
        Semaphore sem3 = new Semaphore(1);
        Semaphore totalScoreSem = new Semaphore(1);
        Pen[] pens = new Pen[10];
        Booth[] booths = new Booth[10];
        Voter[] voters = new Voter[1000];
        VoteCounter[] voteCounters = new VoteCounter[10];
        HashMap<String, Party> parties = new HashMap<>();

        List<PaperBallot> paperBallots = new Vector<>();
        //create parties and add to hashmap
        parties.put("Socialdemokratiet", new Party("Socialdemokratiet"));
        parties.put("Venstre", new Party("Venstre"));
        parties.put("Fremskridtspartiet", new Party("Fremskridtspartiet"));
        parties.put("Enhedslisten", new Party("Enhedslisten"));
        parties.put("Liberal Alliance", new Party("Liberal ALliance"));

        //create pens
        for (int i = 0; i < pens.length; i++) {
            pens[i] = new Pen();
        }

        //create booths
        for (int i = 0; i < booths.length; i++) {
            booths[i] = new Booth();
        }

        //create paper ballots
        for (int i = 0; i < voters.length; i++) {
            paperBallots.add(new PaperBallot());
        }

        //create voters
        for (int i = 0; i < voters.length; i++) {
            voters[i] = new Voter("default", i, sem1, sem2, totalScoreSem, paperBallots.get(i), pens, booths);
        }

        //start voters
        for (Voter voter : voters) {
            voter.start();
        }

        //create vote counters
        for (int i = 0; i < voteCounters.length; i++) {
            voteCounters[i] = new VoteCounter(paperBallots, parties, sem3);
        }

        //wait for all voters to finish
        for (Voter voter : voters) {
            voter.join();
        }

        //start votecounters
        for (VoteCounter voteCounter : voteCounters) {
            voteCounter.start();
        }

        //wait for all votecounters to finish
        for (VoteCounter voteCounter : voteCounters) {
            voteCounter.join();
        }

        System.out.println("Socialdemoktratiet got " + parties.get("Socialdemokratiet").getVotes() + " votes");
        System.out.println("Venstre got " + parties.get("Venstre").getVotes() + " votes");
        System.out.println("Fremskridtspartiet got " + parties.get("Fremskridtspartiet").getVotes() + " votes");
        System.out.println("Enhedslisten got " + parties.get("Enhedslisten").getVotes() + " votes");
        System.out.println("Liberal Alliance got " + parties.get("Liberal Alliance").getVotes() + " votes");
    }
}

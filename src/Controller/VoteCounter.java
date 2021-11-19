package Controller;

import Model.PaperBallot;
import Model.Party;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;

public class VoteCounter extends Thread {

    HashMap<String, Party> parties;
    List<PaperBallot> paperBallots;
    Semaphore sem;

    public VoteCounter(List<PaperBallot> paperBallots, HashMap<String, Party> parties, Semaphore sem) {
        this.paperBallots = paperBallots;
        this.parties = parties;
        this.sem = sem;
    }

    @Override
    public void run() {
        Party party;
        PaperBallot paperBallot;
        try {
            while (!(paperBallots.size() == 0)) {
                sem.acquire();
                if (paperBallots.size() == 0){
                    break;
                }
                paperBallot = paperBallots.remove((int) (Math.random() * paperBallots.size()));
                party = parties.get(paperBallot.getChosenParty());
                party.incrementVotes();
                sem.release();
            }
            sem.release();
            //worker takes a rest
            sleep(5);
        }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

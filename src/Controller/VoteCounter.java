package Controller;

import Model.PaperBallot;
import Model.Party;

import java.util.HashMap;
import java.util.List;

public class VoteCounter extends Thread {

    HashMap<String, Party> parties;
    List<PaperBallot> paperBallots;

    public VoteCounter(List<PaperBallot> paperBallots, HashMap<String, Party> parties) {
        this.paperBallots = paperBallots;
        this.parties = parties;
    }


    @Override
    public void run() {
        Party party;
        PaperBallot paperBallot;
        while (!(paperBallots.isEmpty())) {
            paperBallot = paperBallots.remove((int)(Math.random() * paperBallots.size()));
            party = parties.get(paperBallot.getChosenParty());
            party.incrementVotes();
        }
    }
}

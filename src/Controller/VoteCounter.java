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
        String partyName;
        Party party;
        while (!(paperBallots.size() == 0)) {

            partyName = paperBallots.get((int)(Math.random() * paperBallots.size())).getChosenParty();
            party = parties.get(partyName);
            party.incrementVotes();
        }
    }
}

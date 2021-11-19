package Model;

public class Party {
    public final String partyName;
    private int partyVotes = 0;

    public Party(String partyName) {
        this.partyName = partyName;
    }

    public int getVotes() {
        return partyVotes;
    }

    public void incrementVotes() {
        partyVotes++;
    }

}

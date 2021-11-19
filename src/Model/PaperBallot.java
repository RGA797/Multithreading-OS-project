package Model;

public class PaperBallot {
    private String chosenParty = "";

    private String[] parties = {"Socialdemokratiet", "Venstre", "Fremskridtspartiet", "Enhedslisten", "Liberal Alliance"};
    private boolean used = false;
    private boolean counted = false;

    public void choose(int index) {
        chosenParty = parties[index];
        used = true;
    }

    public String getChosenParty() {
        return chosenParty;
    }

    public String[] getParties() {
        return parties;
    }

    public boolean isCounted(){
        return counted;
    }

    public void count() {
        counted = true;
    }
}

package Model;

public class PaperBallot {
    private String chosenParty = "";

    private String[] parties = {"Socialdemokratiet", "Venstre", "Fremskridtspartiet", "Enhedslisten", "Liberal Alliance"};

    public void choose(int index) {
        chosenParty = parties[index];
    }

    public String getChosenParty() {
        return chosenParty;
    }
}

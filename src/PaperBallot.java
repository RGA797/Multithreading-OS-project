public class PaperBallot {
    private String chosenParty = "";
    private String[] parties = {"Socialdemoktratiet", "Venstre", "Fremskridtspartiet", "Enhedslisten", "Liberal Alliance"};
    private boolean used = false;


    public void choose(int index) {
        chosenParty = parties[index];
        used = true;
    }
}

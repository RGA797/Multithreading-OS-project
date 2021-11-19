public class Pens {
    private int numberOfPens = 10;

    public void write (PaperBallot paperBallot) {
//        number--;
        paperBallot.use();
    }

    public int getNumberOfPens() {
        return this.numberOfPens;
    }
    public void setNumberOfPens(int numberOfPens) {
        this.numberOfPens = numberOfPens;
    }
}

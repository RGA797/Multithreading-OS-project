package Model;

public class Pen {
    private boolean inUse = false;
    private int length = 10;

    public void write () {
        decrementLength();
        setFree();
    }

    public int getLength() {
        return this.length;
    }
    private void decrementLength() {
        this.length = length - 1;
    }
    public boolean isInUse() {
        return inUse;
    }

    public void setInUse() {
        inUse = true;
    }
    public void setFree() {
        inUse = false;
    }
}

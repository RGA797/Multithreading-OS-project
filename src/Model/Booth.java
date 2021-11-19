package Model;

public class Booth {

    public boolean occupied = false;

    public void use() {
        occupied = true;
    }

    public void leave() {
        occupied = false;
    }

    public boolean isOccupied() {
        return occupied;
    }
}

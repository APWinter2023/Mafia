package model;

public class Sniper extends Citizen{
    private int numberOfBulletsLeft;

    public Sniper(String name, int id) {
        super(name, id);
        numberOfBulletsLeft = 2;
    }

    public Sniper(String name) {
        this(name , -1);
    }

    public int getNumberOfBulletsLeft() {
        return numberOfBulletsLeft;
    }

    public boolean shot(){
        return numberOfBulletsLeft -- > 0;
    }
}

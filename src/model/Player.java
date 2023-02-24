package model;

public abstract class Player {
    protected int id;
    protected String name;
    protected boolean isAlive;
    protected boolean isMute;

    public Player(String name , int id){
        this.name = name;
        this.id = id;
        isAlive = true;
        isMute = false;
    }

    public Player(String name) {
        this(name , -1);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isMute() {
        return isMute;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setMute(boolean mute) {
        isMute = mute;
    }

//    public abstract void accept();
}

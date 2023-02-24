package model;

public class GodFather extends Mafia{

    private boolean asked=false;

    public GodFather(String name, int id) {
        super(name, id);
    }

    public GodFather(String name) {
        super(name);
    }

    public boolean isAsked() {
        return asked;
    }

    public void setAsked(boolean asked) {
        this.asked = asked;
    }
}

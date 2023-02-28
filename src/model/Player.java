package model;

public abstract class Player {
    protected int id;
    protected String name;
    protected boolean alive;
    protected boolean mute;
    // temporary fields
    protected boolean heal, healByLecter, killByGodfather, killBySniper;
    private int votes, mafiaVotes;

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
        this.alive = true;
        this.mute = false;
        heal = healByLecter = killByGodfather = killBySniper = false;
        votes = mafiaVotes = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isMute() {
        return mute;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public abstract String action(Player target);

    public abstract boolean detectIsMafia();

    public void vote(Player target) {
        target.increaseVote();
    }

    public void reset() {
        heal = healByLecter = killByGodfather = killBySniper = false;
        votes = mafiaVotes = 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHeal() {
        return heal;
    }

    public void setHeal(boolean heal) {
        this.heal = heal;
    }

    public boolean isHealByLecter() {
        return healByLecter;
    }

    public void setHealByLecter(boolean healByLecter) {
        this.healByLecter = healByLecter;
    }

    public boolean isKillByGodfather() {
        return killByGodfather;
    }

    public void setKillByGodfather(boolean killByGodfather) {
        this.killByGodfather = killByGodfather;
    }

    public boolean isKillBySniper() {
        return killBySniper;
    }

    public void setKillBySniper(boolean killBySniper) {
        this.killBySniper = killBySniper;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getMafiaVotes() {
        return mafiaVotes;
    }

    public void setMafiaVotes(int mafiaVotes) {
        this.mafiaVotes = mafiaVotes;
    }

    public void increaseVote() {
        this.votes++;
    }

    public void increaseMafiaVote() {
        this.votes++;
    }

}

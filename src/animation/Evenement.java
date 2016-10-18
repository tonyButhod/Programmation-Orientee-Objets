package animation;


public abstract class Evenement {
    long date;

    public Evenement(long date) {
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public abstract void execute();
}

package animation;
import objects.Robot;


public abstract class Evenement {
    private long date;
    protected Robot robot;

    public Evenement(long date, Robot robot) {
        this.date = date;
        this.robot = robot;
    }

    public long getDate() {
        return date;
    }
    public Robot getRobot(){
    	return this.robot;
    }
    public void setRobot(Robot robot){
    	this.robot = robot;
    }

    public abstract void execute();
}

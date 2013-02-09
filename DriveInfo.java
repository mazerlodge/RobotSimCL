public class  DriveInfo {

    public int speed;
    public int direction;
    public int duration;

    public DriveInfo() {

	speed = 0;
	direction = 0;
	duration = 0;

    }

    public DriveInfo(int sp, int dir, int duration) {
	speed = sp;
	direction = dir;
	this.duration = duration;

    }

    public void stop() {

	speed = 0;
	direction = 0;
	duration = 1;

    }
}

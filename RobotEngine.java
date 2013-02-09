public class RobotEngine {

    public void go() {

	BTRobot robo = new BTRobot();
	
	robo.operatorControl();
	
    }

    public static void main(String[] args) {

	RobotEngine re = new RobotEngine();
	re.go();

    }

}
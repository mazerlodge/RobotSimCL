public class RobotEngine {

    private int SIM_LOOP_LIMIT = 12;
    private int SIM_AUTO_MODE_LIMIT = 2;

    private int simLoopCount=0;

    public void go() {

	BTRobot robo = new BTRobot();

	while(simLoopCount < SIM_LOOP_LIMIT) {
	    simLoopCount++;

	    // Using a simple loop count to determine which mode is active.
	    if (simLoopCount < SIM_AUTO_MODE_LIMIT)
		robo.currentMode = OperatingMode.AUTONOMOUS;
	    else
		robo.currentMode = OperatingMode.OPERATOR_CONTROL;

	    // Give each of the operating modes a chance to run.
	    //   Each mode checks to see if that mode is to do anything.
	    robo.autonomous();
	    robo.operatorControl();
	
	} // while simLoop...

    }

    public static void main(String[] args) {

	RobotEngine re = new RobotEngine();
	re.go();

    }

}

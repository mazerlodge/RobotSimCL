public class RobotEngine {

    private int SIM_LOOP_LIMIT = 12;
    private int SIM_AUTO_MODE_LIMIT = 2;

    public int simLoopCount=0;

    private BTRobot robo;

    public RobotEngine() {

	// Create a robot and give it a reference to this instance of the Engine.
	robo = new BTRobot();
	robo.theEngine = this;

    }

    public void go() {

	// The robot starts in autonomous mode, do one update then go into auto mode.
	this.update();


    }

    public void update() {
 
	while(simLoopCount < SIM_LOOP_LIMIT) {
	    System.out.printf("RE.go() ln13: Running Loop %d\n", simLoopCount);
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

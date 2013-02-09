public class FMSEngine {

    private int SIM_LOOP_LIMIT = 12;
    private int SIM_AUTO_MODE_LIMIT = 2;

    public int simLoopCount=0;

    // This version of the FMSEngine supports a single robot.
    private BTRobot robo;

    public FMSEngine() {

	// Create a robot and give it a reference to this instance of the Engine.
	robo = new BTRobot();
	robo.theEngine = this;

    }

    // This is the main entry point for the simulator.
    public static void main(String[] args) {

	FMSEngine fms = new FMSEngine();
	fms.go();

    }

    public void go() {

	// The robot starts in autonomous mode, do one update then go into auto mode.
	this.update();


    }

    public void update() {

	OperatingMode desiredMode = OperatingMode.DISABLED;
 
	while(simLoopCount < SIM_LOOP_LIMIT) {
	    System.out.printf("RE.go() ln13: Running Loop %d\n", simLoopCount);
	    simLoopCount++;

	    // Using a simple loop count to determine which mode is active.
	    if (simLoopCount < SIM_AUTO_MODE_LIMIT)
		desiredMode = OperatingMode.AUTONOMOUS;
	    else
		desiredMode = OperatingMode.OPERATOR_CONTROL;

	    // Give the robot a slice of execution time.
	    robo.doExecutionSlice(desiredMode);
	
	} // while simLoop...

	robo.currentMode = OperatingMode.DISABLED;
	// Note: There is no call to doExecutionSlice() here, the robot shuts down
	//        in the simulator. In the cRIO it doesn't quit.
    }


}

public class FMSEngine {

    private int SIM_LOOP_LIMIT = 12;
    private int SIM_AUTO_MODE_LIMIT = 2;

    public int simLoopCount=0;
    public OperatingMode currentDesiredMode = OperatingMode.DISABLED; 

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

    public OperatingMode getDesiredOperatingMode() {

	OperatingMode desiredMode = OperatingMode.DISABLED;
 
	if (simLoopCount < SIM_LOOP_LIMIT) {
	    System.out.printf("FMSE.gDOM() ln41: Running Loop %d\n", simLoopCount);
	    simLoopCount++;

	    // Using a simple loop count to determine which mode is active.
	    if (simLoopCount < SIM_AUTO_MODE_LIMIT)
		desiredMode = OperatingMode.AUTONOMOUS;
	    else
		desiredMode = OperatingMode.OPERATOR_CONTROL;

	} 
	else {
	    desiredMode = OperatingMode.DISABLED;

	}

	return desiredMode;

    }

    public void update() {

	while (simLoopCount < SIM_LOOP_LIMIT) {
	    OperatingMode desiredMode = getDesiredOperatingMode();
 
	    // Give the robot a slice of execution time.
	    robo.doExecutionSlice(desiredMode);
	}

    }

}

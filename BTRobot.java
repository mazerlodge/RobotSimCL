public class BTRobot extends SimulatedRobot {

    private boolean bRobotEnabled;

    private BTDriveTrain dt;
    private ControlBoard cb;
    private BTAutonomous autoMode;

    public BTRobot() {

	robotInit();

    }

    public void robotInit() {

	dt = new BTDriveTrain();
	cb = new ControlBoard();
	autoMode = new BTAutonomous();

	bRobotEnabled = true;
    }

    // NOTE: This mentod is required by SimulatedRobot
    /*
    public void doExecutionSlice(OperatingMode desiredMode) {

	if (desiredMode == OperatingMode.OPERATOR_CONTROL) 
	    operatorControl();

	if (desiredMode == OperatingMode.AUTONOMOUS)
	    autonomous();


    }
    */

    public void disabled() {

	showMsg("Entering disabled() method...");

	// TODO: Any graceful shutdown goes here.

    }

    public void operatorControl() {

	showMsg("Entering operatorControl()...");

	while(isOperatorControl()) {

	    cb.update();
	    dt.update(cb);

	    // TODO: The following have not been implemented yet.
	    // vi.update(cb); // Note: Vision should update right after cb
	    // shooter.update(cb); 
	    // climber.update(cb);
	    

	}

    }

    public void autonomous() {

	showMsg("Entering autonomous()...");

	while(isAutonomous()) {
	    // Note: Notice that control board's update() method is *not*
	    //        called while in autonomous mode.  The autoMode object
	    //        updates the control board's members based on the predefined
	    //        autonomous mode plan.

	    autoMode.update(cb);
	    dt.update(cb);

	    // TODO: The following have not been implemented yet.
	    // vi.update(cb); // Note: Vision should update right after cb
	    // shooter.update(cb); 
	    // climber.update(cb);
	    

	}

    }
}

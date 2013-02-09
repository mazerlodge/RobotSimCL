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

    public void operatorControl() {

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

	while(isAutonomous()) {

	    autoMode.update(cb);
	    dt.update(cb);

	    // TODO: The following have not been implemented yet.
	    // vi.update(cb); // Note: Vision should update right after cb
	    // shooter.update(cb); 
	    // climber.update(cb);
	    

	}

    }
}

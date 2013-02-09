public class BTRobot {


    private int SIM_LOOP_LIMIT = 10;
    private int simLoopCount=0;
    private boolean bRobotEnabled;

    private BTDriveTrain dt;
    private ControlBoard cb;

    public BTRobot() {

	robotInit();

    }

    public void robotInit() {

	dt = new BTDriveTrain();
	cb = new ControlBoard();
	bRobotEnabled = true;
	simLoopCount = 0;
    }

    public boolean isOperatorControl() {
	
	boolean bRval = true;

	if (simLoopCount < SIM_LOOP_LIMIT) 
	    simLoopCount++;
	else
	    bRval = false;

	return  bRval;
	
    }

    public void operatorControl() {

	while(isOperatorControl()) {

	    cb.update();
	    dt.update(cb);

	}

    }

    public void autonomous() {

    }
}

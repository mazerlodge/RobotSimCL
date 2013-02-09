public abstract class SimulatedRobot {
    // This class acts as a proxy for SimpleRobot when working
    // with non-cRio based code running in the RobotEngine simulator.

    public OperatingMode currentMode;
    public FMSEngine theEngine;

    public abstract void operatorControl();
    public abstract void autonomous();
    public abstract void disabled();

    // This method is called by the FMS Engine during each execution cycle.
    public void doExecutionSlice(OperatingMode desiredMode) {

	// TODO: Add any required system checks before entering
	//         the desired mode.
	this.currentMode = desiredMode;

	if (desiredMode == OperatingMode.OPERATOR_CONTROL) 
	    operatorControl();

	if (desiredMode == OperatingMode.AUTONOMOUS)
	    autonomous();

	if (desiredMode == OperatingMode.DISABLED)
	    disabled();

    }


    public void showMsg(String msg) {

	System.out.println(msg);

    }

    public boolean isOperatorControl() {
	
	boolean bRval = false;

	theEngine.update();

	if (currentMode == OperatingMode.OPERATOR_CONTROL) 
	    bRval = true;

	return  bRval;
	
    }

    public boolean isAutonomous() {
	
	boolean bRval = false;

	theEngine.update();

	if (currentMode == OperatingMode.AUTONOMOUS) 
	    bRval = true;

	return  bRval;
	
    }




}
public class SimulatedRobot {
    // This class acts as a proxy for SimpleRobot when working
    // with non-cRio based code running in the RobotEngine simulator.

    public OperatingMode currentMode;
    public RobotEngine theEngine;

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
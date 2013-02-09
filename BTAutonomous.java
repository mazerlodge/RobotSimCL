public class BTAutonomous {

    private int DRIVE_SPEED = 40;
    private int DEGREES_PER_DURATION = 1; // how many durations in a degree of turn?
    private int TURN_SPEED_DIVISOR = 2;   // how much should drive speed be reduced when turning?

    private int autoProgramID = 1;  // TODO: Assume auto program 1 until physical switch installed.
    private int currentStep=0;

    private boolean bStepStarted = false;

    public BTAutonomous() {

	// Assuming auto mode 1 if none specified.
	autoProgramID = 1;
    }

    public BTAutonomous(int autoID) {

	autoProgramID = autoID;
	
    }

    public void update(ControlBoard cb) {
	
	switch(autoProgramID) {
	
	case 1:
	    doAutoProgram1(cb);
	    break;

	case 2:
	    doAutoProgram2(cb);
	    break;

	// TODO: Add more programs as needed.

	} 

    }

    private void doAutoProgram1(ControlBoard cb) {
	// Auto Program 1 Steps are:
	// 1- Drive forward for 10
	// 2- Turn right 90 degrees
	// 3- Drive forward 15
	// 4- Shoot 3 times
	// 5- Wait, and stay in Wait.

	switch(currentStep) {

	case 0:
	    // Just starting up, switch to step 1.
	    goToNextStep();
	    break;

	case 1:
	    // Drive forward for 10

	    // See if we've already started this step
	    if (!bStepStarted) {
		// Just getting started here, set the drive info
		driveForward(10, cb);
		bStepStarted = true;
		break;
	    }
	   
	    // See if we're still driving from a previous loop
	    if (cb.getDriveInfo(DriveSide.LEFT).duration > 0) {
		// we're still driving, just exit for now
		break;
	    }

	    // See if we just finished driving from the previous loop
	    if (cb.getDriveInfo(DriveSide.LEFT).duration == 0) {
		// We just finished this step, go on to the next step
	        goToNextStep();
	    }

	    break;

	case 2:
	    // 2- Turn right 90 degrees
	   // See if we've already started this step
	    if (!bStepStarted) {
		// Just getting started here, set the drive info
		turn(90, cb);
		bStepStarted = true;
		break;
	    }
	    
	    // See if we're still turning from a previous loop
	    if (cb.getDriveInfo(DriveSide.LEFT).duration > 0) {
		// we're still turning, just exit for now
		break;
	    }

	    // See if we just finished turning from the previous loop
	    if (cb.getDriveInfo(DriveSide.LEFT).duration == 0) {
		// We just finished this step, go on to the next step
	        goToNextStep();
	    }

	    break;

	case 3:
	    // 3- Drive forward 15

	    // See if we've already started this step
	    if (!bStepStarted) {
		// Just getting started here, set the drive info
		driveForward(15, cb);
		bStepStarted = true;
		break;
	    }
	   
	    // See if we're still driving from a previous loop
	    if (cb.getDriveInfo(DriveSide.LEFT).duration > 0) {
		// we're still driving, just exit for now
		break;
	    }

	    // See if we just finished driving from the previous loop
	    if (cb.getDriveInfo(DriveSide.LEFT).duration == 0) {
		// We just finished this step, go on to the next step
	        goToNextStep();
	    }

	    break;

	case 4:
	    // 4- Shoot 3 times

	    // See if we've already started this step
	    if (!bStepStarted) {
		// Just getting started here, set the shot info
		shoot(3, cb);
		bStepStarted = true;
		break;
	    }
	   
	    // See if we're still shooting from a previous loop
	    if (cb.getShotInfo().shotsRemaining > 0) {
		// we're still shooting, just exit for now.
		break;
	    }

	    // See if we just finished shooting from the previous loop
	    if (cb.getShotInfo().shotsRemaining == 0) {
		// We just finished this step, go on to the next step
	        cb.getShotInfo().setFiringMode(false);
		goToNextStep();
	    }

	    // See if we got 'stuck' and couldn't find the target in a reasonable time
	    if (cb.getShotInfo().elapsedLoops > cb.getShotInfo().MAX_ELAPSED_LOOPS) {
		// We're stuck, bail out on shooting.
		cb.getShotInfo().setFiringMode(false);
		goToNextStep();
	    }

	    break;

	case 5:
	    // 5- Wait, and stay in Wait.
	    robotWait(cb);
	    break;

	} // end switch


    }

    private void doAutoProgram2(ControlBoard cb) {

	// TODO: Not yet implemented. Switch/Case structure like Auto1, different strategy.


    }

    private void goToNextStep() {
	// Do the two things required to begin a new step.
	currentStep++;
	bStepStarted = false;

    }

    private void driveForward(int duration, ControlBoard cb) {

	// Since driving forward means left and right do the same
	//  thing, we only have to make one DriveInfo object and 
	//  can use it twice in the call to the control board.
	DriveInfo di = new DriveInfo(DRIVE_SPEED, 1, duration);
 
	cb.setDriveInfo(di, DriveSide.LEFT);
	cb.setDriveInfo(di, DriveSide.RIGHT);

    }

    private void turn(int degrees, ControlBoard cb) {

	// Calculate how long we have turn turn for each degree.
	// TODO: This calcuation is a guess, test on the robot to find exact amount
	int turnDuration = degrees / DEGREES_PER_DURATION;
	int turnSpeed = DRIVE_SPEED / TURN_SPEED_DIVISOR;

	// If the degrees value was negative, correct the duration 
	//  durations are always positive.
	if (degrees < 0)
	    turnDuration = turnDuration * -1;

	DriveInfo diTurnWheel = new DriveInfo(turnSpeed, 1, turnDuration);
	DriveInfo diStationaryWheel = new DriveInfo(0, 1, turnDuration);

	// If the degrees value was negative turn the RIGHT wheel,
	//  otherwise turn the LEFT wheel.
	if (degrees < 0) {
	    cb.setDriveInfo(diStationaryWheel, DriveSide.LEFT);
	    cb.setDriveInfo(diTurnWheel, DriveSide.RIGHT);
	}
	else {
	    cb.setDriveInfo(diTurnWheel, DriveSide.LEFT);
	    cb.setDriveInfo(diStationaryWheel, DriveSide.RIGHT);

	}

    }

    private void shoot(int shotsToTake, ControlBoard cb) {

	cb.getShotInfo().setShotsToTake(shotsToTake);
	cb.getShotInfo().setFiringMode(true);

    }

    private void robotWait(ControlBoard cb) {

	cb.getShotInfo().setFiringMode(false);
	cb.getDriveInfo(DriveSide.LEFT).stop();
	cb.getDriveInfo(DriveSide.RIGHT).stop();

    }
}

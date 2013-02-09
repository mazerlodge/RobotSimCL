public class  ShotInfo {

    public int DEFAULT_SPEED = 85;
    public int MAX_ELAPSED_LOOPS = 200; // evaluated by control board update
               // Note: This is a safety, in case the 'bot gets 'stuck' in firing mode.

    public int speed;                // set by vision system
    public int verticalAdjustment;   // set by operator feedback after a shot
    public int horizontalAdjustment; // set by operator feedback after a shot
    public int shotsRemaining;       // how many shots to take, set by operator or autonomous
    public int elapsedLoops;         // how long the robot has been in firing mode
    public boolean bInFiringMode;    // set by operator pulling trigger or autonomous mode
    public boolean bTargetAligned;   // set by vision system

    public ShotInfo() {

	bTargetAligned = false;
	bInFiringMode = false;
	speed = DEFAULT_SPEED;;
	verticalAdjustment = 0;
	horizontalAdjustment = 0;
	shotsRemaining = 0;
	elapsedLoops = 0;
	
    }

    public ShotInfo(int sp, int vertAdj, int horizAdj, int shotsToTake, boolean bInFiringMode) {
	// This constructor probably won't get used, it is included for parity with
	//   the convention of having a constructor w/ all public members for a 'struct like' class.

	speed = sp;
	verticalAdjustment = vertAdj;
	horizontalAdjustment = horizAdj;
	shotsRemaining = shotsToTake;;
	this.bInFiringMode = bInFiringMode;

    }

    public void setShotAdjustment(int vertAdjAmount, int horizAdjAmount) {
	// Change the adjustment by the amount specified.
       
	verticalAdjustment += vertAdjAmount;
	horizontalAdjustment += horizAdjAmount;

    }

    public void setShotsToTake(int shotCount) {

	shotsRemaining = shotCount;

    }

    public void setFiringMode(boolean bDesiredFiringMode) {

	if (!bInFiringMode && bDesiredFiringMode) {
	    // Get into firing mode and reset loop count
	    bInFiringMode = true;
	    elapsedLoops = 0;
	}

	if (bInFiringMode && (bDesiredFiringMode == false)) {
	    // Get out of firing mode
	    bInFiringMode = false;
	    
	}

    }

}

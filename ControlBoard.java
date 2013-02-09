public class ControlBoard {

    public int xVal;
    private DriveInfo leftDI;
    private DriveInfo rightDI;
    private ShotInfo si;

    public ControlBoard() {

	xVal = 0;
	leftDI = new DriveInfo();
	rightDI = new DriveInfo();
	si = new ShotInfo();

    }

    public void update() {

	// just testing, increase xVal on each call.
	xVal++;

	// simulate getting gampad/joystic input
	// both sticks forward at 40 percent
	DriveInfo leftDI = new DriveInfo(85, 1, 1);
	DriveInfo rightDI = new DriveInfo(85, 1, 1);
	setDriveInfo(leftDI, DriveSide.LEFT);
	setDriveInfo(rightDI, DriveSide.RIGHT);

    }

    public void setDriveInfo(DriveInfo di, DriveSide ds) {

	if (ds == DriveSide.LEFT) 
	    leftDI = di;
	else
	    rightDI = di;

    }

    public DriveInfo getDriveInfo(DriveSide ds) {
	
	DriveInfo rval;

	if (ds == DriveSide.LEFT) 
	    rval = leftDI;
	else
	    rval = rightDI;

	return rval;

    }


    public void setShotInfo(ShotInfo si) {

	this.si = si;

    }

    public ShotInfo getShotInfo() {
	
	return si;

    }

}